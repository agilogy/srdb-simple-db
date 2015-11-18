package com.agilogy.simpledb

import com.agilogy.simpledb.schema._
import com.agilogy.simpledb.dsl._
import com.agilogy.srdb.tx.Transaction
import com.agilogy.srdb.types.{DbCursorReader, DbReader}

trait DatabaseOperations extends StatementFactory{

  private val getId = reader1(DbLong)

  private def toPredicate(values: Seq[ColumnAssignment[_]]): Predicate = values.foldLeft[Predicate](True) { case (p, ca) => p and (ca.c nullSafeEq ca.value)}

  def insert(into: Table, values: ColumnAssignment[_]*)(implicit tx: Transaction): Unit = {
    val stmt = insertInto(into).values(values: _*).withoutParams
    stmt()
  }

  def insertAndGetKey[RT](into: Table, values: ColumnAssignment[_]*)(readKey: DbReader[RT] = getId)(implicit tx: Transaction): RT = {
    val stmt = insertInto(into).values(values: _*).andReadGeneratedKeys(readKey).withoutParams
    stmt()
  }

  def insertIfNotFound(into: Table, values: ColumnAssignment[_]*)(implicit tx: Transaction): Unit = {
      val find = from(into).where(toPredicate(values)).select(count(values.head.c).as("count"))
      if (find().head == 0) {
        insert(into, values: _*)
      }
  }

  private def finderPredicate(pk: Seq[Column[_]], values: Seq[ColumnAssignment[_]]): Predicate = {
    val res = pk.map {
      pkc =>
        val ca = values.find(_.c.name == pkc.name).get
        ca.asPredicate
    }.foldLeft[Predicate](True)(_ and _)
    res
  }

  def insertIfNotFoundAndGetKey[RT](into: Table, values: ColumnAssignment[_]*)(readKey: DbReader[RT] = getId)(implicit tx: Transaction): RT = {
        val res = select(into, toPredicate(values))(readKey).headOption
        res match{
          case Some(k) => k
          case None => insertAndGetKey(into, values: _*)(readKey)
        }

  }

  def insertOrUpdate(into: Table, values: ColumnAssignment[_]*)(implicit tx: Transaction): Unit = {
    insertOrUpdate(into, into.primaryKey, values :_*)(tx)
  }

  def insertOrUpdate(into:Table, key:Seq[Column[_]],values: ColumnAssignment[_]*)(implicit tx: Transaction): Unit = {
        val valuesToUpdate = values.filterNot(v => key.contains(v.c))
        val updated = update(into, finderPredicate(key, values), valuesToUpdate: _*)
        if (updated == 0) insert(into, values: _*)
  }

  def insertOrUpdateAndGetKey[RT](into: Table, values: ColumnAssignment[_]*)(readKey: DbReader[RT] = getId)(implicit tx: Transaction): RT = {
    insertOrUpdateAndGetKey(into,into.primaryKey,values :_*)(readKey)
  }

  def insertOrUpdateAndGetKey[RT](into: Table, key:Seq[Column[_]], values: ColumnAssignment[_]*)(readKey: DbReader[RT])(implicit tx: Transaction): RT = {
        val valuesToUpdate = values.filterNot(v => key.contains(v.c))
        val where = finderPredicate(key, values)
        val updated = update(into, where, valuesToUpdate :_*)
        if (updated == 0) {
          insertAndGetKey(into, values: _*)(readKey)
        } else {
          select(into, where)(readKey).head
        }
  }


  def update(table: Table, where: Predicate, set: ColumnAssignment[_]*)(implicit tx: Transaction): Int = {
    require(set.nonEmpty,"Can't update with an empty set of changes")
    val stmt = dsl.update(table).set(set: _*).where(where).withoutParams
    stmt()
  }

  def delete(table: Table, where: Predicate = True)(implicit tx: Transaction): Int = {
    val stmt = dsl.delete(table).where(where).withoutParams
    stmt()
  }

  def select[RT](from: Relation, where: Predicate, groupBy: Seq[Column[_]] = Seq.empty, orderBy: Seq[OrderByCriterion] = Seq.empty, columns: Seq[Column[_]] = Seq.empty)(reads: DbCursorReader[RT])(implicit tx: Transaction): Seq[RT] = {

    val actualColumns = if (columns.isEmpty) from.* else columns
    val q = dsl.from(from).where(where).groupBy(groupBy: _*).orderBy(orderBy: _*).select(actualColumns)(reads)
    q()(tx)
  }

}

object Ops extends DatabaseOperations
