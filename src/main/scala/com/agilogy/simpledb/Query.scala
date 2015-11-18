package com.agilogy.simpledb

import javax.sql.DataSource

import com.agilogy.simpledb.Utilities._

import scala.collection.mutable.ListBuffer

trait FetchSize
case class LimitedFetchSize(rows:Int) extends FetchSize
case object DefaultFetchSize extends FetchSize

trait DatabaseQueryFactory {

  val ds: DataSource

  def createQuery[RT](query: String)(implicit as: ResultSetReads[RT]) = new TextQuery[RT](ds, query, as)

}

trait Query[RT]  extends WithParams[RT] {
  private[simpledb] val ds:DataSource
  val sql:String
  private[simpledb] val preAssignedParameters:Seq[ParameterValue[_]]
  private[simpledb] val reads: ResultSetReads[RT]

  private[this] def getArguments(args:Seq[PositionalArgument[_]]): Seq[ParameterValue[_]] = args.zipWithIndex.map{
    case (pa,idx) => Parameter(idx)(pa.dbWrites).asInstanceOf[Parameter[Any]].set(pa.value)
  } ++ preAssignedParameters

  def apply(args: PositionalArgument[_] *)(implicit txConfig:TransactionConfig):Seq[RT] = {
    new ReadyQuery[RT](ds, sql, getArguments(args), reads).execute(txConfig)
  }

  def stream(args: PositionalArgument[_] *): ReadyQueryStream[RT] = {
    new ReadyQuery[RT](ds, sql, getArguments(args), reads).stream
  }

  def withoutParams: Query0[RT] = new Query0(this)

  def map[RT2](f: RT => RT2): Query[RT2]

}

case class TextQuery[RT](ds: DataSource, sql: String, reads: ResultSetReads[RT], preAssignedParameters:Seq[ParameterValue[_]] = Seq.empty)
extends Query[RT]{
  override def map[RT2](f: (RT) => RT2): TextQuery[RT2] = this.copy(reads = reads.map(f))
}


private[simpledb] abstract class QueryBase[RT](q: Query[RT], dbTypes: DbWrites[_]*) {

  private[this] def parameterNames: Seq[String] = dbTypes.zipWithIndex.map(p => p._2 -> p._1).map {
    pn => pn._1.toString
  } ++ q.preAssignedParameters.map(_.parameter.name)

  checkNamedParameters(q.sql, parameterNames.toSet)

  private[this] def getArguments(args:Seq[Any]) = dbTypes.zip(args).map{
    case (dbWrite,arg) => PositionalArgument(arg,dbWrite.asInstanceOf[DbWrites[Any]])
  }

  protected[this] def execute(args: Any*)(implicit txConfig:TransactionConfig): Seq[RT] = q.apply(getArguments(args) :_*)(txConfig)

  protected[this] def stream(args: Any*): ReadyQueryStream[RT] = q.stream(getArguments(args) :_*)

}

private[simpledb] case class ReadyQuery[RT] private[simpledb](ds: DataSource, query: String, args: Seq[ParameterValue[_]], protected val reads: ResultSetReads[RT]) {

  if (!args.map(_.parameter.name).forall(k => """[0-9]+""".matches(k))) {
    logger.debug("%s %s".format(query, args))
  }
  private[this] val (jdbcQuery, orderedArguments) = translateNamedParameters(query, args.map(_.parameter), args)


  private[simpledb] def foreach(f: (RT) => Unit)(implicit config: TransactionConfig, fetchSize:FetchSize): Unit = {
    TransactionController.inTransaction(ds)(tx => select(tx.conn, jdbcQuery, orderedArguments, reads)(f))
  }

  private[simpledb] def execute(implicit config: TransactionConfig): Seq[RT] = {
    val res = new ListBuffer[RT]
    foreach(e => res.append(e))(config,DefaultFetchSize)
    res.toList
  }

  private[simpledb] def stream: ReadyQueryStream[RT] = new ReadyQueryStream[RT](this)
}

class Query0[RT](q: Query[RT]) extends QueryBase[RT](q) {

  def apply()(implicit txConfig:TransactionConfig): Seq[RT] = super.execute()(txConfig)
  def stream(): ReadyQueryStream[RT] = super.stream()

}

