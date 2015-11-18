organization := "net.casaametller"

name := "simple-db"

scalaVersion := "2.10.4"

parallelExecution in Test := false

resolvers ++= Seq("casaametller repository" at "http://spm.casaametller.net:50006/nexus-2.3.0-04/content/groups/casaametller/")

resolvers += Resolver.url("Agilogy Scala",url("http://dl.bintray.com/agilogy/scala/"))(Resolver.ivyStylePatterns)

libraryDependencies ++= Seq(
  "com.agilogy" %% "groupable" % "1.0.1",
  "com.agilogy" %% "srdb-tx" % "1.0.1",
  "com.agilogy" %% "srdb-types" % "1.1.1",
  "commons-logging" % "commons-logging" % "1.1.1",
  "org.postgresql" % "postgresql" % "9.3-1103-jdbc41" % "test,it",
  "log4j" % "log4j" % "1.2.17" % "test,it",
  "c3p0" % "c3p0" % "0.9.1.2" % "test,it",
  "com.googlecode.flyway" % "flyway-core" % "2.0" % "test,it",
  "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test,it",
  "org.scalaj" % "scalaj-time_2.10.0-M7" % "0.6"
)


net.virtualvoid.sbt.graph.Plugin.graphSettings
