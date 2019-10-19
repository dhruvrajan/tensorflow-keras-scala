package org.tensorflow.keras.scala.examples

import java.lang.{Float => JFloat}

import org.tensorflow.Graph
import org.tensorflow.data.GraphLoader
import org.tensorflow.keras.activations.Activations.{relu, softmax}
import org.tensorflow.keras.callbacks.Callbacks
import org.tensorflow.keras.datasets.FashionMNIST
import org.tensorflow.keras.initializers.Initializers.{randomNormal, zeros}
import org.tensorflow.keras.losses.Losses.sparseCategoricalCrossentropy
import org.tensorflow.keras.metrics.Metrics.accuracy
import org.tensorflow.keras.models.Sequential
import org.tensorflow.keras.optimizers.Optimizers.sgd
import org.tensorflow.keras.scala.Layers.{dense, flatten, input}
import org.tensorflow.keras.scala.Model
import org.tensorflow.keras.scala.Pair._
import org.tensorflow.op.Ops

import scala.util.Using

object FashionMNISTKeras {

  val model: Model[JFloat] = Sequential.of[JFloat](classOf[JFloat],
    input(28, 28),
    flatten(),
    dense(128, activation = relu, kernelInitializer = randomNormal, biasInitializer = zeros),
    dense(10, activation = softmax, kernelInitializer = randomNormal, biasInitializer = zeros)
  )

  def train(model: Model[JFloat]): Model[JFloat] = {
    Using.resource(new Graph()) { graph => {
      val tf: Ops = Ops.create(graph)
      model.compile(tf, optimizer = sgd, loss = sparseCategoricalCrossentropy, metrics = List(accuracy))

      val (trainLoader, testLoader): (GraphLoader[JFloat], GraphLoader[JFloat]) = FashionMNIST.graphLoaders2D()
      // GraphLoader objects contain AutoCloseable `Tensors`.
      Using.resources(trainLoader, testLoader) { (train, test) => {
        model.fit(tf, train, test, epochs = 10, batchSize = 100, callbacks = List(Callbacks.baseCallback))
      }}
    }}

    model
  }

  def main(args: Array[String]): Unit = {
    train(model.self)
  }
}
