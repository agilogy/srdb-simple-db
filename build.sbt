import com.typesafe.sbt.GitPlugin.autoImport._
import com.typesafe.sbt.GitVersioning
import com.gilcloud.sbt.gitlab.{GitlabCredentials,GitlabPlugin}

organization := "com.agilogy"

name := "simple-db"

scalaVersion := "2.12.13"

crossScalaVersions := Seq("2.11.12","2.12.13")

parallelExecution in Test := false

resolvers += Resolver.url("Agilogy Scala",url("https://dl.bintray.com/agilogy/scala/"))(Resolver.ivyStylePatterns)

libraryDependencies ++= Seq(
  "com.agilogy" %% "groupable" % "1.1",
  "com.agilogy" %% "srdb-core" % "2.2",
  "com.agilogy" %% "srdb-tx" % "1.1",
  "com.agilogy" %% "srdb-types" % "2.1",
  "org.postgresql" % "postgresql" % "9.3-1103-jdbc41" % "test,it",
  "log4j" % "log4j" % "1.2.17" % "test,it",
  "c3p0" % "c3p0" % "0.9.1.2" % "test,it",
  "com.googlecode.flyway" % "flyway-core" % "2.0" % "test,it",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test,it",
  "com.github.nscala-time" %% "nscala-time" % "2.20.0"
)

// --> Linters

// See tinyurl.com/sd15lint

// https://tpolecat.github.io/2014/04/11/scalac-flags.html
scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",       // yes, this is 2 args
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture"
)

scalacOptions in (Compile,doc) ++= Seq("-groups", "-implicits", "-diagrams", "-skip-packages","")

// Execute static analysis via `lint:compile`
val LintTarget = config("lint").extend(Compile)

inConfig(LintTarget) {

  Defaults.compileSettings ++
    Seq(
      sources in LintTarget := {
        val lintSources = (sources in LintTarget).value
        lintSources ++ (sources in Compile).value
      },
      scalacOptions in LintTarget ++= Seq(
        "-Xfatal-warnings",
        "-Ywarn-unused-import",
        "-Ywarn-dead-code",
        "-P:linter:disable:PreferIfToBooleanMatch"
      ),
      wartremoverErrors ++= Warts.allBut(Wart.DefaultArguments, Wart.MutableDataStructures)
    )
}

scalacOptions in Compile := (scalacOptions in Compile).value filterNot { switch =>
  switch.startsWith("-P:wartremover:") ||
    "^-Xplugin:.*/org[.]brianmckenna/.*wartremover.*[.]jar$".r.pattern.matcher(switch).find ||
    switch.startsWith("-P:linter:") ||
    "^-Xplugin:.*/com[.]foursquare[.]lint/.*linter.*[.]jar$".r.pattern.matcher(switch).find
}

resolvers += "Linter Repository" at "https://hairyfotr.github.io/linteRepo/releases"

addCompilerPlugin("org.psywerx.hairyfotr" %% "linter" % "0.1.17")

scalastyleFailOnError := true

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

// <-- Linters

// Reformat at every compile.
// See https://github.com/sbt/sbt-scalariform
scalariformItSettings

coverageExcludedPackages := "<empty>"

coverageHighlighting := false

enablePlugins(spray.boilerplate.BoilerplatePlugin)

// --> gitlab

GitlabPlugin.autoImport.gitlabGroupId := Some(583742)
GitlabPlugin.autoImport.gitlabProjectId := None
GitlabPlugin.autoImport.gitlabDomain := "gitlab.com"

GitlabPlugin.autoImport.gitlabCredentials := {
    val token = sys.env.get("GITLAB_DEPLOY_TOKEN") match {
        case Some(token) => token
        case None =>
            sLog.value.warn(s"Environment variable GITLAB_DEPLOY_TOKEN is undefined, 'publish' will fail.")
            ""
    }
    Some(GitlabCredentials("Deploy-Token", token))
}

// <-- gitlab

enablePlugins(GitVersioning)

git.useGitDescribe := true
