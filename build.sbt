import bintray.Keys._
import com.typesafe.sbt.GitPlugin.autoImport._
import com.typesafe.sbt.GitVersioning

organization := "com.agilogy"

name := "simple-db"

scalaVersion := "2.11.7"

crossScalaVersions := Seq("2.10.6","2.11.7")

parallelExecution in Test := false

resolvers += Resolver.url("Agilogy Scala",url("http://dl.bintray.com/agilogy/scala/"))(Resolver.ivyStylePatterns)

libraryDependencies ++= Seq(
  "com.agilogy" %% "groupable" % "1.0.1",
  "com.agilogy" %% "srdb-core" % "2.0",
  "com.agilogy" %% "srdb-tx" % "1.0.1",
  "com.agilogy" %% "srdb-types" % "2.0",
  "commons-logging" % "commons-logging" % "1.1.1",
  "org.postgresql" % "postgresql" % "9.3-1103-jdbc41" % "test,it",
  "log4j" % "log4j" % "1.2.17" % "test,it",
  "c3p0" % "c3p0" % "0.9.1.2" % "test,it",
  "com.googlecode.flyway" % "flyway-core" % "2.0" % "test,it",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test,it",
  "org.scalaj" %% "scalaj-time" % "0.8"
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

addCompilerPlugin("org.psywerx.hairyfotr" %% "linter" % "0.1.12")

scalastyleFailOnError := true

// <-- Linters

// Reformat at every compile.
// See https://github.com/sbt/sbt-scalariform
scalariformSettings

coverageExcludedPackages := "<empty>"

coverageHighlighting := false

Boilerplate.settings

// --> bintray

seq(bintrayPublishSettings:_*)

repository in bintray := "scala"

bintrayOrganization in bintray := Some("agilogy")

packageLabels in bintray := Seq("scala")

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

// <-- bintray

enablePlugins(GitVersioning)

git.useGitDescribe := true

resolvers += "Agilogy snapshots" at "http://188.166.95.201:8081/content/groups/public"

publishMavenStyle := isSnapshot.value

publishTo := {
  val nexus = "http://188.166.95.201:8081/content/repositories/snapshots"
  if (isSnapshot.value) Some("snapshots"  at nexus)
  else publishTo.value
}