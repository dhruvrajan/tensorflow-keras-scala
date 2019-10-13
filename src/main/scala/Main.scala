import org.tensorflow.Graph
import org.tensorflow.data.GraphLoader
import org.tensorflow.keras.activations.Activations
import org.tensorflow.keras.datasets.{FashionMNIST, MNIST}
import org.tensorflow.keras.losses.Losses
import org.tensorflow.keras.metrics.Metrics
import org.tensorflow.keras.models.Sequential
import org.tensorflow.keras.optimizers.Optimizers
import org.tensorflow.keras.scala.{Layers, Model}
import org.tensorflow.op.Ops
import org.tensorflow.utils.Pair

object Main {
  val model: Model[java.lang.Float] = Sequential.of(
    Layers.input(28, 28),
    Layers.flatten(28 * 28),
    Layers.dense(128, activation = Activations.relu),
    Layers.dense(10, Activations.softmax)
  )

  def train(model: Model[java.lang.Float]): Model[java.lang.Float] = {
    val graph: Graph = new Graph()
    val tf: Ops = Ops.create(graph)
    val data: Pair[GraphLoader[java.lang.Float], GraphLoader[java.lang.Float]] = FashionMNIST.graphLoaders2D()
    val train = data.first()
    val test = data.second()

    // Compile and Fit Model
    model.compile(tf, optimizer = Optimizers.sgd, loss = Losses.sparseCategoricalCrossentropy, metrics = List(Metrics.accuracy))
    model.fit(tf, train, test, epochs = 10, batchSize = 100)

    // Close Resources
    test.close()
    train.close()
    graph.close()

    // Output Model
    model
  }

  def main(args: Array[String]): Unit = {
    train(model)
  }
}
