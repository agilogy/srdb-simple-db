package it

import java.sql.ResultSet

import com.agilogy.simpledb._
import com.agilogy.srdb.exceptions.DbException
import com.agilogy.srdb.types.{ NullColumnReadException, ColumnReadException, AtomicNotNullPositionalDbReader }
import it.TestSchema.Departments

class ErrorHandlingTest extends TestBase {

  import txController.inTransaction

  behavior of "error handling"

  it should "handle errors reading unexisting columns in query results" in {
    val d = Departments("d")
    //TODO: Allow using a column directly instead of a reader
    val selectDept = createQuery("select id, name from departments where id = :0")(e.departmentId.reader).withParams[Long]
    inTransaction {
      implicit tx =>
        val deptId = Ops.insertAndGetKey(d, d.name := "Marketing")(d.id)
        val exc = intercept[ColumnReadException] {
          selectDept(deptId).head
        }
        assert(exc.columnName === "department_id")
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
        assert(exc.columnName === "name")
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
        assert(exc.columnName === "code")
    }
  }

  ignore should "handle mapping errors" in {
    val boomReader = new AtomicNotNullPositionalDbReader[String]() {
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
          case e: ColumnReadException => e
        }
        assert(exc.availableColumns.get.find(_._1 == "id").get._2 === Some(deptId))
        assert(exc.availableColumns.get.find(_._1 == "code").get._2 === Some("mkt"))
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

}
