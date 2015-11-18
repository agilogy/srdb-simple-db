package it

import com.agilogy.simpledb._

class StatementsTest extends TestBase {

  import SimpleDb._
  import TestSchema._

  implicit val txConfig = NewTransaction

  //  it should "execute update statements" in {
  //    import com.agilogy.simpleRuntimeColumns._
  //    val updatePlanetPosition = createStatement("update planets set position = :position where name = :name", "position" -> DbType.integer, "name" -> DbType.text)
  //    updatePlanetPosition("name" -> "venus", "position" -> BigInt(99))
  //    val venus = selectPlanetByName("venus").head
  //    assert(venus.get("position") === Some(99))
  //  }

  import db._

  it should "execute delete statements" in {
    val deletePlanetByName = createStatement("delete from planets where name = :0").withParams(text)
    deletePlanetByName("venus")
    val venus = selectPlanetByName("venus").headOption
    assert(venus === None)
  }


  it should "execute statements with 1 positional parameter" in {
    val updateAllPlanetsPosition = createStatement("update planets set position = :0").withParams(integer)
    updateAllPlanetsPosition(23)
    val selectAllPlanets = createQuery("select * from planets")(planetReads).withoutParams
    val allPlanets = selectAllPlanets()
    allPlanets.foreach(p => assert(p.position === 23))

  }

  it should "execute statements with 2 positional parameters" in {
    val updatePlanetPosition = createStatement("update planets set position = :1 where name = :0").withParams(text, integer)
    updatePlanetPosition("venus", 99)
    val venus = selectPlanetByName("venus").head
    assert(venus.position === 99)
  }

  it should "execute statements without declaring parameters" in {
    val updatePlanetPosition = createStatement("update planets set position = :1 where name = :0")
    updatePlanetPosition("venus", 99)
    val venus = selectPlanetByName("venus").head
    assert(venus.position === 99)
  }

  it should "report primary key constraint violations" in{
    val insertPlanet = createStatement("insert into planets(name,position) values (:0,:1)").withParams(text,integer)
    val dke = intercept[UniqueKeyViolationException]{
      insertPlanet("venus",22)
    }
    assert(dke.constraintName === "planets_pkey")
    assert(dke.key === Seq("name"))
  }

  it should "report unique key constraint violations" in{
    val insertPlanet = createStatement("insert into planets(name,position) values (:0,:1)").withParams(text,integer)
    val dke = intercept[UniqueKeyViolationException]{
      insertPlanet("wrong earth",2)
    }
    assert(dke.constraintName === "planets_position_key")
    assert(dke.key === Seq("position"))
  }


  it should "report unique key constraint violations when getting hte generated key" in{
    val insertDepartment = createStatement("insert into departments(name,city) values (:0,:1)").withParams(text,text)
    insertDepartment("sales","Vilafranca")
    val dke = intercept[UniqueKeyViolationException]{
      insertDepartment("sales","Barcelona")
    }
    assert(dke.constraintName === "departments_name_key")
    assert(dke.key === Seq("name"))
  }

  it should "report foreign key violations when inserting" in{
    import SimpleDb.Syntax._
    val fke = intercept[ForeignKeyViolationException] {
      insert(e, e.name := "Jordi", e.departmentId := 345l)
    }
    assert(fke.violationType === ReferencedKeyNotPresent)
    assert(fke.referencingTable === "employees")
    assert(fke.referredTable === "departments")
    assert(fke.constraintName === "employees_department_id_fkey")
    assert(fke.key === Seq("department_id"))

  }

  it should "report foreign key violations when deleting" in{
    import SimpleDb.Syntax._
    val d1 = insertAndGetKey(d, d.name := "d1")(_.get(d.id))
    insert(e, e.name := "Jordi", e.departmentId := d1)
    val fke = intercept[ForeignKeyViolationException] {
     delete(d, d.id ==== d1)
    }
    assert(fke.violationType === KeyStillReferenced)
    assert(fke.referencingTable === "employees")
    assert(fke.referredTable === "departments")
    assert(fke.constraintName === "employees_department_id_fkey")
    assert(fke.key === Seq("id"))

  }
}
