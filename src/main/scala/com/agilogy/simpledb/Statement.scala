package com.agilogy.simpledb

import javax.sql.DataSource

import com.agilogy.simpledb.Database.measure
import com.agilogy.simpledb.Utilities._

trait DatabaseStatementFactory {
  val ds: DataSource

  def createStatement(query:String) = new TextRawStatement[Int](ds, query)(NoStatementResultReader)

  def createStatement[RT](query:String, keyReader: StatementResultReader[RT]) = new TextRawStatement[RT](ds, query)(keyReader)

}

sealed trait StatementResultReader[T]
case object NoStatementResultReader extends StatementResultReader[Int]
case class ActualStatementResultReader[T](reader:Row => T) extends StatementResultReader[T]

trait RawStatement[RT] extends StatementWithParams[RT] {
  val ds:DataSource
  def sql:String
  def preAssignedParameters:Seq[ParameterValue[_]]
  val keyReader: StatementResultReader[RT]

  private[this] def dbArgs(dbWrites: Seq[DbWrites[_]], args: Seq[Any]): Seq[ParameterValue[_]] = args.zip(dbWrites).zipWithIndex.map {
    case ((pv, dbType), idx) => Parameter(idx)(dbType).asInstanceOf[Parameter[Any]].set(pv)
  }

  def apply(args: PositionalArgument[_]*)(implicit tx: TransactionConfig): RT = apply(args.map(_.dbWrites), args.map(_.value))

  private[simpledb] def applyWithParams(arguments: ParameterValue[_]*)(implicit tx: TransactionConfig): RT = measure(sql) {
    val actualArgs = arguments ++ preAssignedParameters
//    println("S " + sql + (if(actualArgs.isEmpty) "" else  "\n  " + actualArgs.map(pv => pv.parameter.name + " = " + pv.value).mkString(",") ))
    val params = actualArgs.map(_.parameter)
    checkNamedParameters(sql, params.map(_.name).toSet)
    logger.debug( """%s <- %s""".format(sql, actualArgs.mkString(", ")))
    val (jdbcQuery, orderedArgs) = translateNamedParameters(sql, params, actualArgs)
    keyReader match {
      case ActualStatementResultReader(r) => TransactionController.inTransaction(ds)(tx => executeUpdateGeneratedKeys(tx.conn, jdbcQuery, orderedArgs, Some(r)))
      case _ => TransactionController.inTransaction(ds)(tx => executeUpdate(tx.conn, jdbcQuery, orderedArgs).asInstanceOf[RT])
    }
  }

  def apply(dbWrites: Seq[DbWrites[_]], args: Seq[Any])(implicit tx: TransactionConfig): RT = this.applyWithParams(dbArgs(dbWrites, args): _*)

  def withoutParams: StatementByPosition0[RT] = new StatementByPosition0(this)

  override val self: RawStatement[RT] = this
}

case class TextRawStatement[RT](ds: DataSource, sql: String)(val keyReader: StatementResultReader[RT])
extends RawStatement[RT]{
  override val preAssignedParameters: Seq[ParameterValue[_]] = Seq.empty
}

private[simpledb] class StatementByPositionBase[RT](stm: RawStatement[RT], parameterWrites: DbWrites[_]*) {
  val params = (0 to parameterWrites.length - 1).zip(parameterWrites).map {
    np =>
      Parameter(np._1)(np._2)
  }
  checkNamedParameters(stm.sql, stm.preAssignedParameters.map(_.parameter.name).toSet ++ params.map(_.name).toSet)

  protected def execute(args: PositionalArgument[_]*)(implicit tx: TransactionConfig): RT = stm.apply(args :_*)

}

class StatementByPosition0[RT](stmt: RawStatement[RT])
  extends StatementByPositionBase[RT](stmt) {
  def apply()(implicit txConfig: TransactionConfig): RT = stmt.apply()(txConfig)
}

