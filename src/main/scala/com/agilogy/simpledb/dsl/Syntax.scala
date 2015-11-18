package com.agilogy.simpledb.dsl

import com.agilogy.simpledb._
import com.agilogy.simpledb.schema.Table

trait Syntax {

  self:Database =>

  def from(r: Relation): FromQueryPart = FromQueryPart(ds, r)

  def fromNoTable: FromQueryPart = FromQueryPart(ds, NoRelation)

  def update(t: Table): Update1 = Update1(ds, t)

  def delete(t: Table): DeleteAllRowsStatement = DeleteAllRowsStatement(ds, t)

  def insertInto[RT](t: Table): Insert1 = Insert1(ds, t)

}

object Syntax extends ExpressionSyntax{

  val True = com.agilogy.simpledb.dsl.True
  val False = com.agilogy.simpledb.dsl.False

}

