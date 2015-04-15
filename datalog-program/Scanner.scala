package jwm.datalog

import scala.io.Source

class Scanner {
  
  def scan(filename: String) {
    val text = Source.fromFile(filename).mkString
    println(text)
  }
}
