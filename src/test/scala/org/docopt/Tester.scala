package org.docopt

import io.Source
import com.twitter.json.Json

import scala.collection.mutable

object Tester extends App {

  val testCases = Source.fromInputStream(getClass.getResourceAsStream("/testcases.docopt"))
                        .getLines()
                        .filterNot(_.startsWith("#"))
                        .mkString("\n")

  val testSet = Set(args.map(x => Integer.valueOf(x)):_*)
  val testFilter = if (args.length > 0) { x:Integer => testSet.contains(x) }
                   else {x:Integer => true}

  var total = 0
  var failed = new mutable.HashSet[Int]

  // the first split is empty, that's why we drop(1)
  testCases.split("r\"\"\"").zipWithIndex.filter({case (t, x) => testFilter(x)}).drop(1).foreach {
    case (testRun, index) =>
      testRun.split("\"\"\"") match {
        case Array(doc, body) =>
          total += 1
          body.trim.split("\\$ ").filterNot(_ == "").foreach {
            case testCase =>
              val Array(argv_, tmp@_*) = testCase.trim.split("\n")
              // some test case has a comment starting with #
              val expectedResultString = tmp.mkString("\n").split("#").head.trim

              val Array("prog", argv@_*) = argv_.split(" ")
              try {
                val expectedResult = Json.build(Json.parse(expectedResultString))
                val result = Json.build(Docopt(doc, argv.toArray).asMap)
                if (result != expectedResult) {
                  failed.add(index)
                  println("===== %d: FAILED =====".format(index))
                  println("\"\"\"" + doc + "\"\"\"")
                  println(argv_)
                  println("result> " + result)
                  println("expected> " + expectedResult + "\n")
                }
              } catch {
                case e:Throwable =>
                  if (expectedResultString != "\"user-error\"") {
                    failed.add(index)
                    println("===== %d: BAD JSON =====".format(index))
                    println("\"\"\"" + doc + "\"\"\"")
                    println(argv_)
                    println("result> " + "\"user-error\"")
                    println("expected> " + expectedResultString + "\n")
                  }
              }
          }
      }
  }

  println(s"Total: $total, failed: ${failed.size}")
  println(s"Failed tests: $failed")

}
