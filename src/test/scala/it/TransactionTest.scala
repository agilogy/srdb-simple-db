package it

import com.agilogy.simpledb._
import com.agilogy.srdb.tx.NewTransaction

import scala.util.control.NonFatal

//TODO: Move to srdb-tx
class TransactionTest extends TestBase {

  import TestSchema._
  import txController.inTransaction

  behavior of "Transaction"

  it should "execute multiple statements in a transaction" in {
    val updatePlanetPosition = createStatement("update planets set position = :1 where name = :0").withParams[String,Int]
    inTransaction {
      implicit tx =>
        updatePlanetPosition("venus", 5)
        Ops.insert(p, p.name := "earth", p.position := 3)
        tx.rollback()
    }(NewTransaction)
    inTransaction {
      implicit tx =>
        assert(selectPlanetByName("venus").head.position === 2)
        assert(selectPlanetByName("earth").headOption === None)
    }(NewTransaction)
  }

  behavior of "TransactionConfig"

  it should "allow nested inTransaction blocks" in {
    //TODO: Check that everything gets indeed executed in a single transaction
    inTransaction {
      tx =>
        inTransaction {
          implicit tx2 =>
            assert(tx2 === tx)
        }(tx)
    }(NewTransaction)
  }

  behavior of "C3P0 logs"

  ignore should "not trace a WARN 'Another error has occurred' when a connection marked as invalid is reused" in {
    val p = Planets("p")

    // ATTENTION: in order to make this test pass it is necessary to have a c3p0.properties specifying
    // what logging implementation to use, and then have that implementation setup and tuned to
    // set ERROR level for ...NewPooledConnection.

    inTransaction {
      implicit tx =>
        Ops.insert(p, p.name := "Earth", p.position := 3)
        tx.withSavepoint {
          Ops.insert(p, p.name := "Earth", p.position := 3)
          // throws a SQLException that marks the connection as INVALID
        } {
          case s: DbException =>
            Ops.insert(p, p.name := "Mercury", p.position := 1)
          // tx.withSavePoint causes the Connection to be valid
          // again but c3p0 already marked it as invalid and the
          // mark is not removed.
        }
        tx.withSavepoint {
          Ops.insert(p, p.name := "Earth", p.position := 3)
          // throws a SQLException once again. This time, because the
          // connection is marked as invalid in c3p0 the exception handling
          // in c3p0 logs.warn("Another error has occurred...").
          // This test only asserts this behavior and should be
          // reviewed if the exception is traced again.
        } {
          case s: DbException =>
            Ops.insert(p, p.name := "Venus", p.position := 2)
        }
    }(NewTransaction)
  }

  behavior of "Savepoints"

  they should "allow me to continue using a transaction after a failure" in {
    val p = Planets("p")
    inTransaction {
      implicit tx =>
        Ops.insert(p, p.name := "Earth", p.position := 3)
        tx.withSavepoint {
          Ops.insert(p, p.name := "Earth", p.position := 3)
        } {
          case NonFatal(_) =>
            Ops.insert(p, p.name := "Mercury", p.position := 1)
        }

        val m = selectPlanetByName("Mercury").head
        assert(m.name === "Mercury")
    }(NewTransaction)
  }

  they should "not mess things up when there are no errors" in {
    val p = Planets("p")
    inTransaction {
      implicit tx =>
        tx.withSavepoint {
          Ops.insert(p, p.name := "Earth", p.position := 3)
        } {
          case NonFatal(t) =>
            throw t;
        }
        val m = selectPlanetByName("Earth").head
        assert(m.name === "Earth")

    }(NewTransaction)

  }

  ignore should "not rollback to the savepoint if the exceptions is not in the catch block of withSavepoint" in {
    val p = Planets("p")
    inTransaction {
      implicit tx =>

        Ops.insert(p, p.name := "Earth", p.position := 3)
        val duplicateKeyException = intercept[Exception] {
          tx.withSavepoint {
            Ops.insert(p, p.name := "Earth", p.position := 3)
          } {
            case NonFatal(t) if t.getMessage == "This is not a real message" =>
              fail("withSavepoint should not give the catch block a different exception than the one that occurred")
          }
        }
        assert(duplicateKeyException.getMessage.contains("duplicate key value violates unique constraint"))

        val txAbortedException = intercept[Exception] {
          Ops.insert(p, p.name := "Mercury", p.position := 1)
        }
        assert(txAbortedException.getMessage.contains("current transaction is aborted"))
    }(NewTransaction)
  }

}
