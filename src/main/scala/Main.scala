import org.tensorflow.keras.activations.Activations
import org.tensorflow.keras.examples.fashionMnist.FashionMNISTKeras
import org.tensorflow.keras.models.Sequential
import org.tensorflow.keras.scala.{Layers, Model}

object Main {

  def main(args: Array[String]): Unit = {

    val model: Model[java.lang.Float] = Sequential.of(
      Layers.input(28, 28),
      Layers.flatten(28 * 28),
      Layers.dense(128, activation = Activations.relu),
      Layers.dense(10, Activations.softmax)
    )


    FashionMNISTKeras.train(model.self)

  }
}
