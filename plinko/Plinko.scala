import scala.io.StdIn
import scala.util.Random

object Plinko {
  val actionMap = Map[Int, () => Boolean](1 -> dropOne, 2 -> dropMany, 3 -> quit)

  def readOption(): Int = {
    println("""|Please select one of the following:
                 |  1 - drop one token 
                 |  2 - drop multiple tokens
                 |  3 - quit""".stripMargin)
    StdIn.readInt
  }
   
  def selectSlot(): Int = {
    println("Please select a slot between 0 and 8")
    val slot = StdIn.readInt
    if (slot >= 0 & slot <= 8) slot else selectSlot
  }

  def numChips(): Int = {
    println("Please enter the number of chips you would like to drop: ")
    val num = StdIn.readInt
    if (num > 0) num else numChips
  }

  def sum(xs: Stream[Int]): Double  = {
    xs reduceLeft {(x,y) => x + y}
  }

  def avg(xs: Stream[Int]): Double  = {
    sum(xs) / xs length
  }

  def slotPrize(slot: Double): Int = {
    slot match {
      case 0 | 8 => 100
      case 1 | 7 => 500
      case 2 | 6 => 1000
      case 3 | 5 => 0
      case 4 => 10000
      case _ => 
        println("An error occured. Please check the slotPrize function")
        0
    }
  }

  def getPath(prevSlot: Double): Stream[Double] = { 
    prevSlot match {
      case 0 => prevSlot #:: getPath(prevSlot + 0.5)
      case 8 => prevSlot #:: getPath(prevSlot - 0.5)
      case _ => 
        if (Random.nextBoolean) prevSlot #:: getPath(prevSlot - 0.5) else prevSlot #:: getPath(prevSlot + 0.5)
    }
  }
     
  def getMultipleWinnings(slot: Double): Stream[Int] = {
    slotPrize(getPath(slot).take(13).last) #:: getMultipleWinnings(slot)
  }

  def dropOne(): Boolean = {
    val slot = selectSlot
    val path = getPath(slot).take(13).toList
    println("The path of the chip is: " + path.mkString("[ ", ", ", " ]"))
    println("The winnings are: $" + slotPrize(path.last))
    true
  }    

  def dropMany(): Boolean = {
    val num = numChips
    val slot = selectSlot
    val winnings = getMultipleWinnings(slot).take(num)
    printf("Total winnings are $%.2f\n", sum(winnings))
    printf("Average winnings are $%.2f\n", avg(winnings))
    true
  }

  def quit(): Boolean = {
    println("Thanks for playing!")
    false
  }

  def menu(option: Int): Boolean = {
    actionMap.get(option) match {
      case Some(f) => f()
      case None =>
        println("Sorry, that command is not recognized.")
        true
    }
  }

  def main(args: Array[String]) = {
    var opt = -1
    do {
      opt = readOption
    } while (menu(opt))
  }
}
