package com.agilogy.simpledb

import java.sql.{Connection, PreparedStatement, ResultSet, SQLException, Statement => JdbcStatement}

import com.agilogy.utils.Logger

private[simpledb] object Utilities {
  val logger = Logger.getLogger(this)

  //TODO: This is dangerous. Keep an eye on better ways to detect parameters
  //TODO: We try to avoid detecting '2014-09-23 10:14:23' as parameters. Any other way?
  private val paramsMatcher = """:([0-9a-zA-Z_]+)([^0-9a-zA-Z_.\:']|$)""".r //parameterTypes.keys.map(k => ":" + k).mkString("|").r

  private def extractParameterNames(query: String): Seq[String] = paramsMatcher.findAllMatchIn(query).map(m => m.group(1)).toSeq

  def checkNamedParameters[DT](query: String, parameterNames: Set[String]) {
    val parameterNamesInQuery: Set[String] = extractParameterNames(query).toSet
    val parametersNotFoundInQuery = parameterNames.diff(parameterNamesInQuery).map( """"%s"""".format(_))
    if (parametersNotFoundInQuery.nonEmpty) {
      throw new IllegalArgumentException( """Some parameters where not found in the query "%s". Provided parameters: %s, parameters in query: %s, missing: %s""".
        format(query, parameterNames, parameterNamesInQuery, parametersNotFoundInQuery.mkString(",")))
    }
    val undefinedParameters = parameterNamesInQuery.diff(parameterNames)
    if (undefinedParameters.nonEmpty) {
      throw new IllegalArgumentException( """The query "%s" contains some parameters with unknown types: %s""".format(query, undefinedParameters))
    }

  }

  def translateNamedParameters(query: String, parameters: Seq[Parameter[_]], arguments: Seq[ParameterValue[_]]) = {
    val names = extractParameterNames(query)
    val orderedArguments = names.map(n => arguments.find(_.parameter.name == n).getOrElse(throw new IllegalArgumentException(s"Parameter $n not found in $arguments")))
    val jdbcQuery = paramsMatcher.replaceSomeIn(query, reMatch => {
      val parameterName = reMatch.group(1)
      val argument = arguments.find(_.parameter.name == parameterName).getOrElse(throw new IllegalArgumentException(s"Parameter $parameterName not found in arguments $arguments"))
      Some(argument.getJdbcPlaceHolder + reMatch.group(2))
    })
    (jdbcQuery, orderedArguments)
  }

  import com.agilogy.simpledb.Database.measure

  def executeUpdate(conn: Connection, query: String, parameters: Seq[ParameterValue[_]]) = measure(query) {
    prepareStatement(conn, query, parameters) {
      ps =>
        try {
          ps.executeUpdate()
        } catch {
          case UniqueKeyViolationException(ukve) => throw ukve.copy(sql = query, args = parameters)
          case ForeignKeyViolationException(fkve) => throw fkve.copy(sql = query, args = parameters)
          case sqle: SQLException => throw BaseExecuteUpdateException(query, parameters, sqle)
        }
    }
  }

  private def readResultSet[T](query: String, rs: ResultSet, reads: ResultSetReads[T])(f: (T) => Unit): Unit = {
    withResultSet(rs) {
      rs =>
        val cursor = new Cursor(rs)
        while (!cursor.done) {
          try {
            f(reads.read(cursor))
          } catch {
            case c: NullColumnReadException => throw new NullColumnReadException(query, cursor.currentRow.asRowValue, c.column, c.dbType)
            case c: ColumnReadException => throw new ColumnReadException(query, cursor.currentRow.asRowValue, c.column, c.dbReads, c.optional, c.cause)
            case t: Throwable => throw new RowMappingException(query, cursor.currentRow.asRowValue, t)
          }
        }
    }
  }

  def select[T](conn: Connection, query: String, arguments: Seq[ParameterValue[_]], reads: ResultSetReads[T])(f: (T) => Unit)(implicit fetchSize:FetchSize): Unit = measure(query) {
    prepareStatement(conn, query, arguments, generatedKeys = false) {
      ps =>
        try {
          fetchSize match {
            case LimitedFetchSize(rows) => ps.setFetchSize(rows)
            case DefaultFetchSize => // NOP
          }
          ps.executeQuery()
        } catch {
          case sqle: SQLException => throw ExecuteQueryException(query, arguments,sqle)
        }
        val rs: ResultSet = ps.getResultSet
        readResultSet(query, rs, reads)(f)
    }
  }

  private def withResultSet[R <: ResultSet, T](rs: R)(f: (R => T)) = {
    try {
      f(rs)
    } finally {
      rs.close()
    }
  }

  private def prepareStatement[T, DT](conn: Connection, query: String, args: Seq[ParameterValue[_]], generatedKeys: Boolean = false)(f: (PreparedStatement => T)): T = {
    val s = try {
      val prepareStatementFlag = if (generatedKeys) JdbcStatement.RETURN_GENERATED_KEYS else JdbcStatement.NO_GENERATED_KEYS
      conn.prepareStatement(query, prepareStatementFlag)
    } catch {
      case e: SQLException => throw PrepareStatementException(query, e)
    }
    try {
      setValues(s, args)
      f(s)
    } finally {
      s.close()
    }
  }

  private def setValues(s: PreparedStatement, args: Seq[ParameterValue[_]]) {
    var i: Int = 1
    args.foreach(a => i += a.set(s, i))
  }

  def executeUpdateGeneratedKeys[RT](conn: Connection, query: String, args: Seq[ParameterValue[_]], reads: Option[Row => RT]): RT = measure(query) {
    prepareStatement(conn, query, args, reads.isDefined) {
      s: PreparedStatement =>
        logger.debug("%s (%s)".format(query, args.mkString(",")))
        val result = try {
          s.executeUpdate()
        } catch {
          case UniqueKeyViolationException(ukve) => throw ukve.copy(sql = query, args = args)
          case ForeignKeyViolationException(fkve) => throw fkve.copy(sql = query, args = args)
          case sqle: SQLException => throw BaseExecuteUpdateException(query, args, sqle)
        }
        if (reads.isEmpty) {
          result.asInstanceOf[RT]
        } else {
          withResultSet(s.getGeneratedKeys) {
            rs =>
              if (rs.next) {
                reads.get.apply(new Row(rs))
              } else {
                throw new RuntimeException("No key was generated")
              }
          }
        }
    }
  }

  @inline def checkNotNullTxConfig()(implicit config: TransactionConfig) = require(config != null, "Don't use null for transaction configuration. Use NewTransaction.")

}
