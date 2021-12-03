ThisBuild / scalaVersion := Dependencies.Versions.scala3
ThisBuild / githubWorkflowTargetTags ++= Seq("v*")
ThisBuild / githubWorkflowJavaVersions := Seq(JavaSpec.graalvm("20.1.0", "8"))

ThisBuild / githubWorkflowBuildPreamble += WorkflowStep.Sbt(
  List("scalafmtCheckAll", "scalafmtSbtCheck"),
  name = Some("Check formatting")
)

ThisBuild / githubWorkflowPublishTargetBranches := Seq(RefPredicate.Equals(Ref.Branch("master")))
ThisBuild / githubWorkflowPublish := Seq(
  WorkflowStep.Sbt(
    List("ciReleaseSonatype"),
    name = Some("Publish artifacts"),
    env = Map(
      "SONATYPE_PASSWORD" -> "${{ secrets.SONATYPE_PASSWORD }}",
      "SONATYPE_USERNAME" -> "${{ secrets.SONATYPE_USERNAME }}"
    )
  )
)
ThisBuild / githubWorkflowPublishCond := Some("github.actor != 'mergify[bot]'")
ThisBuild / githubWorkflowPublishPreamble += WorkflowStep.Use(
  ref = UseRef.Public("crazy-max", "ghaction-import-gpg", "v4"),
  id = Some("import_gpg"),
  name = Some("Import GPG key"),
  params = Map("gpg_private_key" -> "${{ secrets.GPG_PRIVATE_KEY }}", "passphrase" -> "${{ secrets.PGP_PASS }}")
)

ThisBuild / publishTo := sonatypePublishToBundle.value
ThisBuild / versionScheme := Some("early-semver")

ThisBuild / licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
ThisBuild / developers := List(
  Developer(
    "janstenpickle",
    "Chris Jansen",
    "janstenpickle@users.noreply.github.com",
    url = url("https://github.com/janstepickle")
  ),
  Developer(
    "catostrophe",
    "λoλcat",
    "catostrophe@users.noreply.github.com",
    url = url("https://github.com/catostrophe")
  )
)
ThisBuild / homepage := Some(url("https://github.com/janstenpickle/graal-kafka-client"))
ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/janstenpickle/graal-kafka-client"),
    "scm:git:git@github.com:janstenpickle/graal-kafka-client.git"
  )
)
ThisBuild / organization := "io.janstenpickle"
ThisBuild / organizationName := "janstenpickle"
