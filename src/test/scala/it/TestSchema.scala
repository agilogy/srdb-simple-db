package it

import com.agilogy.simpledb.{SimpleReads, Row}
import com.agilogy.simpledb.SimpleDb._
import com.agilogy.simpledb.schema.{PrimaryKey1, PrimaryKey, Table}
import org.joda.time.{LocalDate, DateTime, LocalTime}

object TestSchema {

  case class Planet(name: String, position: Int)

  case class Planets(alias: String) extends Table {
    val tableName = "Planets"
    val name = notNull[String]("name")
    val position = notNull[Int]("position")

    implicit val reads: SimpleReads[Planet] = reader(row => Planet(row.get(name), row.get(position)))
    override val primaryKey: Seq[Column[_]] = Seq(name)
  }

  private val p = Planets("p")
  implicit val planetReads = p.reads

  case class Department(id: Long, name: String, city: String = "Vilafranca", code: Option[String] = None, active: Boolean = true)

  case class Departments(alias: String) extends Table {
    val tableName = "departments"

    val id = notNull[Long]("id")
    val name = notNull[String]("name")
    val city = notNull[String]("city")
    val code = optional[String]("code")
    val active = notNull[Boolean]("active")
    val head = optional[Long]("head_id")

    val reads = reader(row => Department(row.get(id), row.get(name), row.get(city), row.get(code), row.get(active)))
    override val primaryKey: Seq[Column[_]] = Seq(id)
  }

  private val d = Departments("d")
  implicit val departmentReads = d.reads

  case class Employee(id: Long, name: String, departmentId: Long)

  case class Employees(alias: String) extends Table {
    val tableName = "employees"

    val id = notNull[Long]("id")
    val name = notNull[String]("name")
    val departmentId = notNull[Long]("department_id")

    val reads = reader(row => Employee(row.get(id), row.get(name), row.get(departmentId)))
    override val primaryKey: Seq[Column[_]] = Seq(id)
  }

  private val e = Employees("e")
  implicit val employeeReads = e.reads

  case class WithNumeric(id: BigInt, number: BigDecimal)

  case class WithNumerics(alias: String) extends Table {
    val tableName = "table_with_numeric"

    val id = notNull[Long]("id")
    val number = notNull[BigDecimal]("number")

    val reads = reader((row: Row) => WithNumeric(row.get(id), row.get(number)))
    override val primaryKey: Seq[Column[_]] = Seq(id)
  }

  case class EmployeeFamilyMember(id:Long, employeeId:Long, name:String)

  case class EmployeeFamilyMembers(alias:String) extends Table {
    val id = notNull[Long]("id")
    val employeeId = notNull[Long]("employee_id")
    val name = notNull[String]("name")

    override val tableName: String = "employee_family_members"
    override val primaryKey: Seq[Column[_]] = Seq(id)

    val reads = reader(row => EmployeeFamilyMember(row.get(id), row.get(employeeId), row.get(name)))
  }

  private val w = WithNumerics("w")
  implicit val withNumericReads = w.reads

  case class DummyWithDateTimes(alias: String = "dwdt") extends Table {
    override val tableName: String = "dummy_with_date_times"
    val id = notNull[Long]("id")
    val localDateColumn = optional[LocalDate]("d")
    val dateTimeColumn = optional[DateTime]("ts")
    val localTimeColumn = optional[LocalTime]("t")
    override val primaryKey: Seq[Column[_]] = Seq(id)
  }


}
