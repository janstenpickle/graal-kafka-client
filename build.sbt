lazy val commonSettings = Seq(
  Compile / compile / javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
  Test / fork := true,
  resolvers += Resolver.sonatypeRepo("releases"),
  ThisBuild / evictionErrorLevel := Level.Error,
)

lazy val noPublishSettings =
  commonSettings ++ Seq(publish := {}, publishArtifact := false, publishTo := None, publish / skip := true)

lazy val publishSettings = commonSettings ++ Seq(
  publishMavenStyle := true,
  pomIncludeRepository := { _ =>
    false
  },
  Test / publishArtifact := false
)

lazy val root = (project in file("."))
  .settings(noPublishSettings)
  .settings(name := "Graal Kafka Client")
  .aggregate(core)

lazy val core =
  (project in file("modules/core"))
    .settings(publishSettings)
    .settings(
      name := "graal-kafka-client",
      crossPaths := false,
      libraryDependencies ++= Seq(Dependencies.kafka, Dependencies.micronautCore, Dependencies.svm)
    )

addCommandAlias("fmt", "all root/scalafmtSbt root/scalafmtAll")
addCommandAlias("fmtCheck", "all root/scalafmtSbtCheck root/scalafmtCheckAll")
