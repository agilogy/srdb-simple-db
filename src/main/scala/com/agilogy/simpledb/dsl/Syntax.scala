package com.agilogy.simpledb.dsl

import com.agilogy.simpledb.schema.Table

trait Syntax {

  def from(r: Relation): FromQueryPart = FromQueryPart(r)

  def fromNoTable: FromQueryPart = FromQueryPart(NoRelation)

  def update(t: Table): Update1 = Update1(t)

  def delete(t: Table): DeleteAllRowsStatement = DeleteAllRowsStatement(t)

  def insertInto[RT](t: Table): Insert1 = Insert1(t)

}

