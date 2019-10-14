name := "tensorflow-keras-scala"
organization := "org.tensorflow"
version := "0.0.1"
scalaVersion := "2.13.1"
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

lazy val root = project.in(file(".")).settings(
  resolvers += Resolver.mavenLocal,
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.2.0-M1" % Test,
    "org.tensorflow" % "libtensorflow_jni" % "1.13.1",
    "org.tensorflow" % "tensorflow-keras" % "0.0.1"
  )
)