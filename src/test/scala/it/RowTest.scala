package it

import com.agilogy.simpledb.SimpleDb
import SimpleDb._
import Syntax._
import it.TestSchema.Planet

class RowTest extends TestBase{

  behavior of "Row"

  it should "retrieve values from the row (not necessarily inferring the DbType to use by the context)" in{
    val q = db.from(p).select(p.*)(reader(row => Planet(row.get[String]("name"),row.get[Int]("position"))))
  }

}
