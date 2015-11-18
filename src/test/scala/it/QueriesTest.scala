package it

import java.sql.ResultSet

import com.agilogy.simpledb._
import com.agilogy.srdb.types.AtomicNotNullPositionalDbReader

class QueriesTest extends TestBase {

  import TestSchema._
  import txController.inTransaction

  behavior of "queries"

  they should "build and execute simple queries without parameters" in {
    val selectPlanets = createQuery("select * from planets p")(p.reads).withoutParams

    inTransaction {
      implicit tx =>
        assert(selectPlanets() === List(venus))
    }
  }

  they should "select without declaring parameters" in {
    val selectPlanetByName = createQuery("select * from planets where name = :0")(p.reads)
    inTransaction {
      implicit tx =>
        val result = selectPlanetByName("venus").head
        assert(result.name === "venus")
    }
  }

  they should "select with 1 positional parameter" in {
    val selectPlanetByName = createQuery("select * from planets where name = :0")(p.reads).withParams[String]
    inTransaction {
      implicit tx =>
        val result = selectPlanetByName("venus").head
        assert(result.name === "venus")
    }
  }

  they should "select with 2 positional parameters" in {
    val selectPlanetByNameAndPosition = createQuery("select * from planets where name = :0 and position = :1")(p.reads).withParams[String,Int]
    inTransaction {
      implicit tx =>
        val result = selectPlanetByNameAndPosition("venus", 2).head
        assert(result.name === "venus")
    }
  }

  ignore should "build and execute join queries with join reads when names colide" in {
    val sql = "select * from employees e join departments d on e.department_id = d.id order by e.id,d.id"
    val q = createQuery(sql)(e.reads.joinOne(d.reads)).withoutParams
    inTransaction {
      implicit tx =>
        insertDepartmentsAndEmployees()
        // Notice static type:
        val result: Seq[(Employee, Department)] = q()
        val names = result.map { case (emp, dept) => emp.name -> dept.name}
        assert(names === List("emp1" -> "dept1", "emp2" -> "dept1", "emp3" -> "dept2"))
    }
  }

  they should "gracefully handle parameters at the end of line (regression)" in {
    val selectPlanetByNameAndPosition = createQuery("select * from planets where name = :0\n and position = :1")(p.reads).withParams[String,Int]
    inTransaction {
      implicit tx =>
        val result = selectPlanetByNameAndPosition("venus", 2).head
        assert(result.name === "venus")
    }
  }

  behavior of "in clauses"

  they should "be allowed in queries" in {
    val getPlanetsWherePositionIn = createQuery("select * from planets where position in :0 order by position")(p.reads).withParams(InClauseValuesDbWriter[Int])
    inTransaction {
      implicit tx =>
        Ops.insert(p, p.position := 3, p.name := "earth")
        val result = getPlanetsWherePositionIn(Seq(2, 3))
        assert(result.map(_.name) === Seq("venus", "earth"))
    }
  }

  behavior of "scalar"

  it should "be a reads that reads the only one column" in {
    val selectFirstPlanetPosition = createQuery("select position from planets where position = (select min(position) from planets)")(reader1[Int]).withoutParams
    inTransaction {
      implicit tx =>
        val position: Int = selectFirstPlanetPosition().head
        assert(position === 2)
    }
  }

