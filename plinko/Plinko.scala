import scala.io.StdIn

object Plinko {
  def main(args: Array[String]) {
    var quit = 0
    while (quit == 0) {
      var game = -1
      println("""|Please select one of the following:
    |1 - drop one token 
    |2 - drop multiple tokens
    |3 - quit""".stripMargin)
      game = StdIn.readInt
      if (game == 1) {
        println("selected 1")
      }
      else if (game == 2) {
        println("selected 2")
      }
      else if (game == 3) {
        println("selected 3")
        quit = 1
      }
      else {
        println("Sorry, that command is not recognized.")
      }
    }
  }
}
