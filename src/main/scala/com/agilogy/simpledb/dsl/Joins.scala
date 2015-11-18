package com.agilogy.simpledb.dsl

import javax.sql.DataSource

import com.agilogy.simpledb.schema.{Column, ForeignKey, Table}

import com.agilogy.simpledb.dsl.Join._
import com.agilogy.srdb.types.DbCursorReader

import scala.language.implicitConversions

case class Join(table: Table, on: Predicate, isLeft: Boolean) {

  val sql = s"${if (isLeft) "left join" else "join"} ${aliasedTable(table)} on ${on.sql}"
  def `*`:Seq[Column[_]] = table.*
  private[simpledb] val parameters: Seq[Param[_]] = on.parameters

  private[simpledb] val allocateConstants: ConstantAllocation[Join] = on.allocateConstants.map(p => this.copy(on = p))
}

object Join {
  private[simpledb] def aliasedTable(t: Table) = t.tableName + " " + t.alias

  private[simpledb] def join(table: Table, on: Predicate) = Join(table, on, isLeft = false)

  private[simpledb] def leftJoin(table: Table, on: Predicate) = Join(table, on, isLeft = true)
}

trait Relation{


  private[simpledb] val parameters:Seq[Param[_]]
  def sql:String
  def `*`: Seq[Column[_]]

  private[simpledb] type Self
  private[simpledb] val allocateConstants: ConstantAllocation[Self]
}

object Relation{
  /*implicit*/ private[dsl] def table2Relation(t:Table):JoinRelation = JoinRelation(t, Seq.empty)
}


case object NoRelation extends Relation {

  override val sql: String = ""

  override def `*`: Seq[Column[_]] = Seq.empty

  override private[simpledb] val parameters: Seq[Param[_]] = Seq.empty

  private[simpledb] type Self = NoRelation.type
  private[simpledb] override val allocateConstants: ConstantAllocation[Self] = ConstantAllocation.empty(this)
}

case class JoinRelation(table: Table, joins: Seq[Join]) extends Relation {

  val sql: String = "from " + aliasedTable(table) + (if (joins.isEmpty) "" else " ") + joins.map(_.sql).mkString(" ")

  def join(rightTable: Table, on: Predicate): JoinRelation = JoinRelation(table, joins :+ Join.join(rightTable, on))

  def join(rightTable: Table, on: ForeignKey): JoinRelation = JoinRelation(table, joins :+ Join.join(rightTable, on = on.joinOnPredicate(rightTable)))

  def leftJoin(rightTable: Table, on: Predicate): JoinRelation = JoinRelation(table, joins :+ Join.leftJoin(rightTable, on))

  def leftJoin(rightTable: Table, on: ForeignKey): JoinRelation = JoinRelation(table, joins :+ Join.leftJoin(rightTable, on.joinOnPredicate(rightTable)))

  override def `*`: Seq[Column[_]] = table.* ++ joins.flatMap(_.*)

  private[simpledb] override val parameters: Seq[Param[_]] = joins.flatMap(_.parameters)
  private[simpledb] type Self = JoinRelation
  private[simpledb] override val allocateConstants: ConstantAllocation[Self] = ConstantAllocation.allocateAll(joins)(_.allocateConstants).map(js => this.copy(joins = js))
}

case class FromQueryPart(ds:DataSource, from:Relation) extends GeneratedSelectMethods{

  protected def query[RT](columns: Seq[SelectedElement[_]], reads: DbCursorReader[RT]): DslQuery[RT] = DslQuery.build(ds, from, True, columns, reads)

  val sql:String = from match {
    case NoRelation => ""
    case _ => "from " + from.sql
  }

  def where(p: Predicate): WhereQueryPart = WhereQueryPart(ds, from, p)

  def groupBy(columns: Column[_]*): GroupByQueryPart = GroupByQueryPart(ds, from, True, columns)

  def orderBy(criteria: OrderByCriterion*): OrderByQueryPart = OrderByQueryPart(ds, from, where = True, groupBy = Seq.empty, orderBy = criteria)
}

case class WhereQueryPart(ds:DataSource, joinQueryPart: Relation, where: Predicate) extends GeneratedSelectMethods {

  def orderBy(criteria: OrderByCriterion*): OrderByQueryPart = OrderByQueryPart(ds, joinQueryPart, where, groupBy = Seq.empty, orderBy = criteria)

  val sql = joinQueryPart.sql + " where " + where.sql

  protected def query[RT](columns: Seq[SelectedElement[_]], reads: DbCursorReader[RT]): DslQuery[RT] = DslQuery.build(ds, joinQueryPart, where, columns, reads)

  def groupBy(columns: Column[_]*): GroupByQueryPart = GroupByQueryPart(ds, joinQueryPart, where, columns)
}

case class GroupByQueryPart(ds:DataSource, joinQueryPart: Relation, where: Predicate, groupBy: Seq[Column[_]]) extends GeneratedSelectMethods {
  def orderBy(criteria: OrderByCriterion*): OrderByQueryPart = OrderByQueryPart(ds, joinQueryPart, where, groupBy, orderBy = criteria)

  override protected def query[RT](columns: Seq[SelectedElement[_]], reads: DbCursorReader[RT]): DslQuery[RT] =
    DslQuery.build(ds, joinQueryPart, where, columns, reads, groupBy)
}

sealed trait OrderByOrder {
  private[dsl] val sql: String
}

case object Asc extends OrderByOrder {
  override private[dsl] val sql: String = "asc"
}

case object AscNullsFirst extends OrderByOrder {
  override private[dsl] val sql: String = "asc nulls first"
}

case object Desc extends OrderByOrder {
  override private[dsl] val sql: String = "desc"
}

case object DescNullsLast extends OrderByOrder {
  override private[dsl] val sql: String = "desc nulls last"
}

case class OrderByCriterion(column: Column[_], order: OrderByOrder) {
  private[dsl] val sql = column.completeName + (if (order != Asc) " " + order.sql else "")
}

object OrderByCriterion {
  implicit def fromColumn(column: Column[_]): OrderByCriterion = OrderByCriterion(column, Asc)

  implicit def fromPair(pair: (Column[_], OrderByOrder)): OrderByCriterion = OrderByCriterion(pair._1, pair._2)
}

case class OrderByQueryPart(ds:DataSource, joinQueryPart: Relation, where: Predicate, groupBy: Seq[Column[_]], orderBy: Seq[OrderByCriterion]) extends GeneratedSelectMethods {
  override protected def query[RT](columns: Seq[SelectedElement[_]], reads: DbCursorReader[RT]): DslQuery[RT] = DslQuery.build(ds, joinQueryPart, where, columns, reads, groupBy, orderBy)
}


