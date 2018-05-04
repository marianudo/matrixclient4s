import sbt._

object Dependencies {
  lazy val catsVersion = "1.1.0"

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val catsCore = "org.typelevel" %% "cats-core" % catsVersion
  lazy val catsFree = "org.typelevel" %% "cats-free" % catsVersion
}
