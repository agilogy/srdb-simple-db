package it

import com.agilogy.simpledb._
import com.agilogy.simpledb.dsl._
import com.agilogy.srdb.tx.{TransactionConfig, NewTransaction}
import it.TestSchema.{Departments, Planets}
//TODO: Avoid this import
import com.agilogy.srdb.types.SimpleDbCursorReader._

class OperationsTest extends TestBase {

  behavior of "inserts"

  implicit val txConfig:TransactionConfig = NewTransaction

  import db._

  val selectDepartments = createQuery("select * from departments d")(d.reads).withoutParams

  it should "insert" in {
    db.inTransaction {
      implicit tx =>
        insert(d, d.name := "devops")
        val result = selectDepartments()
        assert(result.size === 1)
        assert(result(0).name === "devops")
    }
  }

  it should "insert and return generated key" in {
    db.inTransaction {
      implicit tx =>
        //TODO Avoid having to declare the type in IntelliJ
        val id0: Long = insertAndGetKey(d, d.name := "devops", d.code := Some("devop"))(d.id)
        val id1: Long = insertAndGetKey(d, d.name := "administration")(d.id)
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
    db.inTransaction(tx => insert(d, d.name := "devops")(tx))
    db.inTransaction {
      implicit tx =>
        insertIfNotFound(d, d.name := "devops")
        assert(selectDepartments().size === 1)
        insertIfNotFound(d, d.name := "dept2")
    }
    db.inTransaction {
      implicit tx =>
        assert(selectDepartments().size === 2)
    }
  }

  it should "insert if not found and get key" in {
    db.inTransaction {
      implicit tx =>
        val existingId = insertAndGetKey(d, d.name := "devops")(d.id)
        val returnedId = insertIfNotFoundAndGetKey(d,d.name := "devops")(d.id)
        assert(returnedId === existingId)
        val newId: Long = insertIfNotFoundAndGetKey(d, d.name := "dept2")(d.id)
        assert(newId != existingId)
        assert(selectDepartments().size === 2)
    }
  }

  behavior of "insert or update"

  it should "insert or update" in {
    val p = Planets("p")
    db.inTransaction {
      implicit tx =>
        insertOrUpdate(p, p.name := "earth", p.position := 3)
        assert(selectPlanetByName("earth").head.position === 3)
        insertOrUpdate(p, p.name := "earth", p.position := 4)
        assert(selectPlanetByName("earth").head.position === 4)
    }
  }

  it should "insert or update and get key" in {
    val p = Planets("p")
    db.inTransaction {
      implicit tx =>
        val id1 = insertOrUpdateAndGetKey(p, p.name := "earth", p.position := 3)(p.name)
        assert(selectPlanetByName("earth").head.position === 3)
        val id2 = insertOrUpdateAndGetKey(p, p.name := "earth", p.position := 4)(p.name)
        assert(selectPlanetByName("earth").head.position === 4)
        assert(id1 === id2)
    }
  }

  behavior of "update"

  it should "update a table with some type checking" in {
    val p = Planets("p")
    db.inTransaction {
      implicit tx =>
        val affectedRows = update(p, p.position ==== 2, p.name := "Venus!!")
        assert(affectedRows === 1)
        val planet = selectPlanetByName("Venus!!").head
        assert(planet.position === 2)
    }
  }

  behavior of "select"


  it should "select by exact values of columns" in {
    db.inTransaction {
      implicit tx =>
        insertDepartments()
        val result = db.select(d, (d.city ==== "Vilafranca") and d.active)(d.reads)
        assert(result.map(_.name).toSet === Set("d1", "d2"))
    }
  }

  behavior of "None in conditions"

  it should "select a row with an optional column with null value" in {
    val d = Departments("d")
    import d._
    db.inTransaction {
      implicit tx =>
        insert(d, id := 1l, name := "name", active := true, code := Null)
        //TODO Allow using the column directly
        val rows = select(d, code nullSafeEq Null, columns=Seq(d.id))(d.id.reader)
        assert(rows.size === 1)
    }
  }

  it should "update a row with an optional column with null value" in {
    val d = Departments("d")
    import d._
    db.inTransaction {
      implicit tx =>
        insert(d, id := 1l, name := "name", active := true, code := Null)
        val rows = update(d, code.isNull, active := false)
        assert(rows === 1)
    }
  }
}
