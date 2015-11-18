package it

import com.agilogy.simpledb._
import com.agilogy.simpledb.dsl._
import com.agilogy.srdb.tx.{TransactionController, NewTransaction}

class DbTypeTest extends TestBase {

  implicit val txConfig = NewTransaction

  behavior of "numeric types"

  they should "support bigint constants and read them as Long" in {
    TransactionController.inTransaction(ds) {
      implicit tx =>
        val res: Long = db.fromNoTable.select(3l.as("a")).withoutParams().head
        assert(res === 3l)
    }
  }

  they should "support integer constants and read them as Int" in {
    TransactionController.inTransaction(ds) {
      implicit tx =>
        val res: Int = db.fromNoTable.select(3.as("a")).withoutParams().head
        assert(res === 3)
    }
  }

  they should "support real constants and read them as Float" in {
    TransactionController.inTransaction(ds) {
      implicit tx =>
        val res: Float = db.fromNoTable.select(1.2f.as("a")).withoutParams().head
        assert(res === 1.2f)
    }
  }

  they should "support double constants and read them as Double" in {
    TransactionController.inTransaction(ds) {
      implicit tx =>
        val res: Double = db.fromNoTable.select(1.2d.as("a")).withoutParams().head
        assert(res === 1.2d)
    }
  }

  they should "support numeric constants and read them as BigDecimal" in {
    TransactionController.inTransaction(ds) {
      implicit tx =>
        val res: BigDecimal = db.fromNoTable.select(BigDecimal("12.23").as("a")).withoutParams().head
        assert(res === BigDecimal("12.23"))
    }
  }

  they should "support numeric int constants and read them as BigInt" in {
    TransactionController.inTransaction(ds) {
      implicit tx =>
        val res: BigInt = db.fromNoTable.select(BigInt("12345678901234567890").as("a")).withoutParams().head
        assert(res === BigInt("12345678901234567890"))
    }
  }

  behavior of "text types"

  they should "support text constants and read them as String" in {
    TransactionController.inTransaction(ds) {
      implicit tx =>
        val res: String = db.fromNoTable.select("Hello".as("a")).withoutParams().head
        assert(res === "Hello")
    }
  }

  behavior of "boolean types"

  they should "support boolean constants and read them as Boolean" in {
    TransactionController.inTransaction(ds) {
      implicit tx =>
        val res: Boolean = db.fromNoTable.select(true.as("a")).withoutParams().head
        assert(res === true)
    }
  }

  behavior of "date and time types"

  they should "write DateTime params and read timestamps from the db" in {
//    val dwdt = new DummyWithDateTimes("dwdt")
//    val now = new DateTime()
//    val id: Long = db.insertAndGetKey(dwdt,dwdt.dateTimeColumn := Some(now))(dwdt.id)
//    val res = db.from(dwdt).where(dwdt.id ==== id).select(dwdt.dateTimeColumn).withoutParams().head.get
//    assert(res === now)
  }

  they should "write DateTime params and read timestamps from the db when using different timezones" in {
//    val dwdt = new DummyWithDateTimes("dwdt")
//    val now = new DateTime().toDateTime(DateTimeZone.UTC)
//    val id: Long = db.insertAndGetKey(dwdt,dwdt.dateTimeColumn := Some(now))(dwdt.id)
//    val res = db.from(dwdt).where(dwdt.id ==== id).select(dwdt.dateTimeColumn).withoutParams().head.get.toDateTime(DateTimeZone.UTC)
//    assert(res === now)
  }

  they should "write DateTime params and read timestamps from the db when using different timezones as a parameter" in {
//    val dwdt = new DummyWithDateTimes("dwdt")
//    val now = new DateTime().toDateTime(DateTimeZone.UTC)
//    val insert = db.insertInto(dwdt).values(dwdt.dateTimeColumn := param(0)).andReadGeneratedKeys(dwdt.id).withParams(dwdt.dateTimeColumn.reader)
//    val id = insert(Some(now))
//    val res = db.from(dwdt).where(dwdt.id ==== id).select(dwdt.dateTimeColumn).withoutParams().head.get.toDateTime(DateTimeZone.UTC)
//    assert(res === now)
  }

  they should "support timestamp constants and read them as DateTime" in {
//    val now = new DateTime()
//    val res: DateTime = db.fromNoTable.select(now.as("a")).withoutParams().head
//    assert(res === now)
  }

  they should "write LocalDate params and read dates from the db" in {
//    val dwdt = new DummyWithDateTimes("dwdt")
//    val d = new LocalDate(2012, 2, 29)
//    db.inTransaction {
//      implicit tx =>
//        val id: Long = db.insertAndGetKey(dwdt, dwdt.localDateColumn := Some(d))(dwdt.id)(tx)
//        val res: LocalDate = db.from(dwdt).where(dwdt.id ==== id).select(dwdt.localDateColumn).withoutParams()(tx).head.get
//        assert(res === d)
//    }
  }

  they should "support date constants and read them as LocalDate" in {
//    val d = new LocalDate(2012, 2, 29)
//    val res: LocalDate = db.fromNoTable.select(d.as("a")).withoutParams().head
//    assert(res === d)
  }

  they should "write LocalTime params and read time from the db" in {
//    val dwdt = new DummyWithDateTimes("dwdt")
//    val t = new LocalTime(23, 52, 26, 114)
//    TransactionController.inTransaction(ds) {
//      implicit tx =>
//        val id: Long = db.insertAndGetKey(dwdt, dwdt.localTimeColumn := Some(t))(dwdt.id)
//        val res: LocalTime = db.from(dwdt).where(dwdt.id ==== id).select(dwdt.localTimeColumn).withoutParams().head.get
//        assert(res === t)
//    }
  }

  they should "support time constants and read them as LocalTime" in {
//    val t = new LocalTime(23, 52, 26, 114)
//    val q = db.fromNoTable.select(t.as("a"))
//    val res: LocalTime = q.withoutParams().head
//    assert(res === t)
  }

  //TODO: Move to column type tests belong
  they should "support mapped constants" in {
    case class Name(v:String)
    // Make xmpar return an instance of a new class MappedColumnType so that we can automatically infer the constant strategy
    implicit val nameColumnType = DbString.xmap[Name](Name.apply,_.v)
    implicit val nameConstantStrategy = stringConstantStrategy.map[Name](_.v)
    val john = Name("john :98")
    TransactionController.inTransaction(ds) {
      implicit tx =>
        val q = db.fromNoTable.select(john.as("n")).withoutParams
        val res: Name = q().head
        assert(res === john)
    }
  }
}
