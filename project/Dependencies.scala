import sbt._

object Dependencies {
  object Versions {
    val kafka = "2.8.0"
    val micronaut = "2.5.8"
    val scala3 = "3.0.1"
    val svm = "19.2.1"
  }

  lazy val kafka = "org.apache.kafka"     % "kafka-clients"  % Versions.kafka
  lazy val micronautCore = "io.micronaut" % "micronaut-core" % Versions.micronaut
  lazy val svm = "com.oracle.substratevm" % "svm"            % Versions.svm % "provided"
}
