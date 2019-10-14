Tensorflow Keras (Scala)
-----

This repository contains a Scala wrapper for tensorflow-keras-java; an implementation of
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
object FashionMNISTKeras {
  val model: Model[JFloat] = Sequential.of(
    input(28, 28),
    flatten(),
    dense(128, activation = relu),
    dense(10, activation = softmax)
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
    train(model)
  }
}
```