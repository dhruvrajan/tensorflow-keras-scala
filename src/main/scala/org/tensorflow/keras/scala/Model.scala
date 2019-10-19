package org.tensorflow.keras.scala

import org.tensorflow.data.GraphLoader
import org.tensorflow.keras.models.Model.{CompileOptions, FitOptions}
import org.tensorflow.keras.models.{Model => JModel}
import org.tensorflow.op.Ops

import scala.jdk.javaapi.CollectionConverters
import scala.language.implicitConversions

case class Model[T <: java.lang.Number](self: JModel[T]) {

  lazy val compileDefaults: CompileOptions = CompileOptions.defaults()
  def compile(tf: Ops, optimizer: Optimizer[T] = compileDefaults.getOptimizer[T],
              loss: Loss = compileDefaults.getLoss,
              metrics: Seq[Metric]
                = CollectionConverters.asScala(compileDefaults.getMetrics).toSeq.map(Metric.convert)): Unit = {
    val compileOptionsBuilder: JModel.CompileOptions.Builder = JModel.CompileOptions.builder()
      .setOptimizer(optimizer.self)
      .setLoss(loss.self)

    metrics.foreach((metric: Metric) => compileOptionsBuilder.addMetric(metric.self))
    self.compile(tf, compileOptionsBuilder.build());
  }

  lazy val fitDefaults: FitOptions = FitOptions.defaults();
  def fit(tf: Ops, train: GraphLoader[T], test: GraphLoader[T],
          epochs: Int = fitDefaults.getEpochs,
          batchSize: Int = fitDefaults.getBatchSize,
          callbacks: Seq[Callback] =
            CollectionConverters.asScala(fitDefaults.getCallbacks).toSeq.map(Callback.convert)): Unit = {


    val fitOptionsBuilder: JModel.FitOptions.Builder = JModel.FitOptions.builder()
        .setEpochs(epochs)
        .setBatchSize(batchSize)

    callbacks.foreach((callback: Callback) => fitOptionsBuilder.addCallback(callback.self))

    self.fit(tf, train, test, fitOptionsBuilder.build())
  }

}

object Model {
   implicit def convert[T <: java.lang.Number](a: JModel[T]): Model[T] = Model[T](a)
}