import scala.io.StdIn

object Plinko {
  def menu(option: Int) {
    println("""|Please select one of the following:
                 |  1 - drop one token 
                 |  2 - drop multiple tokens
                 |  3 - quit""".stripMargin)
    if (option == 1) {
      println("selected 1")
      val opt = StdIn.readInt
      menu(opt)
    }
    else if (option == 2) {
      println("selected 2")
      val opt = StdIn.readInt
      menu(opt)
    }
    else if (option == 3) {
      println("selected 3")
    }
    else {
      println("Sorry, that command is not recognized")
    }
  }

  def main(args: Array[String]) {
    println("""|Please select one of the following:
                 |  1 - drop one token 
                 |  2 - drop multiple tokens
                 |  3 - quit""".stripMargin)
    val opt = StdIn.readInt
    menu(opt)
  }
}
