package it

import com.agilogy.simpledb.{Measures, Database}
import com.agilogy.srdb.tx.NewTransaction
import org.scalatest.PrivateMethodTester
//TODO: Avoid this import
import com.agilogy.srdb.types.SimpleDbCursorReader._

class MeasuresTest extends TestBase with PrivateMethodTester {

  import TestSchema._
  import db._

  behavior of "database measurement"

  implicit private val txConfig = NewTransaction

  val selectPlanets = createQuery[Planet]("select * from planets p")(p.reads).withoutParams

  ignore should "measure one database query" in {
    val measurement = db.inTransaction {
      tx =>
        val (_, measurement) = Database.withMeasures {
          selectPlanets()(tx)
        }
        measurement
    }
    assert(measurement.measures.size === 1)
  }

  ignore should "measure several database queries" in {
    db.inTransaction {
      tx =>
        val (_, measurement) = Database.withMeasures {
          selectPlanets()(tx)
          selectPlanets()(tx)
          selectPlanets()(tx)
          selectPlanets()(tx)
          selectPlanets()(tx)
        }
        assert(measurement.measures.size === 5)
    }
  }

  ignore should "restart measures for each taken measure" in {
    db.inTransaction {
      tx =>
        val (_, measurement1) = Database.withMeasures {
          selectPlanets()(tx)
          selectPlanets()(tx)
          selectPlanets()(tx)
          selectPlanets()(tx)
          selectPlanets()(tx)
        }
        assert(measurement1.measures.size === 5)
        val (_, measurement2) = Database.withMeasures {
          selectPlanets()(tx)
          selectPlanets()(tx)
          selectPlanets()(tx)
          selectPlanets()(tx)
          selectPlanets()(tx)
        }
        assert(measurement2.measures.size === 5)
    }
  }

  ignore should "free memory after measure is performed" in {
    db.inTransaction {
      tx =>
        Database.withMeasures {
          selectPlanets()(tx)
          selectPlanets()(tx)
          selectPlanets()(tx)
          selectPlanets()(tx)
          selectPlanets()(tx)
        }
        val privateMeasures = Database invokePrivate PrivateMethod[scala.util.DynamicVariable[Measures]]('measure)()
        assert(privateMeasures.value.measures.size === 0)
    }
  }

  ignore should "only measure queries performed during measure block when nested measures are taken" in {
    db.inTransaction {
      tx =>
        val (_, measurement) = Database.withMeasures {
          selectPlanets()(tx)
          val (_, measurement) = Database.withMeasures {
            selectPlanets()(tx)
            val (_, measurement) = Database.withMeasures {
              selectPlanets()(tx)
              val (_, measurement) = Database.withMeasures {
                selectPlanets()(tx)
                val (_, measurement) = Database.withMeasures {
                  selectPlanets()(tx)
                }
                assert(measurement.measures.size === 1)
              }
              assert(measurement.measures.size === 2)
            }
            assert(measurement.measures.size === 3)
          }
          assert(measurement.measures.size === 4)
        }
        assert(measurement.measures.size === 5)
    }
  }
}