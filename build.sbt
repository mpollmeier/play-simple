name := "play-simple"
organization := "com.michaelpollmeier"
scalaVersion := "2.11.7"
lazy val root = project.in(file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % Test,
  "org.mockito" % "mockito-core" % "1.10.19" % Test
)

name in Universal := moduleName.value

javaOptions in Test += "-Dconfig.file=test/resources/application.conf"
javaOptions in Test += "-Dlogger.file=test/resources/logger.xml"

val repo = "https://nexus.prod.corp/content"
resolvers += "spring" at s"$repo/groups/public"

publishTo <<= version { (v: String) ⇒
  if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at s"$repo/repositories/snapshots")
  else Some("releases" at s"$repo/repositories/releases")
}

releaseSettings
ReleaseKeys.versionBump := sbtrelease.Version.Bump.Minor
ReleaseKeys.tagName := s"${name.value}-v${version.value}"

scalacOptions ++= Seq("-Xlint", "-deprecation")
