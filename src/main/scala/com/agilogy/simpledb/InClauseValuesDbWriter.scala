package com.agilogy.simpledb

import java.sql.PreparedStatement

import com.agilogy.simpledb.dsl.ConstantStrategy
import com.agilogy.srdb.types.{AtomicDbWriter, AtomicNotNullDbWriter}

class InClauseValuesDbWriter[T] (implicit val w: AtomicNotNullDbWriter[T]) extends AtomicDbWriter[Seq[T]] {

  override def set(ps: PreparedStatement, position: Int, seq: Seq[T]): Unit = {
    if(seq.isEmpty){
      w.optional.set(ps,position,None)
    }else {
      seq.zipWithIndex.foreach {
        case(v,i) => w.set(ps, position + i, v)
      }
    }
  }

  override def length(value:Seq[T]): Int = value.length.min(1)

}

object InClauseValuesDbWriter{
  def getJdbcPlaceholderFor(size: Int): String = {
    if(size == 0) "(null)"
    else 1.to(size).map(_ => "?").mkString("(",",",")")
  }

  def apply[T:AtomicNotNullDbWriter]: InClauseValuesDbWriter[T] = new InClauseValuesDbWriter[T]
}

case class InClauseValuesConstantStrategy[T] (implicit val cs:ConstantStrategy[T]) extends ConstantStrategy[Seq[T]]{

      override def constant(value: Seq[T]): String = {
        if(value.isEmpty) "(null)"
        else value.map(v => cs.constant(v)).mkString("(", ",", ")")
      }

      override def isSecure(value: Seq[T]): Boolean = value.forall(cs.isSecure)

}