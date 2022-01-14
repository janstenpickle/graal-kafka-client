import sbt._

object Dependencies {
  object Versions {
    val kafka = "3.0.0"
    val micronaut = "3.2.6"
    val scala3 = "3.1.0"
    val svm = "19.2.1"
  }

  lazy val kafka = "org.apache.kafka"     % "kafka-clients"  % Versions.kafka
  lazy val micronautCore = "io.micronaut" % "micronaut-core" % Versions.micronaut
  lazy val svm = "com.oracle.substratevm" % "svm"            % Versions.svm % "provided"
}
