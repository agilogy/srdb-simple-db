import sbt._
import sbt.Keys._

object Build extends Build {

  def withTests(p: Project) =
    p.configs(IntegrationTest)
      .settings(inConfig(IntegrationTest)(Defaults.testTasks): _*)
      .settings(
      testOptions in Test := Seq(Tests.Filter(unitFilter)),
      testOptions in IntegrationTest := Seq(Tests.Filter(itFilter)),
      parallelExecution in IntegrationTest := false
    )

  def itFilter(name: String): Boolean = (name startsWith "it.") || (name startsWith "system.")

  def unitFilter(name: String): Boolean = !itFilter(name)

  lazy val IntegrationTest = config("it") extend (Test)

  lazy val simpledb = withTests(Project(
    id = "simple-db",
    base = file(".")))

}
