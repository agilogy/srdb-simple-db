package com.agilogy.simpledb

import java.sql.SQLException


abstract class DbException extends RuntimeException {
  val sql: String
  val msg: String
  val cause: Throwable
  val args: Seq[ParameterValue[_]]

  override def getMessage: String = {
    val res = new StringBuilder(msg)
    res.append("\n  SQL: " + sql)
    if (args.nonEmpty) res.append("\n  Arguments: " + args.map(pv => s"${pv.parameter.name} = ${pv.value}").mkString(", "))
    if (cause != null && cause != this && cause.getMessage != null) res.append("\n  Cause: " + cause.getMessage.replaceAll("\n","\n  "))
    res.toString()
  }

  override def getCause: Throwable = cause

}

object DbException {
  def toString(parameters: Seq[ParameterValue[_]]): String = parameters.map(pv => s"${pv.parameter.name} = ${pv.value}").mkString(", ")
}

case class PrepareStatementException(sql: String, cause: Throwable) extends DbException {
  override val msg: String = "Error preparing statement"
  override val args: Seq[ParameterValue[_]] = Seq.empty
}

abstract class ExecuteUpdateException extends DbException

case class BaseExecuteUpdateException(sql: String, args: Seq[ParameterValue[_]], cause: Throwable) extends ExecuteUpdateException {
  val msg = s"Error executing statement"
}

case class UniqueKeyViolationException(constraintName: String, key: Seq[String], sql: String, args: Seq[ParameterValue[_]], cause: Throwable) extends ExecuteUpdateException {
  val msg = s"Duplicate key violated executing statement"
}

object UniqueKeyViolationException {

  private val DuplicateKey = """(?s)ERROR:\s+duplicate key value violates unique constraint \"(.+)\".+\s+Detail: Key \((.+)\)=\((.+)\) already exists\.""".r

  private val Quoted = """"(.+)"""".r

  private[simpledb] def unapply(t: Throwable): Option[UniqueKeyViolationException] = t match {
    case sqle: SQLException if sqle.getSQLState == "23505" =>
      sqle.getMessage match {
        case DuplicateKey(cn, k, _) =>
          val key = k.split(", ?").map {
            case Quoted(s) => s
            case ck => ck
          }
          Some(UniqueKeyViolationException(cn, key, "", Seq.empty, sqle))
        case _ => None
      }
    case _ => None

  }
}

case class ForeignKeyViolationException(violationType: ForeignKeyViolation, constraintName:String, key:Seq[String], referencingTable:String, referredTable:String, sql: String, args: Seq[ParameterValue[_]], cause: Throwable) extends ExecuteUpdateException {
  override val msg: String = "Foreign key violated executing statement"
}

sealed trait ForeignKeyViolation
case object ReferencedKeyNotPresent extends ForeignKeyViolation
case object KeyStillReferenced extends ForeignKeyViolation


object ForeignKeyViolationException{
  private val ForeignKeyNotPresent = ("(?s)ERROR: insert or update on table \"(.+)\" violates foreign key constraint \"(.+)\".+\\s+" +
    "Detail: Key \\((.+)\\)=\\((.+)\\) is not present in table \"(.+)\"\\.").r

  private val ForeignKeyStillReferenced = ("(?s)ERROR: update or delete on table \"(.+)\" violates foreign key constraint \"(.+)\" on table \"(.+)\".+\\s+"
  + "Detail: Key \\((.+)\\)=\\((.+)\\) is still referenced from table \".+\"\\.").r

  private val Quoted = """"(.+)"""".r

  def unapply(t: Throwable): Option[ForeignKeyViolationException] = t match {
    case sqle: SQLException =>
      sqle.getMessage match {
        case ForeignKeyNotPresent(st, cn, k, _, dt) =>
          val key = k.split(", ?").map {
            case Quoted(s) => s
            case ck => ck
          }
          Some(ForeignKeyViolationException(ReferencedKeyNotPresent, cn, key, st, dt, "", Seq.empty, sqle))
        case ForeignKeyStillReferenced(dt, cn, st, k, _) =>
          val key = k.split(", ?").map {
            case Quoted(s) => s
            case ck => ck
          }
          Some(ForeignKeyViolationException(KeyStillReferenced, cn, key, st, dt, "", Seq.empty, sqle))
        case _ => None
      }
    case _ => None

  }
}

case class ExecuteQueryException(sql: String, args: Seq[ParameterValue[_]], cause: Throwable) extends DbException {
  val msg = "Error executing query"
}

case class RowMappingException(sql: String, row: RowValue, cause: Throwable) extends DbException {
  val msg = "Error mapping row %s".format(row)
  override val args: Seq[ParameterValue[_]] = Seq.empty
}

case class ColumnReadException(sql: String, row: RowValue, column: String, dbReads: DbReads[_], optional: Boolean, cause: Throwable) extends DbException {
  val msg = "Error reading %scolumn \"%s\" as %s in %s".format(if (optional) "" else "not null ", column, dbReads.toString, row)
  override val args: Seq[ParameterValue[_]] = Seq.empty
}

case class NullColumnReadException(sql: String, row: RowValue, column: String, dbType: DbType[_]) extends DbException {
  val msg = "Error reading supposedly not null column \"%s\" cause the value is null in %s".format(column, row)
  val cause = null
  override val args: Seq[ParameterValue[_]] = Seq.empty
}

