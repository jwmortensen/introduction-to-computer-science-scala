import scala.io.StdIn
import car.Car

object CarInventory {
  var balance = 10000.0;
  var inventory = List[Car]()
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
    val size = inventory.length
    if (size == 1) 
      println("1 car in inventory")
    else 
      println(size + " cars in inventory")
    for (car <- inventory) car.print
    return true
  }

  def currentBalance(): Boolean = {
    printf("Current Balance: $%.2f\n", balance)
    return true
  }

  def buyCar(): Boolean = {
    println("Please enter information about the car:")
    println("Name:")
    val name = StdIn.readLine
    println("Color:")
    val color = StdIn.readLine
    println("Price:")
    val price = StdIn.readDouble
    if (price > balance) {
      println("Insufficient Funds.")
    }
    else {
      balance -= price
      inventory =  new Car(name, color, price) :: inventory
    }
      
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
