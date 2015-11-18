package com.agilogy.simpledb

@deprecated("Use import com.agilogy.simpledb._ instead", since = "4.6")
object SimpleDb extends ResultSetReadsSyntax with DbTypeImplicits{
  val Asc = com.agilogy.simpledb.dsl.Asc
  val AscNullsFirst = com.agilogy.simpledb.dsl.AscNullsFirst
  val Desc = com.agilogy.simpledb.dsl.Desc
  val DescNullsLast = com.agilogy.simpledb.dsl.DescNullsLast
  val OrderByQueryPart = com.agilogy.simpledb.dsl.OrderByQueryPart

  type Predicate = com.agilogy.simpledb.dsl.Predicate
  type Expression[T] = com.agilogy.simpledb.dsl.Expression[T]

  val DslQuery = com.agilogy.simpledb.dsl.DslQuery
  val JoinRelation = com.agilogy.simpledb.dsl.JoinRelation

  type Table = com.agilogy.simpledb.schema.Table
  type Column[T] = com.agilogy.simpledb.schema.Column[T]
  val Column = com.agilogy.simpledb.schema.Column
  type ColumnAssignment[T] = com.agilogy.simpledb.dsl.ColumnAssignment[T]
  val Syntax = com.agilogy.simpledb.dsl.Syntax

  implicit def columnToKeyReader[T](c:Column[T]):(Row => T) = _.get(c)

}


