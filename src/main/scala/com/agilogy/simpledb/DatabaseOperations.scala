package com.agilogy.simpledb

import com.agilogy.simpledb.schema._
import com.agilogy.simpledb.dsl._

trait DatabaseOperations {

  val db: Database

  import com.agilogy.simpledb.dsl.Syntax._

  private val getId = (r: Row) => r.get[Long]("id")

  private def toPredicate(values: Seq[ColumnAssignment[_]]): Predicate = values.foldLeft[Predicate](True) { case (p, ca) => p and (ca.c nullSafeEq ca.value)}

  private def cvsToPredicate(values: Seq[ColumnAssignment[_]]): Predicate = values.foldLeft[Predicate](True) { case (p, ca) => p and (ca.c nullSafeEq ca.value)}

  def insert(into: Table, values: ColumnAssignment[_]*)(implicit config: TransactionConfig): Unit = {
    val stmt = db.insertInto(into).values(values: _*).withoutParams
    stmt()
  }

  def insertAndGetKey[RT](into: Table, values: ColumnAssignment[_]*)(readKey: Row => RT = getId)(implicit config: TransactionConfig): RT = {
    val stmt = db.insertInto(into).values(values: _*).andReadGeneratedKeys(readKey).withoutParams
    stmt()
  }

  def insertIfNotFound(into: Table, values: ColumnAssignment[_]*)(implicit config: TransactionConfig): Unit = {
    db.inTransaction {
      implicit tx =>
        if (select(into, toPredicate(values))(reader(r => true)).headOption.isEmpty) {
          insert(into, values: _*)
        }
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

  def insertIfNotFoundAndGetKey[RT](into: Table, values: ColumnAssignment[_]*)(readKey: Row => RT = getId)(implicit config: TransactionConfig): RT = {
    db.inTransaction {
      implicit tx =>
        val res = select(into, toPredicate(values))(reader(readKey)).headOption
        res match{
          case Some(k) => k
          case None => insertAndGetKey(into, values: _*)(readKey)
        }
    }
  }

  def insertOrUpdate(into: Table, values: ColumnAssignment[_]*)(implicit config: TransactionConfig): Unit = {
    insertOrUpdate(into, into.primaryKey, values :_*)(config)
  }

  def insertOrUpdate(into:Table, key:Seq[Column[_]],values: ColumnAssignment[_]*)(implicit config: TransactionConfig): Unit = {
    db.inTransaction {
      implicit tx =>
        val valuesToUpdate = values.filterNot(v => key.contains(v.c))
        val updated = update(into, finderPredicate(key, values), valuesToUpdate: _*)
        if (updated == 0) insert(into, values: _*)
    }

  }

  def insertOrUpdateAndGetKey[RT](into: Table, values: ColumnAssignment[_]*)(readKey: Row => RT = getId)(implicit config: TransactionConfig): RT = {
    insertOrUpdateAndGetKey(into,into.primaryKey,values :_*)(readKey)
  }

  def insertOrUpdateAndGetKey[RT](into: Table, key:Seq[Column[_]], values: ColumnAssignment[_]*)(readKey: Row => RT = getId)(implicit config: TransactionConfig): RT = {
    db.inTransaction {
      implicit tx =>
        val valuesToUpdate = values.filterNot(v => key.contains(v.c))
        val where = finderPredicate(key, values)
        val updated = update(into, where, valuesToUpdate :_*)
        if (updated == 0) {
          insertAndGetKey(into, values: _*)(readKey)
        } else {
          select(into, where)(reader(readKey)).head
        }
    }
  }


  def update(table: Table, where: Predicate, set: ColumnAssignment[_]*)(implicit config: TransactionConfig): Int = {
    require(set.nonEmpty,"Can't update with an empty set of changes")
    val stmt = db.update(table).set(set: _*).where(where).withoutParams
    stmt()
  }

  def delete(table: Table, where: Predicate = True)(implicit config: TransactionConfig): Int = {
    val stmt = db.delete(table).where(where).withoutParams
    stmt()
  }

  def select[RT](from: Relation, where: Predicate, groupBy: Seq[Column[_]] = Seq.empty, orderBy: Seq[OrderByCriterion] = Seq.empty, columns: Seq[Column[_]] = Seq.empty)(reads: ResultSetReads[RT])(implicit config: TransactionConfig): Seq[RT] = {

    val actualColumns = if (columns.isEmpty) from.* else columns
    val q = db.from(from).where(where).groupBy(groupBy: _*).orderBy(orderBy: _*).select(actualColumns)(reads)
    q()(config)
  }

}
