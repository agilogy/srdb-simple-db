package com.agilogy.simpledb.dsl

import javax.sql.DataSource

import com.agilogy.simpledb.schema.{Column, Table}
import com.agilogy.simpledb._
import com.agilogy.srdb.types.DbReader

trait Statement[RT] extends RawStatement[RT]{
  def constants:Seq[Constant[_]]
  def preAssignedParameters:Seq[ParameterValue[_]] = constants.map(_.asParameterValue)
}

trait StatementWithoutResultSetReader extends Statement[Int]{
  val keyReader = NoStatementResultReader
}

trait StatementWithResultReader[RT] extends Statement[RT]{
  val keyReader:StatementResultReader[RT]
}

case class Update1(ds:DataSource, t: Table) {

  def set(c: ColumnAssignment[_]*) = UpdateAllRowsStatement(ds, t, c)

}

case class ColumnAssignment[T](c: Column[T], value: Expression[T]) {
  def sql: String = c.name + " = " + value.sql
  private[simpledb] def asPredicate = {
    c ==== value
  }
  private[simpledb] val allocateConstants: ConstantAllocation[ColumnAssignment[T]] = value.allocateConstants.map(v => ColumnAssignment(c,v))
}

private[simpledb] sealed trait UpdateStatementBase extends StatementWithoutResultSetReader {
  val t: Table
  val assignments: Seq[ColumnAssignment[_]]
  val wherePredicate: Option[Predicate]

  def sql = s"update ${t.tableName} ${t.alias} set ${assignments.map(_.sql).mkString(", ")}" +
    wherePredicate.map(w => " where " + w.sql).getOrElse("")
}

case class UpdateAllRowsStatement private (ds:DataSource, t: Table, assignments: Seq[ColumnAssignment[_]],constants:Seq[Constant[_]]) extends UpdateStatementBase {
  def where(predicate: Predicate) = UpdateStatement(ds, t, assignments, predicate, ConstantAllocationContext(constants))

  override val wherePredicate: Option[Predicate] = None

}

object UpdateAllRowsStatement{
  def apply(ds:DataSource, t: Table, assignments: Seq[ColumnAssignment[_]]):UpdateAllRowsStatement = {
    import com.agilogy.simpledb.dsl.ConstantAllocation.allocateAll
    val (as,ctx) = allocateAll(assignments)(_.allocateConstants)(ConstantAllocationContext.initial)
    UpdateAllRowsStatement(ds, t,as,ctx.constants)
  }
}

case class UpdateStatement(ds:DataSource, t: Table, assignments: Seq[ColumnAssignment[_]], where: Predicate, constants:Seq[Constant[_]]) extends UpdateStatementBase {
  override val wherePredicate: Option[Predicate] = Some(where)
}

object UpdateStatement{
  private[simpledb] def apply(ds:DataSource, t:Table, assignments:Seq[ColumnAssignment[_]], where:Predicate, cac:ConstantAllocationContext):UpdateStatement = {
    import com.agilogy.simpledb.dsl.ConstantAllocation.allocateExpressionConstants
    val (w,ctx) = allocateExpressionConstants(where)(cac)
    UpdateStatement(ds, t,assignments,w,ctx.constants)
  }
}

trait DeleteStatementBase extends StatementWithoutResultSetReader{
  val t: Table
  protected val wherePredicate: Option[Predicate]
  override def sql: String = s"delete from ${t.tableName} ${t.alias}" + wherePredicate.map(w => " where " + w.sql).getOrElse("")
}

case class DeleteAllRowsStatement(ds:DataSource, t: Table) extends DeleteStatementBase {
  override protected val wherePredicate: Option[Predicate] = None

  def where(predicate:Predicate):DeleteStatement = DeleteStatement(ds, t,predicate)

  override def constants: Seq[Constant[_]] = Seq.empty
}

case class DeleteStatement private (ds:DataSource, t:Table, predicate:Predicate, constants:Seq[Constant[_]]) extends DeleteStatementBase {
  override protected val wherePredicate: Option[Predicate] = Some(predicate)
}

object DeleteStatement{
  private[simpledb] def apply(ds:DataSource, t:Table, where:Predicate):DeleteStatement = {
    import com.agilogy.simpledb.dsl.ConstantAllocation.allocateExpressionConstants
    val (w,ctx) = allocateExpressionConstants(where)(ConstantAllocationContext.initial)
    DeleteStatement(ds, t,w,ctx.constants)
  }
}

case class Insert1(ds:DataSource, t:Table){
  def values(c: ColumnAssignment[_]*): InsertStatement = InsertStatement(ds, t,c)
}

case class InsertStatement(ds:DataSource, t:Table, values:Seq[ColumnAssignment[_]], constants:Seq[Constant[_]]) extends StatementWithoutResultSetReader {
  private def columnNames = values.map(_.c.name)
  private def insertedValuesSql = values.map(_.value.sql)

  override def sql: String = s"insert into ${t.tableName} (${columnNames.mkString(",")}) values (${insertedValuesSql.mkString(",")})"
  
  def andReadGeneratedKeys[T](f: DbReader[T]): InsertStatementWithResultReader[T] = InsertStatementWithResultReader(this,ActualStatementResultReader(f))

}

object InsertStatement{
  def apply(ds:DataSource, t:Table, values:Seq[ColumnAssignment[_]]):InsertStatement = {
    import com.agilogy.simpledb.dsl.ConstantAllocation.allocateAll
    val (vs,ctx) = allocateAll(values)(_.allocateConstants)(ConstantAllocationContext.initial)
    InsertStatement(ds, t,vs,ctx.constants)
  }
}

case class InsertStatementWithResultReader[RT](stmt:InsertStatement, keyReader:StatementResultReader[RT]) extends StatementWithResultReader[RT] {
  override val ds = stmt.ds
  override def sql: String = stmt.sql

  override def constants: Seq[Constant[_]] = stmt.constants
}
