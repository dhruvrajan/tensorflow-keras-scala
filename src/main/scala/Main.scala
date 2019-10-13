import org.tensorflow.keras.scala.Layers

import org.tensorflow.keras.activations.{Activations}
import org.tensorflow.keras.initializers.{Initializers}
object Main {

  def main(args: Array[String]): Unit = {
    Layers.dense(10, biasInitializer=Initializers.zeros, activation=)
  }
}
