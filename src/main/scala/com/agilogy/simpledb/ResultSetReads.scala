package com.agilogy.simpledb

import java.sql.ResultSet

import com.agilogy.simpledb.dsl.SelectedElement
import com.agilogy.simpledb.schema.Column

import scala.annotation.tailrec
import scala.collection.immutable.IndexedSeq
import scala.util.control.NonFatal

sealed trait ResultSetReads[RT] {
  def map[RT2](f: (RT) => RT2): MappedResultSetReads[RT, RT2] = new MappedResultSetReads(this, f)

  def read(cursor: Cursor): RT

  def readUsingAliases: ResultSetReads[RT]
}

object ResultSetReads extends ResultSetReadsSyntax {

  private[simpledb] def optional[T](f: ResultSetReads[T]): ResultSetReads[Option[T]] = new OptionalResultSetReads(f)

  private[simpledb] def readUsingAliases[RT](f: (Row) => RT): (Row) => RT = {
    row => f(row.readUsingAliases)
  }
}

case class OptionalResultSetReads[RT](reads:ResultSetReads[RT]) extends ResultSetReads[Option[RT]] {
  override def read(cursor: Cursor): Option[RT] = {
    try {
      Some(reads.read(cursor))
    } catch {
      case n: NullColumnReadException =>
        cursor.next()
        None
    }
  }

  override def readUsingAliases: ResultSetReads[Option[RT]] = this.copy(reads.readUsingAliases)
}

class Row(rs: ResultSet, isReadUsingAliases: Boolean = false) {
  def columnNames: IndexedSeq[String] = {
    val rsmd = rs.getMetaData
    for (i <- 1 to rsmd.getColumnCount) yield rsmd.getColumnName(i)
  }

  def get[_, T](e: SelectedElement[T]): T = {
    e.dbType.get(rs, e.alias)
  }

  def get[_, T](c: Column[T]): T = {
    if (isReadUsingAliases) {
      c.dbType.get(rs, c.alias)
    } else {
      c.dbType.get(rs, c.name)
    }
  }

  def getScalar[T](implicit dbType: NotNullDbType[T]): T = dbType.getScalar(rs)

  def get[T](name: String)(implicit dbType: NotNullDbType[T]): T = dbType.get(rs, name)

  def getOption[T](name: String)(implicit dbType: NotNullDbType[T]): Option[T] = dbType.optional.get(rs, name)

  private[simpledb] def asRowValue = {
    val values = columnNames.map {
      cn =>
        val value = {
          try {
            val v = rs.getObject(cn)
            if (rs.wasNull()) None
            else Some(v)
          } catch {
            case NonFatal(t) =>
              t.printStackTrace()
              Some("?")
          }
        }
        RowValue.columnValue(cn, value)
    }
    RowValue(values)
  }

  def readUsingAliases: Row = new Row(rs, isReadUsingAliases = true)
}

class Cursor(rs: ResultSet) {

  var _done = !rs.next()

  def done: Boolean = _done

  val currentRow = new Row(rs)

  def next(): Unit = {
    require(!done)
    _done = !rs.next()
  }
}

case class SimpleReads[RT](reads: Row => RT) extends ResultSetReads[RT] {

  def join[RT2](implicit r2: ResultSetReads[RT2]): GroupLeftJoinReads[RT, RT2] = GroupLeftJoinReads(this, r2)

  def leftJoin[RT2](implicit r2: ResultSetReads[RT2]): GroupLeftJoinReads[RT, RT2] = GroupLeftJoinReads(this, r2)

  def joinOne[RT2](implicit r2: ResultSetReads[RT2]): JoinReads[RT, RT2] = JoinReads(this, r2)

  def leftJoinOne[RT2](implicit r2: ResultSetReads[RT2]): LeftJoinReads[RT, RT2] = LeftJoinReads(this, r2)

  final def read(cursor: Cursor): RT = {
    val result = readRow(cursor.currentRow)
    cursor.next()
    result
  }

  def readRow(row: Row): RT = reads(row)

  override def readUsingAliases: SimpleReads[RT] = this.copy(ResultSetReads.readUsingAliases(reads))
}

