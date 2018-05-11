import Dependencies._

lazy val root = (project in file("."))
  .settings(
    inThisBuild(
      List(
        organization := "me.marianonavas",
        scalaVersion := "2.12.5",
        version := "0.0.1-SNAPSHOT",
        scalacOptions := Seq(
          "-unchecked",
          "-deprecation",
          "-feature",
          "-encoding",
          "UTF-8",
          "-target:jvm-1.8",
          "-Xfuture",
          "-Xfatal-warnings",
          "-Xlint",
          "-Yno-adapted-args",
          "-Ypartial-unification",
          "-Ywarn-nullary-override",
          "-Ywarn-dead-code",
          "-Ywarn-numeric-widen",
          "-Ywarn-value-discard",
          "-Ywarn-unused",
          "-Ywarn-inaccessible",
          "-language:higherKinds"
        ),
        scalafmtOnCompile := true,
        scalafmtVersion := "1.4.0"
      )),
    name := "matrixclient4s",
    libraryDependencies += scalaTest % Test
  )
  .aggregate(
    algebra,
    http
  )

lazy val algebra = (project in file("algebra"))
  .settings(
    name := "algebra",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      catsCore,
      catsFree
    )
  )

lazy val http = (project in file("http"))
  .settings(
    name := "http",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      catsCore,
      catsFree
    )
  )
