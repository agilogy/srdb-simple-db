package it

import com.agilogy.simpledb._

class QueryStreamsTest extends TestBase {

  import TestSchema._
  import txController.inTransaction
  implicit val txControllerImplicit = txController

  behavior of "query streams"

  they should "build and stream simple queries" in {
    inTransaction {
      implicit tx =>
        insertDepartments()
    }
    val selectDepartments = createQuery("select * from departments d where d.name = :0 or d.city = :1")(d.reads).withParams(d.name.writer, d.city.writer)
    val deptsStream = selectDepartments.stream("sales", "Vilafranca")
    val result = deptsStream.toSeq
    assert(result.map(_.name) === List("d1", "d2"))
  }

  they should "map a stream to get another stream" in {
    inTransaction {
      implicit tx =>
        insertDepartments()
    }
    val selectDepartments = createQuery("select * from departments d where d.name = :0 or d.city = :1")(d.reads).withParams(d.name.writer, d.city.writer)
    val deptsStream = selectDepartments.stream("sales", "Vilafranca").map(_.name)
    val result = deptsStream.toSeq
    assert(result === List("d1", "d2"))
  }

  they should "filter and then map a stream to get another stream" in {
    inTransaction {
      implicit tx =>
        insertDepartments()
    }
    val selectDepartments = createQuery("select * from departments d")(d.reads).withoutParams
    val deptsStream = selectDepartments.stream().filter(dep => dep.city == "Barcelona").map(_.name)
    val result = deptsStream.toSeq
    assert(result === List("d3"))
  }

  they should "map and then filter a stream to get another stream" in {
    inTransaction {
      implicit tx =>
        insertDepartments()
    }
    val selectDepartments = createQuery("select * from departments d")(d.reads).withoutParams
    val deptsStream = selectDepartments.stream().map(_.city).filter(_.startsWith("B"))
    val result = deptsStream.toSeq
    assert(result === List("Barcelona"))
  }

  they should "collect a stream to get another stream" in {
    inTransaction {
      implicit tx =>
        insertDepartments()
    }
    val selectDepartments = createQuery("select * from departments d")(d.reads).withoutParams
    val deptsStream = selectDepartments.stream().collect {
      case Department(_, name, "Barcelona", _, _) => s"$name - Barcelona"
      case Department(_, "d1", "Vilafranca", _, _) => "d1 - Penedès"
    }
    val result = deptsStream.toSeq
    assert(result === List("d1 - Penedès", "d3 - Barcelona"))
  }

  they should "zip a stream with index" in {
    val departmentIds = inTransaction { implicit tx =>
      insertDepartments()
    }
    val selectDepartments = createQuery("select * from departments d")(d.reads).withoutParams
    val stream = selectDepartments.stream().zipWithIndex
    val result = stream.toSeq
    assert(result.map(_._2) === 0.to(2))
    assert(result.map(_._1.id) === Seq(departmentIds._1, departmentIds._2, departmentIds._3))
  }

  they should "group" in {
    val departmentIds = inTransaction { implicit tx =>
      insertDepartments()
    }
    val selectDepartments = createQuery("select * from departments d")(d.reads).withoutParams
    val stream = selectDepartments.stream().grouped(1)
    val result = stream.toSeq
    assert(result.map(_.map(_.id)) === Seq(Seq(departmentIds._1), Seq(departmentIds._2), Seq(departmentIds._3)))
  }

  they should "group leftover elements" in {
    val departmentIds = inTransaction { implicit tx =>
      insertDepartments()
    }
    val selectDepartments = createQuery("select * from departments d")(d.reads).withoutParams
    val stream = selectDepartments.stream().grouped(2)
    val result = stream.toSeq
    assert(result.map(_.map(_.id)) === Seq(Seq(departmentIds._1, departmentIds._2), Seq(departmentIds._3)))
  }

}