  //  it should "handle java.math.BigDecimal values when inserting" in {
  //    import com.agilogy.simpleRuntimeColumns._
  //    val bd = new java.math.BigDecimal("0.2")
  //    val id: Int = insertAndGetKey("table_with_numeric", Map("number" -> bd))
  //    val result = selectNumericById(id)
  //    assert(result.size === 1)
  //    assert(result(0).bigDecimal === bd)
  //  }
  //

//  val dwdt = DummyWithDateTimes("dwdt")
//  val selectTimeStampById = from(dwdt).where(dwdt.id ==== param(0)).select(dwdt.id,dwdt.localDateColumn,dwdt.dateTimeColumn,dwdt.localTimeColumn).withParams[Long]

//  it should "handle org.joda.time.DateTime when inserting" in {
//    val dt = new DateTime()
//    inTransaction {
//      implicit tx =>
//        val id: Long = insertAndGetKey(dwdt, dwdt.dateTimeColumn := Some(dt))(_.get(dwdt.id))
//        val result = selectTimeStampById(id)
//        assert(result.size === 1)
//        assert(result(0)._3.get === dt)
//    }
//  }
//
//
//  it should "handle org.joda.time.DateTime really in the past when inserting" in {
//    val dt = new DateTime(14,1,1,12,23,34)
//    inTransaction {
//      implicit tx =>
//        val id: Long = insertAndGetKey(dwdt, dwdt.dateTimeColumn := Some(dt))(_.get(dwdt.id))
//        val result = selectTimeStampById(id)
//        assert(result.size === 1)
//        assert(result(0)._3.get === dt)
//    }
//  }

  //
  //  val selectByTimeStamp = createQuery("select id from table_with_timestamp where since_date = :0").as(scalar[Int]).build(timestamp)
  //
  //  it should "handle org.joda.time.DateTime as a TimeStamp" in {
  //    import com.agilogy.simpleRuntimeColumns._
  //    val dt = new DateTime()
  //    val id: Int = insertAndGetKey("table_with_timestamp", Map("since_date" -> dt))
  //    val result = selectByTimeStamp(dt)
  //    assert(result.size === 1)
  //    assert(result(0) === id)
  //  }
  //
  //  val selectByDateTime = createQuery("select id from table_with_timestamp where date_trunc('day', since_date) = :0").as(scalar[Int]).build(timestamp)
  //
  //  it should "handle a search on sql.TimeStamp as a org.joda.time.DateTime" in {
  //    import com.agilogy.simpleRuntimeColumns._
  //    val dt = new DateTime(2013, 1, 13, 12, 12)
  //    val id: Int = insertAndGetKey("table_with_timestamp", Map("since_date" -> dt))
  //    val ndt = new DateTime(2013, 1, 13, 0, 0)
  //    val result = selectByDateTime(ndt)
  //    assert(result.size === 1)
  //    assert(result(0) === id)
  //  }
  //
  //  val selectByDate = createQuery("select since_date from table_with_date where since_date = :0").as(scalar[LocalDate]).build(date)
  //
  //  it should "handle a org.joda.time.LocalDate as a sql.Date " in {
  //    import com.agilogy.simpleRuntimeColumns._
  //    val ld = new LocalDate()
  //    insert("table_with_date", Map("since_date" -> ld))
  //    val result = selectByDate(ld).head
  //    assert(result === ld)
  //  }
  //
  //  it should "handle an odd date " in {
  //    import RuntimeColumns._
  //    val inputDate = LocalDate.parse("00140615", ISODateTimeFormat.basicDate())
  //    insert("table_with_date", Map("since_date" -> inputDate))
  //    val result = selectByDate(inputDate).head
  //    assert(result === inputDate)
  //  }
  //
  //  val selectByDateOnTimeStamp = createQuery("select id from table_with_timestamp where date_trunc('day', since_date) = :0").as(scalar[Int]).build(date)
  //
  //  it should "handle a search on sql.TimeStamp as a org.joda.time.LocalDate" in {
  //    import com.agilogy.simpleRuntimeColumns._
  //    val dt = new DateTime(2013, 1, 14, 12, 12)
  //    val id: Int = insertAndGetKey("table_with_timestamp", Map("since_date" -> dt))
  //    val ld = new LocalDate(2013, 1, 14)
  //    val result = selectByDateOnTimeStamp(ld)
  //    assert(result.size === 1)
  //    assert(result(0) === id)
  //  }
  //
  //  it should "ignore fields with value None on insertion" in {
  //    import com.agilogy.simpleRuntimeColumns._
  //    val dept = Map("name" -> "dept", "code" -> 23, "employees" -> None)
  //    insert("departments", dept)
  //    val result = selectDepartments()
  //    assert(result.size === 1)
  //    val resultingDept = result.head
  //    assert(resultingDept("employees") === 0)
  //  }
  //
  //  it should "set field values when given Option values on insertion" in {
  //    import com.agilogy.simpleRuntimeColumns._
  //    val dept = Map("name" -> "dept", "code" -> 23, "employees" -> Some(12))
  //    insert("departments", dept)
  //    val result = selectDepartments()
  //    assert(result.size === 1)
  //    val resultingDept = result.head
  //    assert(resultingDept("employees") === 12)
  //  }

