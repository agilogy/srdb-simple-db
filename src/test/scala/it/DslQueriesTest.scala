package it

import com.agilogy.simpledb.dsl._

class DslQueriesTest extends TestBase {

  behavior of "queries dsl"

  import TestSchema._
  import txController.inTransaction

  it should "build and execute a simple query" in {
    val e = Employees("e")
    inTransaction {
      implicit tx =>
        insertDepartmentsAndEmployees()
        val selectEmployeesAndDepts = from(e).select(e.name, e.departmentId).map { case (en, dn) => s"Employee $en works at the department with id $dn" }.withoutParams
        val res = selectEmployeesAndDepts()
        assert(res === List("Employee emp1 works at the department with id 1", "Employee emp2 works at the department with id 1", "Employee emp3 works at the department with id 2"))
    }
  }

  it should "build and execute a join query" in {
    inTransaction(tx => insertDepartmentsAndEmployees()(tx))
    inTransaction {
      implicit tx =>
        val selectEmployeesAndDepts = from(e.join(d, e.departmentId ==== d.id)).select(e.name, d.name).map { case (en, dn) => s"Employee $en works at the department $dn" }.withoutParams
        val res = selectEmployeesAndDepts()
        assert(res === List("Employee emp1 works at the department d1", "Employee emp2 works at the department d1", "Employee emp3 works at the department d2"))
    }
  }

  it should "build and execute a query with group by" in {
    inTransaction(tx => insertDepartmentsAndEmployees()(tx))
    inTransaction {
      implicit tx =>
        val e = Employees("e")
        val selectDepts = from(e.join(d, e.departmentId ==== d.id)).groupBy(d.name).select(d.name).withoutParams
        val res = selectDepts()
        assert(res === List("d1", "d2"))
    }

  }

  it should "select distinct values when asked to" in {
    val q = from(d).select(d.city).distinct

    inTransaction {
      implicit tx =>
        insertDepartments()
        val res = q()
        assert(res.size === 2)
        assert(res.contains("Barcelona"))
        assert(res.contains("Vilafranca"))
    }
  }

  it should "support non predefined functions in the dsl" in {
    val charLength = sqlFunction1[String, Int]("char_length")
    val q = from(d).where(charLength(d.city) >= 10).select(d.name)
    inTransaction {
      implicit tx =>
        insertDepartments()
        val res = q()
        assert(res.size === 2)
        assert(res.contains("d1"))
        assert(res.contains("d2"))
    }
  }

  it should "support string query fragments in the dsl" in {
    val q = from(d).where(sql[Boolean]("lower(d.city) like 'b%'")).select(d.name)
    inTransaction {
      implicit tx =>
        insertDepartments()
        val res = q()
        assert(res.size === 1)
        assert(res.contains("d3"))
    }
  }

}
