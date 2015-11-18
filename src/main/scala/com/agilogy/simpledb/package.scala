package com.agilogy

package object simpledb extends ResultSetReadsSyntax with DbTypeImplicits{

  type Transaction = com.agilogy.srdb.tx.Transaction
  type TransactionConfig = com.agilogy.srdb.tx.TransactionConfig
  val NewTransaction = com.agilogy.srdb.tx.NewTransaction
  type TransactionController = com.agilogy.srdb.tx.TransactionController
  val TransactionController = com.agilogy.srdb.tx.TransactionController
}
