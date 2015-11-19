package it

import com.agilogy.simpledb._
import com.agilogy.simpledb.dsl.Null

class OperationsTest extends TestBase {

  behavior of "inserts"

  import TestSchema._
  import txController.inTransaction

  val selectDepartments = createQuery("select * from departments d")(d.reads).withoutParams

  it should "insert" in {
    inTransaction {
      implicit tx =>
        Ops.insert(d, d.name := "devops")
        val result = selectDepartments()
        assert(result.size === 1)
        assert(result(0).name === "devops")
    }
  }

  it should "insert and return generated key" in {
    inTransaction {
      implicit tx =>
        //TODO Avoid having to declare the type in IntelliJ
        val id0: Long = Ops.insertAndGetKey(d, d.name := "devops", d.code := Some("devop"))(d.id)
        val id1: Long = Ops.insertAndGetKey(d, d.name := "administration")(d.id)
        assert(id1 === id0 + 1)
        val result = selectDepartments()
        assert(result(0).id === id0)
        assert(result(0).name === "devops")
        assert(result(0).code === Some("devop"))
        assert(result(1).id === id1)
        assert(result(1).name === "administration")
    }
  }

  it should "insert if not found" in {
    inTransaction(tx => Ops.insert(d, d.name := "devops")(tx))
    inTransaction {
      implicit tx =>
        Ops.insertIfNotFound(d, d.name := "devops")
        assert(selectDepartments().size === 1)
        Ops.insertIfNotFound(d, d.name := "dept2")
    }
    inTransaction {
      implicit tx =>
        assert(selectDepartments().size === 2)
    }
  }

  it should "insert if not found and get key" in {
    inTransaction {
      implicit tx =>
        val existingId = Ops.insertAndGetKey(d, d.name := "devops")(d.id)
        val returnedId = Ops.insertIfNotFoundAndGetKey(d, d.name := "devops")(d.id)
        assert(returnedId === existingId)
        val newId: Long = Ops.insertIfNotFoundAndGetKey(d, d.name := "dept2")(d.id)
        assert(newId != existingId)
        assert(selectDepartments().size === 2)
    }
  }

  behavior of "insert or update"

  it should "insert or update" in {
    val p = Planets("p")
    inTransaction {
      implicit tx =>
        Ops.insertOrUpdate(p, p.name := "earth", p.position := 3)
        assert(selectPlanetByName("earth").head.position === 3)
        Ops.insertOrUpdate(p, p.name := "earth", p.position := 4)
        assert(selectPlanetByName("earth").head.position === 4)
    }
  }

  it should "insert or update and get key" in {
    val p = Planets("p")
    inTransaction {
      implicit tx =>
        val id1 = Ops.insertOrUpdateAndGetKey(p, p.name := "earth", p.position := 3)(p.name)
        assert(selectPlanetByName("earth").head.position === 3)
        val id2 = Ops.insertOrUpdateAndGetKey(p, p.name := "earth", p.position := 4)(p.name)
        assert(selectPlanetByName("earth").head.position === 4)
        assert(id1 === id2)
    }
  }

  behavior of "update"

  it should "update a table with some type checking" in {
    val p = Planets("p")
    inTransaction {
      implicit tx =>
        val affectedRows = Ops.update(p, p.position ==== 2, p.name := "Venus!!")
        assert(affectedRows === 1)
        val planet = selectPlanetByName("Venus!!").head
        assert(planet.position === 2)
    }
  }

  behavior of "select"

  it should "select by exact values of columns" in {
    inTransaction {
      implicit tx =>
        insertDepartments()
        val result = Ops.select(d, (d.city ==== "Vilafranca") and d.active)(d.reads)
        assert(result.map(_.name).toSet === Set("d1", "d2"))
    }
  }

  behavior of "None in conditions"

  it should "select a row with an optional column with null value" in {
    val d = Departments("d")
    import d._
    inTransaction {
      implicit tx =>
        Ops.insert(d, id := 1l, name := "name", active := true, code := Null)
        //TODO Allow using the column directly
        val rows = Ops.select(d, code nullSafeEq Null, columns = Seq(d.id))(d.id.reader)
        assert(rows.size === 1)
    }
  }

  it should "update a row with an optional column with null value" in {
    val d = Departments("d")
    import d._
    inTransaction {
      implicit tx =>
        Ops.insert(d, id := 1l, name := "name", active := true, code := Null)
        val rows = Ops.update(d, code.isNull, active := false)
        assert(rows === 1)
    }
  }
}
