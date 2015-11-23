package com.agilogy.simpledb

import com.agilogy.simpledb.Utilities._
import com.agilogy.srdb.tx.Transaction
import com.agilogy.srdb.types.{ DbReader, AtomicDbWriter }
import com.agilogy.srdb.Srdb

trait StatementFactory {

  def createStatement(query: String): TextRawStatement[Int] = new TextRawStatement[Int](query)(NoStatementResultReader)

  def createStatement[RT](query: String, keyReader: StatementResultReader[RT]): TextRawStatement[RT] = new TextRawStatement[RT](query)(keyReader)

}

sealed trait StatementResultReader[T]
case object NoStatementResultReader extends StatementResultReader[Int]
case class ActualStatementResultReader[T](reader: DbReader[T]) extends StatementResultReader[T]

trait RawStatement[RT] extends StatementWithParams[RT] {
  def sql: String
  def preAssignedParameters: Seq[ParameterValue[_]]
  val keyReader: StatementResultReader[RT]

  private[this] def dbArgs(dbWrites: Seq[AtomicDbWriter[_]], args: Seq[Any]): Seq[ParameterValue[_]] = args.zip(dbWrites).zipWithIndex.map {
    case ((pv, writer), idx) => Parameter(idx)(writer).asInstanceOf[Parameter[Any]].set(pv)
  }

  def apply(args: PositionalArgument[_]*)(implicit tx: Transaction): RT = apply(args.map(_.dbWrites), args.map(_.value))

  private[simpledb] def applyWithParams(arguments: ParameterValue[_]*)(implicit tx: Transaction): RT = {
    val actualArgs = arguments ++ preAssignedParameters
    checkNamedParameters(sql, actualArgs.map(_.parameter).map(_.name).toSet)
    val (jdbcQuery, orderedArgs) = translateNamedParameters(sql, actualArgs)
    keyReader match {
      case ActualStatementResultReader(r) => Srdb.updateGeneratedKeys(jdbcQuery)(r)(tx.conn, orderedArgs.map(_.toArg))
      case _ => Srdb.update(jdbcQuery)(tx.conn, orderedArgs.map(_.toArg)).asInstanceOf[RT]
    }
  }

  def apply(dbWrites: Seq[AtomicDbWriter[_]], args: Seq[Any])(implicit tx: Transaction): RT = this.applyWithParams(dbArgs(dbWrites, args): _*)

  def withoutParams: StatementByPosition0[RT] = new StatementByPosition0(this)

  override val self: RawStatement[RT] = this
}

case class TextRawStatement[RT](sql: String)(val keyReader: StatementResultReader[RT])
    extends RawStatement[RT] {
  override val preAssignedParameters: Seq[ParameterValue[_]] = Seq.empty
}

private[simpledb] class StatementByPositionBase[RT](stm: RawStatement[RT], parameterWrites: AtomicDbWriter[_]*) {
  val params = (0 to parameterWrites.length - 1).zip(parameterWrites).map {
    np =>
      Parameter(np._1)(np._2)
  }
  checkNamedParameters(stm.sql, stm.preAssignedParameters.map(_.parameter.name).toSet ++ params.map(_.name).toSet)

  protected def execute(args: PositionalArgument[_]*)(implicit tx: Transaction): RT = stm.apply(args: _*)

}

class StatementByPosition0[RT](stmt: RawStatement[RT])
    extends StatementByPositionBase[RT](stmt) {
  def apply()(implicit tx: Transaction): RT = stmt.apply()(tx)
}

