package com.agilogy.simpledb

import java.sql.PreparedStatement

case class Parameter[T](name: String)(implicit val dbWrites: DbWrites[T]) {
  def set(value: T): ParameterValue[T] = SimpleParameterValue(this, value)
}

object Parameter{
  def apply[T](i:Int)(implicit dbWrites: DbWrites[T]):Parameter[T] = Parameter(i.toString)(dbWrites)
}

trait ParameterValue[T] {
  val parameter: Parameter[T]
  val value: T

  private[simpledb] def set(s: PreparedStatement, pos: Int) = {
    parameter.dbWrites.set(s, pos, value)
  }

  private[simpledb] def getJdbcPlaceHolder = parameter.dbWrites.getJdbcPlaceHolderFor(value)

}

case class SimpleParameterValue[T] private[simpledb](parameter: Parameter[T], value: T) extends ParameterValue[T]

//case class ColumnValue[T] private[simpledb](column: Column[T], value: T) extends ParameterValue[T] {
//  override val parameter: Parameter[T] = new Parameter[T](column.name)(column.dbType)
//  def asAssignment = ColumnAssignment(column,Syntax.const(value)(column.dbType))
//}

case class PositionalArgument[T](value:T, dbWrites:DbWrites[T])

object PositionalArgument{
  implicit def fromValue[T](v:T)(implicit dbWrites:DbWrites[T]) = PositionalArgument(v,dbWrites)
}

