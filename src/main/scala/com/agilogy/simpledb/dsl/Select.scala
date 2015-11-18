package com.agilogy.simpledb.dsl

import com.agilogy.simpledb.schema.Column
import com.agilogy.simpledb._

//case class SelectReads[RT](columns: Seq[Column[_]])(f: (Row) => RT) extends NotGroupedReadsBase[RT] {
//  override def readRow(row: Row): RT = f(row)
//
//  override def readUsingAliases: SelectReads[RT] = this.copy(columns)(ResultSetReads.readUsingAliases(f))
//}

trait SelectedElement[T] extends Expression[T]{
  val alias: String
  val dbType: DbType[T]
}

case class SelectedExpression[T](expression: Expression[T], alias: String)(implicit r: DbType[T]) extends SelectedElement[T] {
  val dbType: DbType[T] = r
  val sql: String = expression.sql
  private[simpledb] val parameters: Seq[Param[_]] = expression.parameters

  override private[simpledb] val allocateConstants:ConstantAllocation[SelectedExpression[T]] = expression.allocateConstants.map(e => this.copy(expression = e))
}