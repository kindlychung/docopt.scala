# docopt2

[docopt](http://docopt.org/) language definition can be found on the official webpage.

[![Build Status](https://travis-ci.org/cllu/docopt.scala.svg?branch=develop)](https://travis-ci.org/cllu/docopt.scala)

## Usage

```scala
object Main {
  val doc =
    """Usage:
      |  Main [--force] EXPR
    """.stripMargin

  def main(_args: Array[String]) {
    val args = try {
      Docopt(doc, _args)
    } catch {
      case e: Exception =>
        print(doc)
        sys.exit(-1)
    }

    val experimentFolder = opt.get("EXPR").get.asInstanceOf[String]
    val force = args.getOrElse("--force", false)
  }
}
```

## Installation

The library is published to my own maven repo, add the following to your sbt build file:

```scala
resolvers += "Chunliang's Maven Repository" at "https://repo.chunlianglyu.com"
libraryDependencies += "com.chunlianglyu.docopt2" %% "docopt2" % "0.1"
```

## Tests Coverage

Currently 42/81 language agnostic tests are passed. We do not support `options` section yet.
