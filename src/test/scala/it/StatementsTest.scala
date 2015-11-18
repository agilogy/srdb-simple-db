package it

import com.agilogy.simpledb._

class StatementsTest extends TestBase {

  import txController.inTransaction

  it should "execute delete statements" in {
    val deletePlanetByName = createStatement("delete from planets where name = :0").withParams[String](writer1[String])
    inTransaction {
      implicit tx =>
        deletePlanetByName("venus")
        val venus = selectPlanetByName("venus").headOption
        assert(venus === None)
    }
  }


  it should "execute statements with 1 positional parameter" in {
    val updateAllPlanetsPosition = createStatement("update planets set position = :0").withParams[Int]
    inTransaction {
      implicit tx =>
        updateAllPlanetsPosition(23)
        val selectAllPlanets = createQuery("select * from planets")(p.reads).withoutParams
        val allPlanets = selectAllPlanets()
        allPlanets.foreach(p => assert(p.position === 23))
    }
  }

  it should "execute statements with 2 positional parameters" in {
    val updatePlanetPosition = createStatement("update planets set position = :1 where name = :0").withParams[String,Int]
    inTransaction {
      implicit tx =>
        updatePlanetPosition("venus", 99)
        val venus = selectPlanetByName("venus").head
        assert(venus.position === 99)
    }
  }

  it should "execute statements without declaring parameters" in {
    val updatePlanetPosition = createStatement("update planets set position = :1 where name = :0")
    inTransaction {
      implicit tx =>
        updatePlanetPosition("venus", 99)
        val venus = selectPlanetByName("venus").head
        assert(venus.position === 99)
    }
  }

  behavior of "error reporting"

  ignore should "report primary key constraint violations" in{
    val insertPlanet = createStatement("insert into planets(name,position) values (:0,:1)").withParams[String,Int]
    inTransaction {
      implicit tx =>
        val dke = intercept[UniqueKeyViolationException] {
          insertPlanet("venus", 22)
        }
        assert(dke.constraintName === "planets_pkey")
        assert(dke.key === Seq("name"))
    }
  }

  ignore should "report unique key constraint violations" in{
    val insertPlanet = createStatement("insert into planets(name,position) values (:0,:1)").withParams[String,Int]
    inTransaction {
      implicit tx =>
        val dke = intercept[UniqueKeyViolationException] {
          insertPlanet("wrong earth", 2)
        }
        assert(dke.constraintName === "planets_position_key")
        assert(dke.key === Seq("position"))
    }
  }


  ignore should "report unique key constraint violations when getting hte generated key" in{
    val insertDepartment = createStatement("insert into departments(name,city) values (:0,:1)").withParams[String,String]
    inTransaction {
      implicit tx =>
        insertDepartment("sales", "Vilafranca")
        val dke = intercept[UniqueKeyViolationException] {
          insertDepartment("sales", "Barcelona")
        }
        assert(dke.constraintName === "departments_name_key")
        assert(dke.key === Seq("name"))
    }
  }

  ignore should "report foreign key violations when inserting" in{
    inTransaction {
      implicit tx =>
        val fke = intercept[ForeignKeyViolationException] {
          Ops.insert(e, e.name := "Jordi", e.departmentId := 345l)
        }
        assert(fke.violationType === ReferencedKeyNotPresent)
        assert(fke.referencingTable === "employees")
        assert(fke.referredTable === "departments")
        assert(fke.constraintName === "employees_department_id_fkey")
        assert(fke.key === Seq("department_id"))
    }
  }

  ignore should "report foreign key violations when deleting" in{
    inTransaction {
      implicit tx =>
        val d1 = Ops.insertAndGetKey(d, d.name := "d1")(d.id)
        Ops.insert(e, e.name := "Jordi", e.departmentId := d1)
        val fke = intercept[ForeignKeyViolationException] {
          Ops.delete(d, d.id ==== d1)
        }
        assert(fke.violationType === KeyStillReferenced)
        assert(fke.referencingTable === "employees")
        assert(fke.referredTable === "departments")
        assert(fke.constraintName === "employees_department_id_fkey")
        assert(fke.key === Seq("id"))
    }
  }
}
