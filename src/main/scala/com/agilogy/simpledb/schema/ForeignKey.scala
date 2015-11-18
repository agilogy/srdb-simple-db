package com.agilogy.simpledb.schema

import com.agilogy.simpledb.dsl.{EqualsPredicate, Predicate}

trait ForeignKey {
  private[simpledb] def joinOnPredicate(t2: Table): Predicate

}

case class ForeignKey1[T](c: Column[T], references: Column[T])
  extends ForeignKey {

  override private[simpledb] def joinOnPredicate(t2: Table): Predicate = EqualsPredicate(c, references.at(t2))
}
