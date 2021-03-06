lazy val baseName           = "Dotterweide-Exp-Coursier"
lazy val baseNameL          = baseName.toLowerCase
lazy val projectVersion     = "0.1.0-SNAPSHOT"
lazy val mimaVersion        = "0.1.0"

// versions of library dependencies
val deps = new {
  val main = new {
    val appDirs             = "1.0.3"
    val coursier            = "1.1.0-M9"
    val fileUtil            = "1.1.3"
    val slf4j               = "1.7.25"
    val scalaSwing          = "2.1.0"
  }
  val test = new {
    val scalaTest           = "3.0.5"
  }
}

lazy val commonSettings = Seq(
  version             := projectVersion,
  description         := "Dotterweide experiments in using Coursier dependency resolution",
  homepage            := Some(url(s"https://github.com/dotterweide/$baseNameL")),
  scalaVersion        := "2.12.8",
  crossScalaVersions  := Seq("2.12.8", "2.11.12"),
  licenses            := Seq(lgpl2),
  scalacOptions      ++= Seq(
    "-deprecation", "-unchecked", "-feature", "-encoding", "utf8", "-Xlint", "-Xsource:2.13"
  )
)

lazy val testSettings = Seq(
  libraryDependencies += {
    val v = if (scalaVersion.value == "2.13.0-M5") "3.0.6-SNAP5" else deps.test.scalaTest
    "org.scalatest" %% "scalatest" % v % Test
  }
)

lazy val lgpl2 = "LGPL v2.1+" -> url("http://www.gnu.org/licenses/lgpl-2.1.txt")

lazy val root = project.in(file("."))
  .settings(commonSettings)
  .settings(testSettings)
  .settings(
    name := baseName,
    libraryDependencies ++= Seq(
      "de.sciss"                %%  "fileutil"    % deps.main.fileUtil,   // utilities for path construction
      "io.get-coursier"         %%  "coursier"    % deps.main.coursier,   // fetching artifacts
      "net.harawata"            %   "appdirs"     % deps.main.appDirs,    // finding cache directory
      "org.scala-lang.modules"  %%  "scala-swing" % deps.main.scalaSwing, // UI
      "org.slf4j"               %   "slf4j-nop"   % deps.main.slf4j,      // disable logger output
    ),
    mimaPreviousArtifacts := Set("de.sciss" %% baseNameL % mimaVersion)
  )


