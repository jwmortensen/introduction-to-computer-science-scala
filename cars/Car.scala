package car

class Car(var name: String, var color: String, var price: Double) {
  
  def paint(newColor: String): Boolean = {
    color = newColor
    price += 1000
    return true
  }

  def print()  = {
    printf("Name: %s\nColor: %s\nPrice: $%.2f\n\n", name, color, price)
  }
}
