import jwm.datalog.Scanner

object Main {
  def main(args: Array[String]) {
    if (args.length > 0) {
      val s = new Scanner
      s.scan(args(0))
    } 
    else
      Console.err.println("Please enter filename")
  }
}