  behavior of "error handling"

  ignore should "handle errors reading unexisting columns in query results" in {
    val d = Departments("d")
    //TODO: Allow using a column directly instead of a reader
    val selectDept = createQuery("select id, name from departments where id = :0")(e.departmentId.reader).withParams[Long]
    inTransaction {
      implicit tx =>
        val deptId = Ops.insertAndGetKey(d, d.name := "Marketing")(d.id)
        val exc = intercept[ColumnReadException] {
          selectDept(deptId).head
        }
        assert(exc.column === "department_id")
//        assert(exc.reader.typeName === "BOOLEAN")
    }
  }

  ignore should "handle errors reading columns of wrong type in query results" in {
    val d = Departments("d")
    val dept = Map("name" -> "Marketing", "code" -> "mkt")
    //TODO: Rename on of the notNull functions!
    val selectDept = createQuery("select id, name from departments where id = :0")(notNull[Int]("name")).withParams[Long]

    inTransaction {
      implicit tx =>
        val deptId = Ops.insertAndGetKey(d, d.name := "Marketing")(d.id)
        val exc = try {
          selectDept(deptId).head
          fail("Should have thrown an exception")
        } catch {
          case e: ColumnReadException => e
        }
        assert(exc.column === "name")
//        assert(exc.reader.typeName === "TIMESTAMPTZ")
    }
  }

  ignore should "handle errors reading null columns as not null in query results" in {
    val selectDept = createQuery("select id, name, code from departments where id = :0")(d.code.positionalReader.notNull).withParams[Long]
    inTransaction {
      implicit tx =>
        val deptId = Ops.insertAndGetKey(d, d.name := "Marketing")(d.id)
        val exc = intercept[NullColumnReadException] {
          selectDept(deptId).head
        }
        assert(exc.column === "code")
    }
  }

  ignore should "handle mapping errors" in {
    val boomReader = new AtomicNotNullPositionalDbReader[String](){
      override def get(rs: ResultSet, pos: Int): String = throw new RuntimeException("boom!")
    }
    inTransaction {
      implicit tx =>
        val deptId = Ops.insertAndGetKey(d, d.name := "Marketing", d.code := Some("mkt"))(d.id)
        val selectDept = createQuery("select id, name, code from departments where id = :0")(boomReader).withParams[Long]
        val exc = try {
          selectDept(deptId).head
          fail("Should have thrown an exception")
        } catch {
          case e: RowMappingException => e
        }
        assert(exc.row("id") === Some(deptId))
        assert(exc.row("code") === Some("mkt"))
    }
  }

  ignore should "handle query errors executing queries" in {
    val sQuery = "this is not sql"
    val q1 = createQuery(sQuery)(reader1[Int]).withoutParams
    inTransaction {
      implicit tx =>
        val exc = intercept[DbException] {
          q1()
        }
        assert(exc.getMessage.startsWith(s"Error executing query"))
        assert(exc.sql === sQuery)
    }
  }


