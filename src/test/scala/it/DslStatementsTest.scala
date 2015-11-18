package it

import com.agilogy.simpledb._
import com.agilogy.simpledb.dsl._

class DslStatementsTest extends TestBase {

  import txController.inTransaction

  behavior of "statements dsl"

  val selectDepts = from(d).orderBy(d.name).select(d.*)(d.reads).withoutParams

  it should "build and execute a simple update statement" in {
    inTransaction(tx => insertDepartments()(tx))
    val stmt = update(d).set(d.active := false).where(d.city ==== "Vilafranca")
    inTransaction {
      implicit tx =>
        stmt()
        val res = selectDepts()
        assert(res(0).active === false)
        assert(res(1).active === false)
        assert(res(2).active === true)
    }
  }

  it should "build and execute a simple delete statement" in {
    inTransaction(tx => insertDepartments()(tx))
    val stmt = delete(d).where(d.city ==== "Vilafranca")
    inTransaction {
      implicit tx =>
        stmt()
        val res = selectDepts()
        assert(res.size === 1)
        assert(res(0).city != "Vilafranca")
    }
  }

  it should "build and execute an insert statement" in {
    inTransaction(tx => insertDepartments()(tx))
    val stmt = insertInto(d).values(d.name := param(0), d.city := param(1), d.active := true)
      .andReadGeneratedKeys(d.id)
      .withParams[String, String]
    inTransaction {
      implicit tx =>
        stmt("d5", "Sant Pere Molanta")
        val res = selectDepts()
        assert(res.size === 4)
        assert(res(3).name === "d5")
        assert(res(3).city === "Sant Pere Molanta")
        val id: Long = stmt.apply("d6", "Sant Pere Molanta")
        val res2 = from(d).where(d.id ==== id).select(d.name, d.city).apply().head
        assert(res2 ===("d6", "Sant Pere Molanta"))
    }
  }
}
