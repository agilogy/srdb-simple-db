package it

import com.agilogy.simpledb._
import com.agilogy.simpledb.dsl._

class DbTypeTest extends TestBase {

  import txController.inTransaction

  behavior of "numeric types"

  they should "support bigint constants and read them as Long" in {
    inTransaction {
      implicit tx =>
        val res: Long = fromNoTable.select(3l.as("a")).withoutParams().head
        assert(res === 3l)
    }
  }

  they should "support integer constants and read them as Int" in {
    inTransaction {
      implicit tx =>
        val res: Int = fromNoTable.select(3.as("a")).withoutParams().head
        assert(res === 3)
    }
  }

  they should "support real constants and read them as Float" in {
    inTransaction {
      implicit tx =>
        val res: Float = fromNoTable.select(1.2f.as("a")).withoutParams().head
        assert(res === 1.2f)
    }
  }

  they should "support double constants and read them as Double" in {
    inTransaction {
      implicit tx =>
        val res: Double = fromNoTable.select(1.2d.as("a")).withoutParams().head
        assert(res === 1.2d)
    }
  }

  they should "support numeric constants and read them as BigDecimal" in {
    inTransaction {
      implicit tx =>
        val res: BigDecimal = fromNoTable.select(BigDecimal("12.23").as("a")).withoutParams().head
        assert(res === BigDecimal("12.23"))
    }
  }

  they should "support numeric int constants and read them as BigInt" in {
    inTransaction {
      implicit tx =>
        val res: BigInt = fromNoTable.select(BigInt("12345678901234567890").as("a")).withoutParams().head
        assert(res === BigInt("12345678901234567890"))
    }
  }

  behavior of "text types"

  they should "support text constants and read them as String" in {
    inTransaction {
      implicit tx =>
        val res: String = fromNoTable.select("Hello".as("a")).withoutParams().head
        assert(res === "Hello")
    }
  }

  behavior of "boolean types"

  they should "support boolean constants and read them as Boolean" in {
    inTransaction {
      implicit tx =>
        val res: Boolean = fromNoTable.select(true.as("a")).withoutParams().head
        assert(res === true)
    }
  }

  behavior of "date and time types"

  ignore should "write DateTime params and read timestamps from the db" in {
    //    val dwdt = new DummyWithDateTimes("dwdt")
    //    val now = new DateTime()
    //    val id: Long = db.insertAndGetKey(dwdt,dwdt.dateTimeColumn := Some(now))(dwdt.id)
    //    val res = db.from(dwdt).where(dwdt.id ==== id).select(dwdt.dateTimeColumn).withoutParams().head.get
    //    assert(res === now)
  }

  ignore should "write DateTime params and read timestamps from the db when using different timezones" in {
    //    val dwdt = new DummyWithDateTimes("dwdt")
    //    val now = new DateTime().toDateTime(DateTimeZone.UTC)
    //    val id: Long = db.insertAndGetKey(dwdt,dwdt.dateTimeColumn := Some(now))(dwdt.id)
    //    val res = db.from(dwdt).where(dwdt.id ==== id).select(dwdt.dateTimeColumn).withoutParams().head.get.toDateTime(DateTimeZone.UTC)
    //    assert(res === now)
  }

  ignore should "write DateTime params and read timestamps from the db when using different timezones as a parameter" in {
    //    val dwdt = new DummyWithDateTimes("dwdt")
    //    val now = new DateTime().toDateTime(DateTimeZone.UTC)
    //    val insert = db.insertInto(dwdt).values(dwdt.dateTimeColumn := param(0)).andReadGeneratedKeys(dwdt.id).withParams(dwdt.dateTimeColumn.reader)
    //    val id = insert(Some(now))
    //    val res = db.from(dwdt).where(dwdt.id ==== id).select(dwdt.dateTimeColumn).withoutParams().head.get.toDateTime(DateTimeZone.UTC)
    //    assert(res === now)
  }

  ignore should "support timestamp constants and read them as DateTime" in {
    //    val now = new DateTime()
    //    val res: DateTime = fromNoTable.select(now.as("a")).withoutParams().head
    //    assert(res === now)
  }

  ignore should "write LocalDate params and read dates from the db" in {
    //    val dwdt = new DummyWithDateTimes("dwdt")
    //    val d = new LocalDate(2012, 2, 29)
    //    db.inTransaction {
    //      implicit tx =>
    //        val id: Long = db.insertAndGetKey(dwdt, dwdt.localDateColumn := Some(d))(dwdt.id)(tx)
    //        val res: LocalDate = db.from(dwdt).where(dwdt.id ==== id).select(dwdt.localDateColumn).withoutParams()(tx).head.get
    //        assert(res === d)
    //    }
  }

  ignore should "support date constants and read them as LocalDate" in {
    //    val d = new LocalDate(2012, 2, 29)
    //    val res: LocalDate = fromNoTable.select(d.as("a")).withoutParams().head
    //    assert(res === d)
  }

  ignore should "write LocalTime params and read time from the db" in {
    //    val dwdt = new DummyWithDateTimes("dwdt")
    //    val t = new LocalTime(23, 52, 26, 114)
    //    inTransaction {
    //      implicit tx =>
    //        val id: Long = db.insertAndGetKey(dwdt, dwdt.localTimeColumn := Some(t))(dwdt.id)
    //        val res: LocalTime = db.from(dwdt).where(dwdt.id ==== id).select(dwdt.localTimeColumn).withoutParams().head.get
    //        assert(res === t)
    //    }
  }

  ignore should "support time constants and read them as LocalTime" in {
    //    val t = new LocalTime(23, 52, 26, 114)
    //    val q = fromNoTable.select(t.as("a"))
    //    val res: LocalTime = q.withoutParams().head
    //    assert(res === t)
  }

  //TODO: Move to column type tests belong
  they should "support mapped constants" in {
    case class Name(v: String)
    // TODO: Make xmap return an instance of a new class MappedColumnType so that we can automatically infer the constant strategy
    implicit val nameColumnType = DbString.xmap[Name](Name.apply, _.v)
    implicit val nameConstantStrategy = stringConstantStrategy.map[Name](_.v)
    val john = Name("john :98")
    inTransaction {
      implicit tx =>
        val q = fromNoTable.select(john.as("n")).withoutParams
        val res: Name = q().head
        assert(res === john)
    }
  }
}
