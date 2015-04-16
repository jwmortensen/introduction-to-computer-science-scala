package jwm.datalog

import scala.io.Source

object State extends Enumeration {
  type State = Value
  val S0, S1, S2, S3 = Value
}

class Scanner {
  import State._
  
  def isComma(text: String): Int = {
    if (text.startsWith(",")) 1 else 0
  }

  def isPeriod(text: String): Int = {
    if (text.startsWith(".")) 1 else 0
  }

  def isQMark(text: String): Int = {
    if (text.startsWith("?")) 1 else 0
  }

  def isLeftParen(text: String): Int = {
    if (text.startsWith("(")) 1 else 0
  }

  def isRightParen(text: String): Int = {
    if (text.startsWith(")")) 1 else 0
  }

  def isColon(text: String): Int = {
    if (text.startsWith(":")) 1 else 0
  }

  def isColonDash(text: String): Int = {
    if (text.startsWith(":-")) 2 else 0
  }
  
  def isMultiply(text: String): Int = {
    if (text.startsWith("*")) 1 else 0
  }
  
  def isAdd(text: String): Int = {
    if (text.startsWith("+")) 1 else 0
  }
  
  def isString(text: String): Int = {
    var state = S0
    var ind = 0
    if (text.startsWith("'")) {
      state = S1
      text.tail.toStream.takeWhile(_ => state != S3).foreach( c => {
        ind += 1
        if (state == S1 && c == '\'') state = S2
        else if (state == S2 && c != '\'') state = S3
        else state = S1
      })
    }
    if (state == S3) ind
    else 0
  }
    
  def scan(filename: String) {
    val text = Source.fromFile(filename).mkString
    val len = isString(text)
    println("the length is " + len)
  }
}
