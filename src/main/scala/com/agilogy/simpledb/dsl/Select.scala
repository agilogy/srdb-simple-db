package com.agilogy.simpledb.dsl

import com.agilogy.simpledb.schema.Column
import com.agilogy.simpledb._
import com.agilogy.srdb.types.{AtomicNamedDbReader, AtomicPositionalDbReader}

//case class SelectReads[RT](columns: Seq[Column[_]])(f: (Row) => RT) extends NotGroupedReadsBase[RT] {
//  override def readRow(row: Row): RT = f(row)
//
//  override def readUsingAliases: SelectReads[RT] = this.copy(columns)(ResultSetReads.readUsingAliases(f))
//}

trait SelectedElement[T] extends Expression[T]{
  val name: String
  val positionalReader: AtomicPositionalDbReader[T]
  def reader: AtomicNamedDbReader[T] = positionalReader.as(name)
  val alias:Option[String]
}

case class SelectedExpression[T](expression: Expression[T], name: String)(implicit r: AtomicPositionalDbReader[T]) extends SelectedElement[T] {
  override lazy val sql: String = expression.sql
  private[simpledb] val parameters: Seq[Param[_]] = expression.parameters

  override private[simpledb] val allocateConstants:ConstantAllocation[SelectedExpression[T]] = expression.allocateConstants.map(e => this.copy(expression = e))

  override lazy val positionalReader: AtomicPositionalDbReader[T] = r
  override val alias: Option[String] = Some(name)
}