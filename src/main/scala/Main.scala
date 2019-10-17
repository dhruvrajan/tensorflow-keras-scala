//import org.tensorflow.Graph
//import org.tensorflow.data.GraphLoader
//import org.tensorflow.keras.activations.Activations.{relu, softmax}
//import org.tensorflow.keras.datasets.FashionMNIST
//import org.tensorflow.keras.losses.Losses.sparseCategoricalCrossentropy
//import org.tensorflow.keras.metrics.Metrics.accuracy
//import org.tensorflow.keras.models.Sequential
//import org.tensorflow.keras.optimizers.Optimizers.sgd
//import org.tensorflow.keras.scala.{Layers, Model}
//import org.tensorflow.op.Ops
//import org.tensorflow.utils.Pair
//
//import scala.util.Using
//
//object Main {
//  val model: Model[java.lang.Float] = Sequential.of(
//    Layers.input(28, 28),
//    Layers.flatten(),
//    Layers.dense(128, activation = relu),
//    Layers.dense(10, activation = softmax)
//  )
//
//  def train(model: Model[java.lang.Float]): Model[java.lang.Float] = {
//    Using.resource(new Graph()) { graph => {
//      val tf: Ops = Ops.create(graph)
//      model.compile(tf, optimizer = sgd, loss = sparseCategoricalCrossentropy, metrics = List(accuracy))
//
//      val data: Pair[GraphLoader[java.lang.Float], GraphLoader[java.lang.Float]] = FashionMNIST.graphLoaders2D()
//      // GraphLoader objects contain AutoCloseable `Tensors`.
//      Using.resources(data.first(), data.second()) { (train, test) => {
//        model.fit(tf, train, test, epochs = 10, batchSize = 100)
//      }
//      }
//    }
//    }
//
//    model
//  }
//
//  def main(args: Array[String]): Unit = {
//    train(model)
//  }
//}