case class JoinReads[T1, T2](left: SimpleReads[T1], right: ResultSetReads[T2]) extends ResultSetReads[(T1, T2)] {

  override def read(cursor: Cursor): (T1, T2) = {
    val leftResult = left.readRow(cursor.currentRow)
    val rightResult = right.read(cursor)
    leftResult -> rightResult
  }

  override def readUsingAliases: JoinReads[T1, T2] = this.copy(left.readUsingAliases, right.readUsingAliases)
}

case class LeftJoinReads[T1, T2](left: SimpleReads[T1], right: ResultSetReads[T2]) extends ResultSetReads[(T1, Option[T2])] {

  override def readUsingAliases: LeftJoinReads[T1, T2] = this.copy(left.readUsingAliases, right.readUsingAliases)

  override def read(cursor: Cursor): (T1, Option[T2]) = {
    val leftResult = left.readRow(cursor.currentRow)
    val rightResult = ResultSetReads.optional(right).read(cursor)
    leftResult -> rightResult

  }

}

case class GroupLeftJoinReads[T1, T2](groupReads: SimpleReads[T1], right: ResultSetReads[T2]) extends ResultSetReads[(T1, Seq[T2])] {

  protected[this] def readGroup(row: Row) = groupReads.readRow(row)

  protected[this] def safeRead[T3](cursor: Cursor)(right: ResultSetReads[T3]): (T1, Seq[T3]) = {
    @tailrec
    def rec(group: T1, elements: Seq[T3]): (T1, Seq[T3]) = {
      val currentGroup = readGroup(cursor.currentRow)
      if (currentGroup == group) {
        val r = try {
          Some(right.read(cursor))
        }
        catch {
          case n: NullColumnReadException =>
            cursor.next()
            None
        }
        val currentElements = elements ++ r
        //        cursor.next()
        if (cursor.done) {
          group -> currentElements
        } else {
          rec(currentGroup, currentElements)
        }
      } else {
        group -> elements
      }
    }

    require(!cursor.done)
    rec(readGroup(cursor.currentRow), Seq.empty)
  }

  def readRow(row: Row): (T1, Seq[T2]) = groupReads.readRow(row) -> Seq.empty

  override def read(cursor: Cursor): (T1, Seq[T2]) = safeRead(cursor)(right)

  override def readUsingAliases: GroupLeftJoinReads[T1, T2] = GroupLeftJoinReads(groupReads.readUsingAliases, right.readUsingAliases)
}

//case class GroupJoinReads[T1, T2](groupReads: SimpleReads[T1], right: SimpleReads[T2]) extends GroupJoinReadsBase[T1, T2] {
//  override def read(cursor: Cursor): (T1, Seq[T2]) = {
//    val (g, elems) = safeRead(cursor)(ResultSetReads.optional(right))
//    g -> elems.flatten
//  }
//
//  override def readUsingAliases: GroupJoinReads[T1, T2] = this.copy(groupReads.readUsingAliases, right.readUsingAliases)
//}

trait ResultSetReadsSyntax {

  /*implicit*/ def reader[T](implicit f: Row => T): SimpleReads[T] = SimpleReads(f)

  /*implicit*/ def scalar[T](implicit dbType: NotNullDbType[T]): SimpleReads[T] = reader((r: Row) => r.getScalar[T])

}

case class MappedResultSetReads[RT, RT2](reads: ResultSetReads[RT], f: RT => RT2) extends ResultSetReads[RT2] {

  override def read(cursor: Cursor): RT2 = f(reads.read(cursor))

  override def readUsingAliases: ResultSetReads[RT2] = this.copy(reads.readUsingAliases)
}