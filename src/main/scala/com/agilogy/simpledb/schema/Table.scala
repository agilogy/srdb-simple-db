package com.agilogy.simpledb.schema

import com.agilogy.simpledb.dsl._
import com.agilogy.srdb.types._

class PrimaryKey[T](val columns: Column[_]*)

case class PrimaryKey1[T1](c1: Column[T1]) extends PrimaryKey[T1](c1)

case class PrimaryKey2[T1, T2](c1: Column[T1], c2: Column[T1]) extends PrimaryKey[(T1, T2)](c1, c2)

case class PrimaryKey3[T1, T2, T3](c1: Column[T1], c2: Column[T2], c3: Column[T3]) extends PrimaryKey[(T1, T2, T3)](c1, c2, c3)

abstract class Table {

  val alias: String
  val tableName: String

  private var columns = scala.collection.mutable.ArrayBuffer.empty[Column[_]]

  private[this] def addColumn[T](c: NotNullColumn[T]): NotNullColumn[T] = {
    columns += c
    c
  }

  private[this] def addColumn[T](c: OptionalColumn[T]): OptionalColumn[T] = {
    columns += c
    c
  }

  protected[this] def optional[T](name: String)(implicit columnType: ColumnType[T]): OptionalColumn[T] = {
    addColumn(Column.optional(this, name))
  }

  protected[this] def notNull[T](name: String)(implicit columnType: ColumnType[T]): NotNullColumn[T] = {
    addColumn(Column.notNull(this, name))
  }

  protected[this] def foreignKey[T](c: Column[T], references: Column[T]) =
    ForeignKey1(c, references)

  def * : Seq[Column[_]] = columns.toSeq

  //  def reader[RT](f: (Row) => RT): SimpleReads[RT] = simpledb.reader(f)

  val primaryKey: Seq[Column[_]]
}

trait Column[T] extends SelectedElement[T] {

  val table: Table

  val name: String

  val writer: AtomicDbWriter[T]

  override lazy val sql: String = completeName

  protected[simpledb] def tableAlias: Option[String] = Some(table.alias)

  private[schema] def at[TT2 <: Table](t2: TT2): Column[T]

  private[simpledb] def completeName: String = table.alias + "." + name

  val alias: Option[String] = None

  override private[simpledb] val parameters: Seq[Param[_]] = Seq.empty

  type self = Column[T]

  override private[simpledb] val allocateConstants = ConstantAllocation.empty(this)

  def :=(e: Expression[T]): ColumnAssignment[T] = ColumnAssignment(this, e)
}

case class NotNullColumn[T] private[schema] (table: Table, name: String)(implicit val columnType: ColumnType[T]) extends Column[T] {

  private[schema] def at[TT2 <: Table](t2: TT2): NotNullColumn[T] = NotNullColumn(t2, name)

  def opt: OptionalColumn[T] = OptionalColumn(table, name)(columnType)

  override lazy val positionalReader: AtomicNotNullPositionalDbReader[T] = reader1[T](columnType)

  lazy val writer: AtomicNotNullDbWriter[T] = writer1[T](columnType)
}

case class OptionalColumn[T] private[schema] (table: Table, name: String)(implicit val columnType: ColumnType[T]) extends Column[Option[T]] {

  private[schema] def at[TT2 <: Table](t2: TT2): OptionalColumn[T] = OptionalColumn(t2, name)

  lazy val writer: AtomicOptionalDbWriter[T] = writer1[T](columnType).optional

  override lazy val positionalReader: AtomicOptionalPositionalDbReader[T] = reader1[T](columnType).optional
}

object Column {

  object UnknownTable extends Table {
    override lazy val tableName: String = throw new IllegalArgumentException("This table name should not be used")
    override val alias: String = ""
    override val primaryKey: Seq[Column[_]] = Seq.empty
  }

  private[simpledb] def unknown(name: String): Column[Option[Any]] = {
    val unknownColumnType: ColumnType[Any] = ColumnType.from[Any](
      v => v.asInstanceOf[AnyRef],
      (ps, pos, v) => ps.setObject(pos, v),
      (rs, pos) => rs.getObject(pos),
      (rs, name) => rs.getObject(name),
      JdbcType.Other
    )
    OptionalColumn[Any](UnknownTable, name)(unknownColumnType)
  }

  private[schema] def optional[TT <: Table, T](table: TT, name: String)(implicit columnType: ColumnType[T]): OptionalColumn[T] = OptionalColumn(table, name)(columnType)

  private[schema] def notNull[TT <: Table, T](table: TT, name: String)(implicit columnType: ColumnType[T]): NotNullColumn[T] = NotNullColumn(table, name)(columnType)

}
