package it

import com.agilogy.srdb.tx.NewTransaction


class DslQueriesTest extends TestBase {

  behavior of "queries dsl"

  import TestSchema._
  import com.agilogy.simpledb.dsl.Syntax._

  it should "build and execute a simple query" in {
    val e = Employees("e")
    db.inTransaction {
      implicit tx =>
        insertDepartmentsAndEmployees()
        val selectEmployeesAndDepts = db.from(e).select(e.name, e.departmentId).map { case (en, dn) => s"Employee $en works at the department with id $dn"}.withoutParams
        val res = selectEmployeesAndDepts()
        assert(res === List("Employee emp1 works at the department with id 1", "Employee emp2 works at the department with id 1", "Employee emp3 works at the department with id 2"))
    }(NewTransaction)
  }

  it should "build and execute a join query" in {
    db.inTransaction(tx => insertDepartmentsAndEmployees()(tx))(NewTransaction)
    db.inTransaction {
      implicit tx =>
        val selectEmployeesAndDepts = db.from(e.join(d, e.departmentId ==== d.id)).select(e.name, d.name).map { case (en, dn) => s"Employee $en works at the department $dn"}.withoutParams
        val res = selectEmployeesAndDepts()
        assert(res === List("Employee emp1 works at the department d1", "Employee emp2 works at the department d1", "Employee emp3 works at the department d2"))
    }(NewTransaction)
  }

  it should "build and execute a query with group by" in {
    db.inTransaction(tx => insertDepartmentsAndEmployees()(tx))(NewTransaction)
    db.inTransaction {
      implicit tx =>
        val e = Employees("e")
        val selectDepts = db.from(e.join(d, e.departmentId ==== d.id)).groupBy(d.name).select(d.name).withoutParams
        val res = selectDepts()
        assert(res === List("d1", "d2"))
    }(NewTransaction)

  }

  it should "select distinct values when asked to" in {
    val q = db.from(d).select(d.city).distinct

    db.inTransaction {
      implicit tx =>
        insertDepartments()
        val res = q()
        assert(res.size === 2)
        assert(res.contains("Barcelona"))
        assert(res.contains("Vilafranca"))
    }(NewTransaction)
  }


}
