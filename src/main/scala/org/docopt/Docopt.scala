package org.docopt

import org.docopt.pattern._

import scala.{Option => SOption}
import scala.collection.mutable
import org.docopt.pattern.Option
import org.docopt.pattern.Command
import org.docopt.pattern.Argument


class Docopt(map: Map[String, Any]) {

  def asMap = map

  def getInt(key: String): SOption[Int] = map.get(key) match {
    case Some(null) => None
    case Some(v) => Some(v.asInstanceOf[Int])
    case None => None
  }
  def getInt(key: String, default: Int): Int = map.getOrElse(key, default).asInstanceOf[Int]

  def getBoolean(key: String): SOption[Boolean] = map.get(key) match {
    case None => None
    case Some(null) => None
    case Some(v) => Some(v.asInstanceOf[Boolean])
  }
  def getBoolean(key: String, default: Boolean): Boolean = map.getOrElse(key, default).asInstanceOf[Boolean]

  def getString(key: String): SOption[String] = map.get(key) match {
    case None => None
    case Some(null) => None
    case Some(v) => Some(v.asInstanceOf[String])
  }
  def getString(key: String, default: String): String = map.getOrElse(key, default).asInstanceOf[String]

  def getStrings(key: String): Seq[String] = map.get(key) match {
    case Some(v: Array[String]) => v.toSeq
    case _ => Seq()
  }

}

object Docopt {

  private def extractValue(value: Value): Any = value match {
    case b:BooleanValue => b.value
    case i:IntValue => i.value
    case s:StringValue => s.value
    case m:ManyStringValue => Array(m.value:_*)
    case _ => null
  }

  def apply(usage: String,
            argv: Array[String],
            help: Boolean = true,
            version: String = "",
            optionsFirst: Boolean = false,
            exitOnException: Boolean=true): Docopt = {

    try {
      val collected = PatternParser.docopt(usage, argv.filter(_ != ""), help, version, optionsFirst)
      val tupled:Seq[(String, Any)] = collected.map {
        case o@Option(l,s,a,value:Value) => (o.name ,extractValue(value))
        case Argument(name,value:Value) => (name, extractValue(value))
        case Command(name,value:Value) => (name, extractValue(value))
      }
      new Docopt(tupled.toMap)
    } catch {
      case e: Exception =>
        if (exitOnException) {
          println(usage)
          sys.exit(0)
        } else {
          throw e
        }
    }
  }
}
