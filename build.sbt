lazy val visicut = (project in file("."))
  .enablePlugins(JavaAppPackaging, JDKPackagerPlugin)
  .aggregate(liblasercut)
  .dependsOn(liblasercut)
  .settings(
    name := "VisiCut",
    version := "1.9-SNAPSHOT",
    organization := "de.thomas-oster",
    maintainer := "Thomas Oster <mail@thomas-oster.de>",
    autoScalaLibrary := false,
    crossPaths := false,
    Compile / javacOptions ++= Seq("-Xlint:unchecked", "-source", "11", "-target", "11"),
    Compile / doc / javacOptions := Seq("-Xdoclint:none"),
    libraryDependencies ++= Seq(
      "org.apache.httpcomponents" % "httpcore" % "4.4.11",
      "com.github.sarxos" % "webcam-capture" % "0.3.12",
      "org.apache.httpcomponents" % "httpclient" % "4.5.9",
      "org.scribe" % "scribe" % "1.3.7",
      "org.thymeleaf" % "thymeleaf" % "2.1.6.RELEASE",
      "guru.nidi.com.kitfox" % "svgSalamander" % "1.1.2",
      "org.apache.httpcomponents" % "httpmime" % "4.5.1",
      "org.jdesktop" % "appframework" % "1.0.3",
      "org.jdesktop" % "beansbinding" % "1.2.1",
      "com.thoughtworks.xstream" % "xstream" % "1.4.11.1",
      "com.google.zxing" % "javase" % "3.4.0",
      "gov.nist.math" % "jama" % "1.0.3",
      "org.freehep" % "freehep-psviewer" % "2.0",
      "com.googlecode.json-simple" % "json-simple" % "1.1.1",
      "org.apache.xmlgraphics" % "batik-dom" % "1.8",
      "org.apache.xmlgraphics" % "batik-svggen" % "1.8",
      "commons-io" % "commons-io" % "2.6",
      "com.neuronrobotics" % "JCSG" % "0.5.3",
      "dom4j" % "dom4j" % "1.6.1",

      "com.novocode" % "junit-interface" % "0.11" % Test
    ),
    Test / fork := true,
    Test / parallelExecution := true,
    Compile / mainClass := Some("de.thomas_oster.visicut.gui.VisicutApp"),
    fork in (Compile, run) := true,
    organizationName := "Thomas Oster",
    startYear := Some(2011),
    headerLicense := Some(HeaderLicense.LGPLv3OrLater(startYear.value.get.toString, organizationName.value))
  )

lazy val liblasercut = (project in file("LibLaserCut"))