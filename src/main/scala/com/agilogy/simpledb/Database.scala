package com.agilogy.simpledb

import javax.sql.DataSource

import com.agilogy.simpledb.dsl.Syntax
import com.agilogy.srdb.tx.{TransactionController, TransactionConfig, Transaction}

class Database private(val ds: DataSource) extends DatabaseStatementFactory with DatabaseQueryFactory with DatabaseOperations with Syntax {

  val db = this

  def inTransaction[T](f: Transaction => T)(implicit config: TransactionConfig): T = {
    TransactionController.inTransaction(ds)(f)(config)
  }

}

object Database extends Measurable {

  def apply(ds: DataSource) = new Database(ds)
}

