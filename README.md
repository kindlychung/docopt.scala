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
    val args = Docopt(doc, _args)

    val experimentFolder = opt.getString("EXPR").get
    val force = args.getBoolean("--force", false)
  }
}
```

## Installation

The library is published to my own maven repo, add the following to your sbt build file:

```scala
resolvers += "Chunliang's Maven Repository" at "https://repo.chunlianglyu.com"
libraryDependencies += "com.chunlianglyu.docopt2" %% "docopt2" % "0.2"
```

Or use 0.2.2 on jitpack:

```
resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies += "com.github.kindlychung" % "docopt.scala" % "0.2.2"	
```

## Tests Coverage

Currently 75/81 docopt tests are passed.
