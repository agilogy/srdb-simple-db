package it

import com.agilogy.simpledb.SimpleDb
import SimpleDb._
import Syntax._
import com.agilogy.srdb.tx.NewTransaction

class DslStatementsTest extends TestBase {


  implicit val txConfig = NewTransaction

  behavior of "statements dsl"

  val selectDepts = db.from(d).orderBy(d.name).select(d.*)(d.reads).withoutParams

  it should "build and execute a simple update statement" in {
    db.inTransaction(tx => insertDepartments()(tx))
    val stmt = db.update(d).set(d.active := false).where(d.city ==== "Vilafranca")
    stmt()
    val res = selectDepts()
    assert(res(0).active === false)
    assert(res(1).active === false)
    assert(res(2).active === true)
  }

  it should "build and execute a simple delete statement" in {
    db.inTransaction(tx => insertDepartments()(tx))
    val stmt = db.delete(d).where(d.city ==== "Vilafranca")
    stmt()
    val res = selectDepts()
    assert(res.size === 1)
    assert(res(0).city != "Vilafranca")
  }

  it should "build and execute an insert statement" in {
    db.inTransaction(tx => insertDepartments()(tx))
    val insertSql = db.insertInto(d).values(d.name := param(0), d.city := param(1), d.active := true).andReadGeneratedKeys(d.id)
    val stmt = insertSql.withParams(text, text)
    stmt("d5", "Sant Pere Molanta")
    val res = selectDepts()
    assert(res.size === 4)
    assert(res(3).name === "d5")
    assert(res(3).city === "Sant Pere Molanta")
    val id: Long = stmt.apply("d6", "Sant Pere Molanta")
    val res2 = db.from(d).where(d.id ==== id).select(d.name, d.city).apply().head
    assert(res2 ===("d6", "Sant Pere Molanta"))
  }
}
