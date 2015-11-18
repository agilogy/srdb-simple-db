package it

import com.agilogy.simpledb.SimpleDb._
import Syntax._
import com.agilogy.srdb.tx.NewTransaction

class AggregateFunctionsTest extends TestBase{

  import TestSchema._

  behavior of "aggregate functions"

  private def insertPlanets():Unit = {
    val p = Planets("p")
    db.inTransaction{
      implicit tx =>
        deletePlanets()
        db.insert(p, p.name := "Mercury", p.position := 1)
        db.insert(p, p.name := "Venus", p.position := 2)
        db.insert(p, p.name := "Earth", p.position := 3)
    }(NewTransaction)
  }

  they should "calculate the sum function" in{
    insertPlanets()
    val res = db.from(p).select(sum(p.position).as("a")).withoutParams()(NewTransaction).head
    assert(res === Some(6))
  }

  they should "calculate the min function" in{
    insertPlanets()
    val res = db.from(p).select(min(p.position).as("a")).withoutParams()(NewTransaction).head
    assert(res === Some(1))
  }

  they should "calculate the max function" in{
    insertPlanets()
    val res = db.from(p).select(max(p.position).as("a")).withoutParams()(NewTransaction).head
    assert(res === Some(3))
  }

  they should "calculate the count function" in{
    insertPlanets()
    val res = db.from(p).select(count(p.name).as("a")).withoutParams()(NewTransaction).head
    assert(res === 3)
  }

}
