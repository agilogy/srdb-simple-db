organization := "net.casaametller"

name := "simple-db"

scalaVersion := "2.11.7"

parallelExecution in Test := false

resolvers ++= Seq("casaametller repository" at "http://spm.casaametller.net:50006/nexus-2.3.0-04/content/groups/casaametller/")

resolvers += Resolver.url("Agilogy Scala",url("http://dl.bintray.com/agilogy/scala/"))(Resolver.ivyStylePatterns)

libraryDependencies ++= Seq(
  "com.agilogy" %% "groupable" % "1.0.1",
  "com.agilogy" %% "srdb-core" % "2.0-SNAPSHOT",
  "com.agilogy" %% "srdb-tx" % "1.0.1",
  "com.agilogy" %% "srdb-types" % "2.0-SNAPSHOT",
  "commons-logging" % "commons-logging" % "1.1.1",
  "org.postgresql" % "postgresql" % "9.3-1103-jdbc41" % "test,it",
  "log4j" % "log4j" % "1.2.17" % "test,it",
  "c3p0" % "c3p0" % "0.9.1.2" % "test,it",
  "com.googlecode.flyway" % "flyway-core" % "2.0" % "test,it",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test,it",
  "org.scalaj" %% "scalaj-time" % "0.8"
)


net.virtualvoid.sbt.graph.Plugin.graphSettings
