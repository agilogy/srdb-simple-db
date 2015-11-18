package com.agilogy.simpledb

import com.agilogy.srdb.Argument
import com.agilogy.srdb.types.AtomicDbWriter

case class Parameter[T](name: String)(implicit val dbWrites: AtomicDbWriter[T]) {
  def set(value: T): ParameterValue[T] = ParameterValue(this, value)
}

object Parameter{
  def apply[T](i:Int)(implicit dbWrites: AtomicDbWriter[T]):Parameter[T] = Parameter(i.toString)(dbWrites)
}

case class ParameterValue[T] private[simpledb](parameter: Parameter[T], value: T){

  private[simpledb] def getJdbcPlaceHolder:String = parameter.dbWrites match {
    case i:InClauseValuesDbWriter[_] =>  InClauseValuesDbWriter.getJdbcPlaceholderFor(value.asInstanceOf[Iterable[_]].size)
    case _ => "?"
  }

  def toArg: Argument = (ps,pos) => parameter.dbWrites.set(ps,pos,value)

}

case class PositionalArgument[T](value:T, dbWrites:AtomicDbWriter[T])

object PositionalArgument{
  implicit def fromValue[T](v:T)(implicit dbWrites:AtomicDbWriter[T]): PositionalArgument[T] = PositionalArgument(v,dbWrites)
}

