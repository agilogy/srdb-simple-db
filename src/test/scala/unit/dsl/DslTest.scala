package unit.dsl

import java.net.URI
import javax.sql.DataSource

import com.agilogy.simpledb._
import com.agilogy.simpledb.schema._
import com.agilogy.simpledb.dsl._
import it.TestSchema.Employees
import org.postgresql.jdbc2.optional.SimpleDataSource
import org.scalatest.FlatSpec
//TODO: Avoid this import
import com.agilogy.srdb.types.SimpleDbCursorReader._

class DslTest extends FlatSpec {

  val mockDs:DataSource = new SimpleDataSource

  val db = Database(mockDs)

  implicit val uriDbType = DbString.xmap[URI](s => new URI(s),_.toString)

  case class Person(id: Long, name: String, age: Int, departmentId: Long, motherId: Long)

  case class People(alias: String = "p") extends Table {
    override val tableName: String = "people"

    val id = notNull[Long]("id")
    val name = notNull[String]("name")
    val age = notNull[Int]("age")
    val departmentId = notNull[Long]("dept_id")
    val motherId = notNull[Long]("mother_id")
    val inactive = notNull[Boolean]("inactive")
    val deleted = notNull[Boolean]("deleted")
    val uri = notNull[URI]("uri")
    val headOf = optional[Int]("head_of")
    val deptFk = foreignKey(departmentId, references = Departments().id)
    val motherFK = foreignKey(motherId, id)

    val reads = reader1(name.columnType)
    override val primaryKey: Seq[Column[_]] = Seq(id)
  }

  case class Departments(alias: String = "d") extends Table {
    override val tableName: String = "departments"

    val id = notNull[Long]("id")
    val name = notNull[String]("name")

    val reads = reader1(name.columnType)
    override val primaryKey: Seq[Column[_]] = Seq(id)
  }

  behavior of "expressions"

  they should "support constants" in {
    assert(3.sql === "3")
    assert(4l.sql === "4")
    assert(true.sql === "true")
    assert(false.sql === "false")
    assert("hi".sql === "'hi'")
    assert(Null.sql === "null")
  }

  ignore should "support user type constants" in {
    //TODO: Fix this one
//    assert(new URI("/a").sql === "'/a'")
  }

  they should "support parameters" in {
    assert(param[String](3).sql === ":3")
  }

  they should "support columns" in {
    val p = People("p")
    assert(p.name.sql === "p.name", "String columns not supported")
    assert(p.age.sql === "p.age", "Int columns not supported")
    assert(p.departmentId.sql === "p.dept_id", "Long columns not supported")
    assert(p.deleted.sql === "p.deleted", "Boolean columns not supported")
  }

  it should "support columns of user types" in {
    val p = People("p")
    assert(p.uri.sql === "p.uri")
  }

  they should "support boolean operators on boolean columns" in {
    val p = People("p")
    assert((p.inactive or p.deleted).sql === "(p.inactive or p.deleted)")
    assert((p.inactive and p.deleted).sql === "(p.inactive and p.deleted)")
    assert(not(p.deleted).sql === "(not p.deleted)")
    assert((True or p.deleted).sql === "(true or p.deleted)")
  }

  they should "support boolean operators on boolean constants" in {
    assert(not(True).sql === "(not true)")
    assert((True or False).sql === "(true or false)")
    assert((True and False).sql === "(true and false)")
  }

  they should "support comparison operators" in {
    val p = People("p")
    assert((p.age ==== 4).sql === "(p.age = 4)")
    assert((p.age < 4).sql === "(p.age < 4)")
    assert((p.age <= 4).sql === "(p.age <= 4)")
    assert((p.age > 4).sql === "(p.age > 4)")
    assert((p.age >= 4).sql === "(p.age >= 4)")
  }

  they should "support string comparison" in {
    assert(const("hola").like(const("h%")).sql === "('hola' like 'h%')")
  }

  they should "support the 'in' operator with a constant value" in {
    val p = People("p")
    assert((p.name in ("john","jane")).sql === "p.name in ('john','jane')")
    assert((p.age in (18, 19)).sql === "p.age in (18,19)")
  }

  they should "support the 'in' operator with a paramter" in {
    val p = People("p")
    assert((p.age in param[Seq[Int]](0)).sql === "p.age in :0")
  }

  they should "support the 'coalesce' function" in {
    val p = People("p")
    assert(coalesce[Int](p.headOf, 23).sql === "coalesce(p.head_of,23)")
  }

  they should "support the is null expression" in {
    val p = People("p")
    assert(p.departmentId.isNull.sql === "p.dept_id is null")
  }

  they should "support complex predicates" in {
    val p = People("p")
    val pred = (StringColumnExpression(p.name) ==== "smith") or (p.age >= 16 and p.age <= 65) and (p.motherId ==== 6l)
    assert(pred.sql === "(((p.name = 'smith') or ((p.age >= 16) and (p.age <= 65))) and (p.mother_id = 6))")
  }

  behavior of "simple queries syntax"

  it should "build simple select queries" in {
    val d = Departments("d")
    val selectDepts = db.from(d).where(d.id ==== 3l).select(d.*)(d.reads)
    assert(selectDepts.sql === "select d.id, d.name from departments d where (d.id = 3)")
  }

  behavior of "joins syntax"

  private val allPAndDFields = " p.id, p.name, p.age, p.dept_id, " +
    "p.mother_id, p.inactive, p.deleted, p.uri, p.head_of, " +
    "d.id, d.name "

