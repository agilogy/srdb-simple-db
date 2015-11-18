package com.agilogy.simpledb

import com.agilogy.simpledb.schema.Column

import scala.collection.MapLike

case class ColumnValue[T](column:Column[T], value:T)

case class RowValue(columnValues: Seq[ColumnValue[_]]) extends Map[String, Any] with MapLike[String, Any, RowValue] {

  override def empty = RowValue(Seq.empty)

  def get(key: String) = columnValues.find(c => c.column.name == key).map(_.value)

  def +[B1 >: Any](kv: (String, B1)) = throw new UnsupportedOperationException("Not implemented")

  def -(key: String) = RowValue(columnValues.filterNot(_.column.name == key))

  def iterator = columnValues.map(cv => cv.column.name -> cv.value).iterator

}

object RowValue {

  private[simpledb] def columnValue(name: String, value: Option[Any]):ColumnValue[Option[Any]] = ColumnValue(Column.unknown(name), value)
}
