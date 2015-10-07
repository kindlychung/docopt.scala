# ``docopt.scala`` is a JVM implementation of docopt

`docopt http://docopt.org/` language definition can be found on the official webpage.

[![Build Status](https://travis-ci.org/cllu/docopt.scala.svg?branch=develop)](https://travis-ci.org/cllu/docopt.scala)

## Usage

- For a scala example, see ``src/test/scala/org/docopt/Testee.scala`` and
  ``src/test/scripts/testee``
- For a java example, see ``src/test/java/org/docopt/NavalFate.java`` and
  ``src/test/scripts/naval_fate``

## Installation

The library is published to my own maven repo, add the following to your sbt build file:

```scala
resolvers += "Chunliang's Maven Repository" at "https://repo.chunlianglyu.com"
libraryDependencies += "com.chunlianglyu.docopt2" %% "docopt2" % "0.1"
```

## Tests Coverage

The current coverage is 150/164 language agnostic tests.
