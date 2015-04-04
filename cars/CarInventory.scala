import scala.io.StdIn

object CarInventory {
  var balance = 10000.0;
  var inventory 
  val actionMap = Map[Int, () => Boolean](
    1 -> currentInventory, 
    2 -> currentBalance,
    3 -> buyCar,
    4 -> sellCar,
    5 -> paintCar,
    6 -> loadFile,
    7 -> saveFile,
    8 -> quit
  )

  /** Returns an int based on what the user selects **/
  def readOption(): Int = {
    println("""|Please select one of the following options:
      |  1 - Show current inventory
      |  2 - Show current balance
      |  3 - Buy a car
      |  4 - Sell a car
      |  5 - Paint a car
      |  6 - Load file
      |  7 - Save file
      |  8 - Quit""".stripMargin)
    StdIn.readInt
  }

  def currentInventory(): Boolean = {
    return true
  }

  def currentBalance(): Boolean = {
    return true
  }

  def buyCar(): Boolean = {
    return true
  }

  def sellCar(): Boolean = {
    return true
  }

  def paintCar(): Boolean = {
    return true
  }

  def loadFile(): Boolean = {
    return true
  }

  def saveFile(): Boolean = {
    return true
  }

  def quit(): Boolean = {
    return false
  }

  def menu(option: Int): Boolean = {
    actionMap.get(option) match {
      case Some(f) => f()
      case None => 
        println("Sorry, that command is not recognized. Please try again.")
        true
    }
  }

  def main(args: Array[String]) {
    var opt = 0
    do {
      opt = readOption
    } while(menu(opt))
  }
}
