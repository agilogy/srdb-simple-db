package com.agilogy.simpledb

import com.agilogy.simpledb.Utilities._
import com.agilogy.srdb.Srdb
import com.agilogy.srdb.{ FetchSize, DefaultFetchSize }
import com.agilogy.srdb.tx.{ TransactionController, Transaction }
import com.agilogy.srdb.types.{ DbCursorReader, AtomicDbWriter }
import com.agilogy.srdb.types._

import scala.collection.mutable.ListBuffer

trait QueryFactory {

  def createQuery[RT](query: String)(implicit as: DbCursorReader[RT]): TextQuery[RT] = new TextQuery[RT](query, as)

}

trait Query[RT] extends WithParams[RT] {
  def sql: String
  private[simpledb] val preAssignedParameters: Seq[ParameterValue[_]]
  private[simpledb] val reads: DbCursorReader[RT]

  private[this] def getArguments(args: Seq[PositionalArgument[_]]): Seq[ParameterValue[_]] = args.zipWithIndex.map {
    case (pa, idx) =>
      //TODO: Fix redundant cast
      Parameter(idx)(pa.dbWrites).asInstanceOf[Parameter[Any]].set(pa.value)
  } ++ preAssignedParameters

  def apply(args: PositionalArgument[_]*)(implicit tx: Transaction): Seq[RT] = {
    new ReadyQuery[RT](sql, getArguments(args), reads).execute(tx)
  }

  def stream(args: PositionalArgument[_]*)(implicit txController: TransactionController): ReadyQueryStream[RT] = {
    new ReadyQuery[RT](sql, getArguments(args), reads).stream
  }

  def withoutParams: Query0[RT] = new Query0(this)

  def map[RT2](f: RT => RT2): Query[RT2]

}

case class TextQuery[RT](sql: String, reads: DbCursorReader[RT], preAssignedParameters: Seq[ParameterValue[_]] = Seq.empty)
    extends Query[RT] {
  override def map[RT2](f: (RT) => RT2): TextQuery[RT2] = this.copy(reads = reads.map(f))
}

private[simpledb] abstract class QueryBase[RT](q: Query[RT], dbTypes: AtomicDbWriter[_]*) {

  private[this] def parameterNames: Seq[String] = dbTypes.zipWithIndex.map(p => p._2 -> p._1).map {
    pn => pn._1.toString
  } ++ q.preAssignedParameters.map(_.parameter.name)

  checkNamedParameters(q.sql, parameterNames.toSet)

  private[this] def getArguments(args: Seq[Any]) = dbTypes.zip(args).map {
    case (dbWrite, arg) => PositionalArgument(arg, dbWrite.asInstanceOf[AtomicDbWriter[Any]])
  }

  protected[this] def execute(args: Any*)(implicit tx: Transaction): Seq[RT] = q.apply(getArguments(args): _*)(tx)

  protected[this] def stream(args: Any*)(implicit txController: TransactionController): ReadyQueryStream[RT] = q.stream(getArguments(args): _*)

}

private[simpledb] case class ReadyQuery[RT] private[simpledb] (query: String, args: Seq[ParameterValue[_]], protected val reads: DbCursorReader[RT]) {

  private[this] val (jdbcQuery, orderedArguments) = translateNamedParameters(query, args)

  private[simpledb] def foreach(f: (RT) => Unit)(implicit tx: Transaction, fetchSize: FetchSize): Unit = {
    val s = Srdb.select(jdbcQuery, fetchSize)(_.foreach(f)(reads))
    s(tx.conn, orderedArguments.map(_.toArg))
  }

  private[simpledb] def execute(implicit tx: Transaction): Seq[RT] = {
    val res = new ListBuffer[RT]
    foreach(e => res.append(e))(tx, DefaultFetchSize)
    res.toList
  }

  private[simpledb] def stream(implicit txController: TransactionController): ReadyQueryStream[RT] = new ReadyQueryStream[RT](this)
}

class Query0[RT](q: Query[RT]) extends QueryBase[RT](q) {

  def apply()(implicit tx: Transaction): Seq[RT] = super.execute()(tx)
  def stream()(implicit txController: TransactionController): ReadyQueryStream[RT] = super.stream()

}

