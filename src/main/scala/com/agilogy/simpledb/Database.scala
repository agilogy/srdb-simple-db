package com.agilogy.simpledb

import javax.sql.DataSource

import com.agilogy.simpledb.dsl.Syntax
import com.agilogy.srdb.tx.{TransactionController, TransactionConfig, Transaction}

@deprecated("Use the functions available when importing com.agilogy.simpledb._ and com.agilogy.simpledb.dsl._")
class Database private(val ds: DataSource) extends StatementFactory with QueryFactory with DatabaseOperations with Syntax {

  val db = this

  def inTransaction[T](f: Transaction => T)(implicit config: TransactionConfig): T = {
    TransactionController.inTransaction(ds)(f)(config)
  }

}

object Database extends Measurable {

  def apply(ds: DataSource): Database = new Database(ds)
}

