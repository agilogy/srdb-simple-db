package it

import com.agilogy.simpledb._
import com.agilogy.srdb.tx.{NewTransaction, Transaction}
import com.googlecode.flyway.core.Flyway
import com.mchange.v2.c3p0.ComboPooledDataSource
//TODO: Avoid this import
import com.agilogy.srdb.types.SimpleDbCursorReader._

import it.TestSchema._
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FlatSpec}

trait TestBase extends FlatSpec with DatabaseUriConfig with BeforeAndAfterAll with BeforeAndAfterEach {

  override lazy val dbUriPropertyName = "SIMPLE_DB_TEST_DB_URL"

  val defaultDbUri = "postgresql://test:test@localhost:5432/simple-db-test"

  val ds = new ComboPooledDataSource()
  ds.setJdbcUrl(databaseConnectionUrl)
  ds.setUser(databaseUsername)
  ds.setPassword(databasePassword)
  ds.setDriverClass(classOf[org.postgresql.Driver].getName)
  ds.setMaxPoolSize(2)

  val migrations = {
    val res = new Flyway()
    res.setDataSource(ds)
    res
  }

  val db = Database(ds)

  override def beforeAll():Unit =  {
    migrations.clean()
    migrations.migrate()
  }

  override def afterAll(): Unit ={
    ds.close()
  }

  import db._

  val deletePlanets = createStatement("delete from planets")
  val deleteDepartments = createStatement("delete from departments")
  val selectPlanetByName = createQuery("select * from planets where name = :0")(planetReads).withParams[String]
  val deleteEmployees = createStatement("delete from employees")

  val venus = Planet("venus", 2)
  val d = Departments("d")
  val e = Employees("e")
  val p = Planets("p")
  val w = WithNumerics("w")

  protected def insertDepartments()(implicit tx: Transaction): (Long, Long, Long) = {
    val dept1 = insertAndGetKey(d, d.name := "d1", d.city := "Vilafranca", d.active := true)(d.id)
    val dept2 = insertAndGetKey(d, d.name := "d2", d.city := "Vilafranca", d.active := true)(d.id)
    val dept3 = insertAndGetKey(d, d.name := "d3", d.city := "Barcelona", d.active := true)(d.id)
    (dept1, dept2, dept3)
  }

  protected def insertDepartmentsAndEmployees()(implicit tx: Transaction): Unit = {
    val (dept1, dept2, _) = insertDepartments()
    insert(e, e.name := "emp1", e.departmentId := dept1)
    insert(e, e.name := "emp2", e.departmentId := dept1)
    insert(e, e.name := "emp3", e.departmentId := dept2)
  }


  override def beforeEach() {
    db.inTransaction {
      implicit tx =>
        deletePlanets()
        deleteEmployees()
        deleteDepartments()
        insert(p, p.name := "venus", p.position := 2)
    }(NewTransaction)
  }
}
