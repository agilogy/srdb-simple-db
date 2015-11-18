package it

import com.agilogy.simpledb._
import com.agilogy.simpledb.dsl._

class AggregateFunctionsTest extends TestBase{

  import TestSchema._
  import txController.inTransaction

  behavior of "aggregate functions"

  private def insertPlanets():Unit = {
    val p = Planets("p")
    inTransaction {
      implicit tx =>
        deletePlanets()
        Ops.insert(p, p.name := "Mercury", p.position := 1)
        Ops.insert(p, p.name := "Venus", p.position := 2)
        Ops.insert(p, p.name := "Earth", p.position := 3)
    }
  }

  they should "calculate the sum function" in{
    insertPlanets()
    inTransaction{
      implicit tx =>
        val res = from(p).select(sum(p.position).as("a")).withoutParams().head
        assert(res === Some(6))
    }
  }

  they should "calculate the min function" in{
    insertPlanets()
    inTransaction {
      implicit tx =>
        val res = from(p).select(min(p.position).as("a")).withoutParams().head
        assert(res === Some(1))
    }
  }

  they should "calculate the max function" in{
    insertPlanets()
    inTransaction {
      implicit tx =>
        val res = from(p).select(max(p.position).as("a")).withoutParams().head
        assert(res === Some(3))
    }
  }

  they should "calculate the count function" in{
    insertPlanets()
    inTransaction {
      implicit tx =>
        val res = from(p).select(count(p.name).as("a")).withoutParams().head
        assert(res === 3)
    }
  }

}
