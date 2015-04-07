import java.io._
import scala.io.Source
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

  /** returns true. prints total number of cars in inventory and summary info
    * for each vehicle
    */
  def currentInventory(): Boolean = {
    val size = inventory.length
    if (size == 1) 
      println("1 car in inventory")
    else 
      println(size + " cars in inventory")
    for (car <- inventory) car.print
    true
  }

  /** returns true. prints current cash balance. **/
  def currentBalance(): Boolean = {
    printf("Current Balance: $%.2f\n", balance)
    true
  }

  /** returns true. if the price of the new car is less than balance, 
    * the car is added to the inventory and the price is subtracted
    * from balance
    */
  def buyCar(): Boolean = {
    println("Vehicle Information:")
    println("====================")
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
    true
  }

  /** returns true. removes specified car from inventory and adds its price to balance. **/
  def sellCar(): Boolean = {
    println("Vehicle name:")
    val name = StdIn.readLine
    val removeIndex = inventory.indexWhere(_.name == name)
    if (removeIndex != -1) {
      balance += inventory(removeIndex).price
      inventory = inventory.patch(removeIndex, Nil, 1)
    }
    else {
      println("Invalid name.")
    }
    true
  }

  /** returns true. 
    * 
    * User specifies paint color and car to paint. Car color is changed in inventory,
    * and the price of the car is increased by $1,000
    */
  def paintCar(): Boolean = {
    println("Vehicle name:")
    val name = StdIn.readLine
    println("New color:")
    val color = StdIn.readLine
    val car = inventory.filter(_.name == name).head
    car.paint(color)
    true
  }

  /** returns true
    * 
    * The user specifies a file, and then money is added to the balance
    * and cars are added to the inventory
    */
  def loadFile(): Boolean = {
    println("Enter filename:")
    val filename = StdIn.readLine
    for (line <- Source.fromFile(filename).getLines())
      if (line.charAt(0).isDigit) 
        balance += line.toDouble
      else {
        val carData = line.split(" ")
        inventory = new Car(carData(0), carData(1), carData(2).toDouble) :: inventory
      }
    true
  }

  def saveFile(): Boolean = {
    println("Enter filename:")
    val filename = StdIn.readLine
    val pw = new PrintWriter(new File(filename))
    pw.write(balance.toString + "\n")
    for (car <- inventory)    
      pw.write(car.name + " " + car.color + " " + car.price + "\n")
    pw.close
    true
  }

  def quit(): Boolean = {
    false
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
