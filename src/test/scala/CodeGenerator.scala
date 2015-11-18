import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths}

import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat

object CodeGenerator extends App {

  private val header =
    """
      |// *********************************************************************************************************************
      |// ** DON'T EDIT BY HAND!                                                                                             **
      |// ** This file is generated                                                                                          **
      |// ** Use sbt test:run to run CodeGenerator                                                                           **
      |// *********************************************************************************************************************
      |
      |package com.agilogy.simpledb
      |
      | """.stripMargin

  case class LoopCount(i: Int) {
    override def toString: String = i.toString
  }

  private def t(template: Int => String)(implicit idx: LoopCount) = (1 to idx.i).map(i => template(i)).mkString(",")

  private def PTn(name:String="PT")(implicit idx: LoopCount): String = t(name + _)

  private def paramTypeParams()(implicit idx: LoopCount): String = t(i => s"paramType$i: DbWrites[PT$i]")

  private def paramTypeArgs()(implicit idx: LoopCount): String = t("paramType" + _)

  private def paramValuesParams()(implicit idx: LoopCount): String = t(i => s"arg$i: PT$i")

  private def queryClassCode(i: LoopCount) = {
    implicit val idx = i
    s"""
      |case class Query$idx[RT, ${PTn()}](q: Query[RT], ${paramTypeParams()}) extends QueryBase[RT](q, ${paramTypeArgs()}) {
      |
      |  def apply(${paramValuesParams()})(implicit txConfig:TransactionConfig): Seq[RT] = super.execute(${t("arg" + _)})(txConfig)
      |  def stream(${paramValuesParams()}): ReadyQueryStream[RT] = super.stream(${t("arg" + _)})
      |  def map[RT2](f: (RT) => RT2): Query$idx[RT2, ${PTn()}] = this.copy(q = q.map(f))
      |
      |}
      |""".stripMargin
  }

  private def statementClassCode(i: LoopCount) = {
    implicit val idx = i
    s"""
      |class Statement$idx[RT,${PTn()}](stmt:RawStatement[RT], ${paramTypeParams()})
      |  extends StatementByPositionBase[RT](stmt, ${paramTypeArgs()}) {
      |
      |  def apply(${paramValuesParams()})(implicit txConfig: TransactionConfig): RT = stmt.apply(${t(i => s"PositionalArgument(arg$i,paramType$i)")})
      |
      |}
      |""".stripMargin
  }

  private def withParamsMethod(i: LoopCount) = {
    implicit val idx = i
    s"""  def withParams[${PTn()}](${paramTypeParams()}) = new Query$idx(self,${paramTypeArgs()})
       |""".stripMargin
  }

  private def withParamsStatementMethod(i: LoopCount) = {
    implicit val idx = i
    s"""  def withParams[${PTn()}](${paramTypeParams()}) = new Statement$idx(self,${paramTypeArgs()})
       |""".stripMargin
  }

  private def selectMethod(i: LoopCount) = {
    implicit val idx = i
    s"""  def select[RT,${PTn("T")}](${t(i => "c" + i + ": SelectedElement[T" + i + "]")}): DslQuery[(${PTn("T")})] =
       |    query(Seq(${PTn("c")}),reader(row => (${t(i => "row.get(c" + i + ")") })))
       |
       |""".stripMargin
  }



  private def generateQueries(): String = {
    val sb = new StringBuilder()
    sb.append(header)
    sb.append(
      """
        |
        |trait WithParams[RT]{
        |  self:Query[RT] =>
        |
        |""".stripMargin)
    (1 to 21).map(LoopCount).foreach {
      implicit i =>
        sb.append(withParamsMethod(i))
    }
    sb.append("}")
    (1 to 21).map(LoopCount).foreach {
      implicit i =>
        sb.append(queryClassCode(i))
    }
    sb.toString()
  }

  private def generateStatements(): String = {
    val sb = new StringBuilder()
    sb.append(header)
    sb.append(
      """
        |
        |trait StatementWithParams[RT]{
        |  val self:RawStatement[RT]
        |
        |""".stripMargin)
    (1 to 21).map(LoopCount).foreach {
      implicit i =>
        sb.append(withParamsStatementMethod(i))
    }
    sb.append(
      """}
      """.stripMargin)
    (1 to 21).map(LoopCount).foreach {
      implicit i =>
        sb.append(statementClassCode(i))
    }
    sb.toString()
  }

  private def generateSelectMethods():String = {
    val sb = new StringBuilder()
    sb.append(header)
    sb.append(
      """
        |package dsl
        |
        |import com.agilogy.simpledb.schema.Column
        |
        |trait GeneratedSelectMethods{
        |
        |  def select[RT](columns: Seq[SelectedElement[_]])(as: ResultSetReads[RT]): DslQuery[RT] = query(columns,as)
        |
        |  protected def query[RT](columns:Seq[SelectedElement[_]], reads:ResultSetReads[RT]): DslQuery[RT]
        |
        |  def select[RT,T1](c1: SelectedElement[T1]): DslQuery[T1] = query(Seq(c1),reader(row => row.get(c1)))
        |
        | """.stripMargin)
    (2 to 21).map(LoopCount).foreach {
      implicit i =>
        sb.append(selectMethod(i))
    }
    sb.append(
      """
        |}
        | """.stripMargin)
    sb.toString()
  }

  private def backup(file: Path): Unit = {
    val now = new DateTime().toString(ISODateTimeFormat.dateTime())
    val backupFile = Paths.get(file.toAbsolutePath.toString + "." + now + ".bck")
    Files.copy(file, backupFile)
    println("Backed up " + backupFile.getFileName.toString)
  }

  private def write(file: Path, s: String): Unit = {
    backup(file)
    Files.write(file, s.getBytes(StandardCharsets.UTF_8))
    println("Generated " + file.toAbsolutePath.toString)
  }

  val dir = "./src/main/scala/com/agilogy/simpledb/"
  println("Generating statements...")
  write(Paths.get(s"${dir}GeneratedStatements.scala"), generateStatements())
  println("Generating queries...")
  write(Paths.get(s"${dir}GeneratedQueries.scala"), generateQueries())
  write(Paths.get(s"${dir}dsl/GeneratedSelectMethods.scala"), generateSelectMethods())
  println("Done!")
}
