import sbt.Keys._
import sbt._

object Build extends Build {

  val Organization = "com.chunlianglyu.docopt2"
  val Name = "docopt2"
  val Version = "0.2.3"
  val ScalaVersion = "2.11.7"

  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

  lazy val project = Project(
    id = Name,
    base = file("."),
    settings = Defaults.coreDefaultSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,

      publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/Projects/repo.chunlianglyu.com"))),

      libraryDependencies ++= Seq(
        "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4" % "test",
        "org.scalatest" %% "scalatest" % "2.2.5" % "test",
        "junit" % "junit" % "4.10" % "test"
      )
    )
  )
}
