enablePlugins(ScalaJSPlugin)

name := "sri-web-template"

scalaVersion := "2.11.8"

val sriVersion = "0.5.0"

val sriscalaCssVersion = "2016.5.0"

val reactJSVersion = "15.1.0"

val assetsDir = "assets/"

jsDependencies ++= Seq(
  "org.webjars.npm" % "react"     % reactJSVersion / "dist/react.js" commonJSName "React"    minified "react.min.js",
  "org.webjars.npm" % "react-dom" % reactJSVersion / "react-dom.js" commonJSName "ReactDOM"  minified "react-dom.min.js" dependsOn "dist/react.js",
  "org.webjars.npm" % "history" % "3.0.0-2" / "history.js"  commonJSName "History" minified "history.min.js"
)

// creates single js resource file for easy integration in html page
skip in packageJSDependencies := false

// where jsDependencies go
crossTarget in (Compile, packageJSDependencies) := file(assetsDir)
crossTarget in (Compile, packageMinifiedJSDependencies) := file(assetsDir)

libraryDependencies ++= Seq(
  "com.github.chandu0101.sri" %%% "web" % sriVersion,
  "com.github.chandu0101.sri" %%% "scalacss" % sriscalaCssVersion
)

// copy fastOptJS/fullOptJS  fiels to assets directory
crossTarget in(Compile, fullOptJS) := file(assetsDir)
crossTarget in(Compile, fastOptJS) := file(assetsDir)
crossTarget in(Compile, packageScalaJSLauncher) := file(assetsDir)
artifactPath in(Compile, fastOptJS) := ((crossTarget in(Compile, fastOptJS)).value /
  ((moduleName in fastOptJS).value + "-opt.js"))

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature",
  "-language:postfixOps", "-language:implicitConversions",
  "-language:higherKinds", "-language:existentials")