  //
  //  it should "handle datetime format in UTC time zone" in {
  //    import com.agilogy.simpleRuntimeColumns._
  //    val dt = DateTime.now(DateTimeZone.forOffsetHours(8))
  //    val id: Int = insertAndGetKey("table_with_timestamp", Map("since_date" -> dt))
  //    val result = selectTimeStampById(id)
  //    assert(result.size === 1)
  //    assert(result(0) === dt.withZone(DateTimeZone.UTC))
  //  }
  //
  //
  //  val selectTimeById = createQuery("select my_time from table_with_time where id = :0").as(scalar[LocalTime]).build(integer)
  //
  //  it should "handle org.joda.time.LocalTime when inserting" in {
  //    import com.agilogy.simpleRuntimeColumns._
  //    val lt = LocalTime.now(DateTimeZone.UTC)
  //    val id: Int = insertAndGetKey("table_with_time", Map("my_time" -> lt))
  //    val result = selectTimeById(id)
  //    assert(result.size === 1)
  //    assert(result(0) === lt)
  //  }
  //
  //  val selectByTime = createQuery("select id from table_with_time where my_time = :0").as(scalar[Int]).build(time)
  //
  //  it should "handle org.joda.time.LocalTime as a Time" in {
  //    import com.agilogy.simpleRuntimeColumns._
  //    val lt = LocalTime.now(DateTimeZone.UTC)
  //    val id: Int = insertAndGetKey("table_with_time", Map("my_time" -> lt))
  //    val result = selectByTime(lt)
  //    assert(result.size === 1)
  //    assert(result(0) === id)
  //  }


  def insertDeparmentsWithNumerics(): (Long, Long, Long) = {
    inTransaction {
      implicit tx =>
        val deptId1 = Ops.insertAndGetKey(d, d.name := "dept1")(d.id)
        Ops.insert(w, w.id := deptId1, w.number := BigDecimal(11))
        Ops.insert(w, w.id := deptId1, w.number := BigDecimal(12))
        val deptId2 = Ops.insertAndGetKey(d, d.name := "dept2")(d.id)
        Ops.insert(w, w.id := deptId2, w.number := BigDecimal(21))
        val deptId3 = Ops.insertAndGetKey(d, d.name := "dept3")(d.id)
        (deptId1, deptId2, deptId3)
    }
  }


  behavior of "join readers"

  it should "read left join queries" in {
    val (deptId1, deptId2, deptId3) = insertDeparmentsWithNumerics()
    val selectLeftJoinSql = "select * from departments d left join table_with_numeric w on d.id = w.id order by d.id"
    val selectLeftJoin = createQuery(selectLeftJoinSql)(d.reads.join(w.reads)).withoutParams
    inTransaction {
      implicit tx =>
        val result = selectLeftJoin()
        assert(result.size === 3, s"There should be 3 results in $result")
        assert(result(0)._1.id === deptId1)
        assert(result(0)._2 === Seq(WithNumeric(deptId1, 11), WithNumeric(deptId1, 12)))
        assert(result(1)._1.id === deptId2)
        assert(result(1)._2 === Seq(WithNumeric(deptId2, 21)))
        assert(result(2)._1.id === deptId3)
        assert(result(2)._2 === Seq())
    }
  }

  it should "read join queries" in {
    val (deptId1, deptId2, _) = insertDeparmentsWithNumerics()
    val selectJoinSql = "select * from departments d join table_with_numeric w on d.id = w.id"
    val selectJoin = createQuery(selectJoinSql)(d.reads.join(w.reads)).withoutParams
    inTransaction {
      implicit tx =>
        val result = selectJoin()
        assert(result.size === 2)
        assert(result(0)._1.id === deptId1)
        assert(result(0)._2 === Seq(WithNumeric(deptId1, 11), WithNumeric(deptId1, 12)))
        assert(result(1)._1.id === deptId2)
        assert(result(1)._2 === Seq(WithNumeric(deptId2, 21)))
    }
  }

  it should "read join to one queries" in {
    val (deptId1, deptId2, _) = insertDeparmentsWithNumerics()
    val selectJoinSql = "select * from departments d join table_with_numeric w on d.id = w.id"
    val selectJoin = createQuery(selectJoinSql)(d.reads.joinOne(w.reads)).withoutParams
    inTransaction {
      implicit tx =>
        val result = selectJoin()
        assert(result.size === 3)
        assert(result(0)._1.id === deptId1)
        assert(result(0)._2 === WithNumeric(deptId1, 11))
        assert(result(1)._1.id === deptId1)
        assert(result(1)._2 === WithNumeric(deptId1, 12))
        assert(result(2)._1.id === deptId2)
        assert(result(2)._2 === WithNumeric(deptId2, 21))
    }
  }


