import scala.io.StdIn

object Plinko {
  def menu(option: Int): Boolean = {
    option match {
      case 1 =>
        println("selected 1")
        true
      case 2 =>
        println("selected 2")
        true
      case 3 =>
        println("selected 3")
        false 
      case _ =>
        println("Sorry, that command is not recognized")
        true
    }
  }
  
  def readOption: Int = {
    println("""|Please select one of the following:
                 |  1 - drop one token 
                 |  2 - drop multiple tokens
                 |  3 - quit""".stripMargin)
    StdIn.readInt
  }
    

  def main(args: Array[String]) {
    var opt = -1
    do {
      opt = readOption
    } while (menu(opt))
  }
}
