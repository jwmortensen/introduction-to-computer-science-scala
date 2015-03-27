import scala.io.StdIn
import scala.util.Random

object Plinko {

  def readOption: Int = {
    println("""|Please select one of the following:
                 |  1 - drop one token 
                 |  2 - drop multiple tokens
                 |  3 - quit""".stripMargin)
    StdIn.readInt
  }
   
  def selectSlot: Int = {
    println("Please select a slot between 0 and 8")
    var slot = StdIn.readInt
    if (slot >= 0 & slot <= 8) {
      slot
    }
    else {
      println("Sorry, you must pick a valid slot between 0 and 8.")
      selectSlot
    }
  }

  def slotPrize(slot: Int): Int = {
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
    var left = Random.nextBoolean
    if (prevSlot <= 0.0) {
      left = false
    }
    else if (prevSlot >= 8.0) {
      left = true
    }

    if (left) {
      prevSlot #:: getPath(prevSlot - 0.5) 
    }
    else {
      prevSlot #:: getPath(prevSlot + 0.5)
    }
  }
     
  def dropOne = {
    val slot = selectSlot
    val path = getPath(slot).take(12)
    println(path.toList.toString)
    println(slot)
  }    

  def menu(option: Int): Boolean = {
    option match {
      case 1 =>
        dropOne
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
  

  def main(args: Array[String]) = {
    var opt = -1
    do {
      opt = readOption
    } while (menu(opt))
  }
}
