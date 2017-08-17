name := "sqlmapper"

version := "1.0"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % "2.12.3",
    "com.h2database" % "h2" % "1.4.194" % "test",
    "org.apache.commons" % "commons-dbcp2" % "2.0.1" % "test",
    "com.typesafe" % "config" % "1.3.1" % "test",
    "org.scalatest" %% "scalatest" % "3.0.3" % "test"
)