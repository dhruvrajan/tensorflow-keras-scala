Tensorflow Keras (Scala)
-----

This repository contains a Scala wrapper for [Tensorflow Keras Java](https://github.com/dhruvrajan/tensorflow-keras-java); an implementation of
the high-level Keras API for training deep learning models. Using the scala wrapper allows
for a more concise representation of Keras model training, with syntax very close to that of 
the Python API.

*Python*
```python
import tensorflow as tf

model = tf.keras.models.Sequential([
  tf.keras.layers.Flatten(input_shape=(28, 28)),
  tf.keras.layers.Dense(128, activation='relu', kernel_initializer="random_normal", bias_initializer="zeros"),
  tf.keras.layers.Dense(10, activation='softmax', kernel_initializer="random_normal", bias_initializer="zeros")
])

model.compile(optimizer='sgd', loss='sparse_categorical_crossentropy', metrics=['accuracy'])

(X_train, y_train), (X_val, y_val) = tf.keras.datasets.load_mnist()
model.fit(X_train, y_train, val_data=(X_val, y_val), epochs=10, batch_size=100)
```

*Scala*:
```scala
package org.tensorflow.keras.scala.examples

import java.lang.{Float => JFloat}

import org.tensorflow.Graph
import org.tensorflow.data.GraphLoader
import org.tensorflow.keras.activations.Activations.{relu, softmax}
import org.tensorflow.keras.datasets.FashionMNIST
import org.tensorflow.keras.initializers.Initializers.{randomNormal, zeros}
import org.tensorflow.keras.losses.Losses.sparseCategoricalCrossentropy
import org.tensorflow.keras.metrics.Metrics.accuracy
import org.tensorflow.keras.models.Sequential
import org.tensorflow.keras.optimizers.Optimizers.sgd
import org.tensorflow.keras.scala.Layers.{dense, flatten, input}
import org.tensorflow.keras.scala.Model
import org.tensorflow.op.Ops
import org.tensorflow.utils.Pair

import scala.util.Using

object FashionMNISTKeras {

  val model: Model[JFloat] = Sequential.of[JFloat](
    classOf[JFloat],
    input(28, 28),
    flatten(),
    dense(128, activation = relu, kernelInitializer = randomNormal, biasInitializer = zeros),
    dense(10, activation = softmax, kernelInitializer = randomNormal, biasInitializer = zeros)
  )

  def train(model: Model[JFloat]): Model[JFloat] = {
    Using.resource(new Graph()) { graph => {
      val tf: Ops = Ops.create(graph)
      model.compile(tf, optimizer = sgd, loss = sparseCategoricalCrossentropy, metrics = List(accuracy))

      val data: Pair[GraphLoader[JFloat], GraphLoader[JFloat]] = FashionMNIST.graphLoaders2D()
      // GraphLoader objects contain AutoCloseable `Tensors`.
      Using.resources(data.first(), data.second()) { (train, test) => {
        model.fit(tf, train, test, epochs = 10, batchSize = 100)
      }}
    }}

    model
  }

  def main(args: Array[String]): Unit = {
    train(model.self)
  }
}
```