package org.tensorflow.keras.scala

import org.tensorflow.data.GraphLoader
import org.tensorflow.keras.losses.Losses
import org.tensorflow.keras.metrics.Metrics

import scala.language.implicitConversions
import org.tensorflow.keras.models.{Model => JModel}
import org.tensorflow.keras.optimizers.Optimizers
import org.tensorflow.op.Ops

case class Model[T <: java.lang.Number](self: JModel[T]) {

  def compile(tf: Ops, optimizer: Optimizer[java.lang.Float] = Optimizers.sgd,
              loss: Loss = Losses.sparseCategoricalCrossentropy,
              metrics: Seq[Metric] = List(Metrics.accuracy)): Unit = {
    val compileOptionsBuilder: JModel.CompileOptions.Builder = JModel.CompileOptions.builder()
      .setOptimizer(optimizer.self)
      .setLoss(loss.self)

    metrics.foreach((metric: Metric) => compileOptionsBuilder.addMetric(metric.self))

    self.compile(tf, compileOptionsBuilder.build());
  }

  def fit(tf: Ops, train: GraphLoader[T], test: GraphLoader[T],
          epochs: Int = JModel.FitOptions.DEFAULT_EPOCHS,
          batchSize: Int = JModel.FitOptions.DEFAULT_BATCH_SIZE): Unit = {

    self.fit(tf, train, test, JModel.FitOptions.builder().setEpochs(epochs).setBatchSize(batchSize).build())
  }

}

object Model {
   implicit def convert[T <: java.lang.Number](a: JModel[T]): Model[T] = Model[T](a)
}