import scala.language.implicitConversions
import org.tensorflow.keras.scala.layers._
import org.tensorflow.keras.activations.{Activations, Activation};

object Main {

  def main(args: Array[String]): Unit = {
    layers.dense(10, Activations.linear)
  }
}
