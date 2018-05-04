import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "me.marianonavas",
      scalaVersion := "2.12.5",
      version      := "0.0.1-SNAPSHOT"
    )),
    name := "matrix4s",
    libraryDependencies += scalaTest % Test
  )