  it should "read left join to one queries" in {
    val (deptId1, deptId2, deptId3) = insertDeparmentsWithNumerics()
    val selectJoinSql = "select * from departments d left join table_with_numeric w on d.id = w.id order by d.id"
    val selectJoin = createQuery(selectJoinSql)(d.reads.leftJoinOne(w.reads)).withoutParams
    inTransaction {
      implicit tx =>
        val result = selectJoin()
        assert(result.size === 4)
        assert(result(0)._1.id === deptId1)
        assert(result(0)._2 === Some(WithNumeric(deptId1, 11)))
        assert(result(1)._1.id === deptId1)
        assert(result(1)._2 === Some(WithNumeric(deptId1, 12)))
        assert(result(2)._1.id === deptId2)
        assert(result(2)._2 === Some(WithNumeric(deptId2, 21)))
        assert(result(3)._1.id === deptId3)
        assert(result(3)._2 === None)
    }
  }

  //TODO: Make this work... but test in srdb-types, probably
  it should "read using 2 joins" in {
//    val d = Departments("d")
//    val e = Employees("e")
//    val efm = EmployeeFamilyMembers("efm")
//    inTransaction {
//      implicit tx =>
//        val d1 = insertAndGetKey(d, d.name := "d1")(d.id)
//        val e11 = insertAndGetKey(e, e.departmentId := d1, e.name := "e11")(e.id)
//        val f111 = insertAndGetKey(efm, efm.employeeId := e11, efm.name := "f111")(efm.id)
//        val f112 = insertAndGetKey(efm, efm.employeeId := e11, efm.name := "f112")(efm.id)
//        val e12 = insertAndGetKey(e, e.departmentId := d1, e.name := "e12")(e.id)
//        //    val d2 = insertAndGetKey(d,d.name := "d2")(_.get(d.id))
//
//        //TODO: it should be namedReader or something similar
//        val readDept = d.name.as("d_name").reader
//        val readEmp = e.name.as("e_name").reader
//        val readF = efm.name.as("efm_name").reader
//
//        //    println(createQuery("select d.name as d_name, e.name as e_name from departments d join employees e on d.id = e.department_id")(reader(r => r.get(d.name.as("d_name")) -> r.get(e.name.as("e_name")))).withoutParams.apply())
//        val sql = "select d.name as d_name, e.name as e_name, efm.name as efm_name " +
//          "from departments d join employees e on d.id = e.department_id " +
//          "left join employee_family_members efm on e.id = efm.employee_id " +
//          "order by d.name, e.name, efm.name"
//
//        val q1: Query0[(String, Seq[(String, Seq[String])])] = createQuery(sql)(readDept.leftJoin(readEmp.leftJoin(readF))).withoutParams
//        val res1 = q1()
//        assert(res1 === Seq("d1" -> Seq("e11" -> Seq("f111", "f112"), "e12" -> Seq.empty)))
//
//        val q2: Query0[(String, Seq[(String, Option[String])])] = createQuery(sql)(readDept.leftJoin(readEmp.leftJoinOne(readF))).withoutParams
//        val res2 = q2()
//        assert(res2 === Seq("d1" -> Seq("e11" -> Some("f111"), "e11" -> Some("f112"), "e12" -> None)))
//
//        val q3: Query0[(String, (String, Seq[String]))] = createQuery(sql)(readDept.joinOne(readEmp.leftJoin(readF))).withoutParams
//        val res3 = q3()
//        assert(res3 === Seq("d1" -> ("e11" -> Seq("f111", "f112")), "d1" -> ("e12" -> Seq.empty)))
//    }
  }

}