  it should "build join queries using a predicate" in {
    val (p, d) = (People("p"), Departments("d"))
    val joinPeopleDepts = db.from(p.join(d, p.departmentId ==== d.id))
    val selectPD = joinPeopleDepts.where(p.age >= 50).select(p.* ++ d.*)(p.reads.join(d.reads))
    assert(selectPD.sql === "select" + allPAndDFields +
      "from people p join departments d on (p.dept_id = d.id) where (p.age >= 50)")
  }

  it should "build left join queries using a predicate" in {
    val (p, d) = (People("p"), Departments("d"))
    val joinPeopleDepts = db.from(p.leftJoin(d, p.departmentId ==== d.id))
    val selectPD = joinPeopleDepts.where(p.age >= 50).select(p.id,d.id)
    assert(selectPD.sql === "select p.id, d.id " +
      "from people p left join departments d on (p.dept_id = d.id) where (p.age >= 50)")

  }

  it should "build join queries using a foreign key" in {
    val (p, d) = (People("p"), Departments("d"))
    val s = db.from(p.join(d, p.deptFk)).select(p.* ++ d.*)(p.reads.join(d.reads))
    assert(s.sql === "select" + allPAndDFields +  "from people p join departments d on (p.dept_id = d.id)")
  }

  it should "build join queries using the same table with 2 different aliases" in {
    val (c, m) = (People("c"), People("m"))

    val s = db.from(c.join(m, c.motherFK)).select(c.* ++ m.*)(c.reads.join(m.reads))

    assert(s.sql === "select c.id, c.name, c.age, c.dept_id, " +
      "c.mother_id, c.inactive, c.deleted, c.uri, c.head_of, " +
      "m.id, m.name, m.age, m.dept_id, m.mother_id, m.inactive, " +
      "m.deleted, m.uri, m.head_of " +
      "from people c join people m on (c.mother_id = m.id)")
  }

  it should "build queries with group by" in {
    val (d, e) = (Departments("d"), Employees("e"))

    val s = db.from(e.join(d, e.departmentId ==== d.id)).groupBy(d.name).select(d.name)
    assert(s.sql === "select d.name from employees e join departments d on (e.department_id = d.id) group by d.name")
    assert(s.sql === "select d.name from employees e join departments d on (e.department_id = d.id) group by d.name")
    val s2 = db.from(e.join(d, e.departmentId ==== d.id)).where(True).groupBy(d.name).select(d.name)
    assert(s2.sql === s.sql)
  }

  it should "build queries with order by clause" in {
    val (d, e) = (Departments("d"), Employees("e"))
    val s1 = db.from(e).orderBy(e.departmentId, e.name -> Desc).select(e.name)
    assert(s1.sql === "select e.name from employees e order by e.department_id, e.name desc")
    val s2 = db.from(e.join(d, e.departmentId ==== d.id)).orderBy(d.name, e.name -> Desc).select(e.name)
    assert(s2.sql === "select e.name from employees e join departments d on (e.department_id = d.id) order by d.name, e.name desc")
    val s3 = db.from(e).where(e.departmentId ==== 4l).orderBy(e.departmentId, e.name -> Desc).select(e.name)
    assert(s3.sql === "select e.name from employees e where (e.department_id = 4) order by e.department_id, e.name desc")
    val s4 = db.from(e).groupBy(e.departmentId, e.name).orderBy(e.departmentId, e.name).select(e.departmentId, e.name)
    assert(s4.sql === "select e.department_id, e.name from employees e group by e.department_id, e.name order by e.department_id, e.name")
  }

  behavior of "update"

  it should "build an update statement" in {
    val d = Departments("d")
    val stmt = db.update(d).set(d.name := param(0))
    assert(stmt.sql === "update departments d set name = :0")
    val stmt2 = stmt.where(d.id ==== 23l)
    assert(stmt2.sql === "update departments d set name = :0 where (d.id = 23)")
  }

  behavior of "delete"

  it should "build a delete statement" in{
    val d = Departments("d")
    val stmt = db.delete(d)
    assert(stmt.sql === "delete from departments d")
    val stmt2 = stmt.where(d.id ==== 23l)
    assert(stmt2.sql === "delete from departments d where (d.id = 23)")
  }

  behavior of "insert"

  it should "build an insert statement" in {
    val d = Departments("d")
    val stmt = db.insertInto(d).values(d.id := param(0), d.name := param(1))
    assert(stmt.sql === "insert into departments (id,name) values (:0,:1)")
  }

  behavior of "constants"

  they should "be identified to be passed as parameters when they are text" in {
    val d = Departments("d")
    val q = db.from(d).where(d.name ==== "--drop table employees" and d.name < "--boooh!").select(d.name)
    assert(q.sql === "select d.name from departments d where ((d.name = :c0) and (d.name < :c1))")
    assert(q.constants.size === 2)
    assert(q.constants(0).v === "--drop table employees")
    assert(q.constants(1).v === "--boooh!")
  }

  they should "be rewritten as their values when they are whitelisted text" in {
    val d = Departments("d")
    val q = db.from(d).where(d.name ==== "d1" and d.name < "abc").select(d.name)
    assert(q.sql === "select d.name from departments d where ((d.name = 'd1') and (d.name < 'abc'))")
    assert(q.constants.size === 0)
  }
}
