package org.tensorflow.keras.scala

import org.tensorflow.data.GraphLoader
import org.tensorflow.keras.models.Model.{CompileOptions, FitOptions}
import org.tensorflow.keras.models.{Model => JModel}
import org.tensorflow.op.Ops

import scala.jdk.javaapi.CollectionConverters
import scala.language.implicitConversions

case class Model[T <: java.lang.Number](self: JModel[T]) {

  object defaults {
    lazy val compile: CompileOptions = CompileOptions.defaults()
    lazy val fit: FitOptions = FitOptions.defaults()
  }

  def compile(optimizer: Optimizer[T] = defaults.compile.getOptimizer[T],
              loss: Loss = defaults.compile.getLoss,
              metrics: Seq[Metric]
                = CollectionConverters.asScala(defaults.compile.getMetrics).toSeq.map(Metric.convert))
             (implicit tf: Ops) : Unit = {
    val compileOptionsBuilder: JModel.CompileOptions.Builder = JModel.CompileOptions.builder()
      .setOptimizer(optimizer.self)
      .setLoss(loss.self)

    metrics.foreach((metric: Metric) => compileOptionsBuilder.addMetric(metric.self))
    self.compile(tf, compileOptionsBuilder.build());
  }

  def fit(train: GraphLoader[T], test: GraphLoader[T],
          epochs: Int = defaults.fit.getEpochs,
          batchSize: Int = defaults.fit.getBatchSize,
          callbacks: Seq[Callback] =
            CollectionConverters.asScala(defaults.fit.getCallbacks).toSeq.map(Callback.convert))
         (implicit tf: Ops): Unit = {


    val fitOptionsBuilder: JModel.FitOptions.Builder = JModel.FitOptions.builder()
        .setEpochs(epochs)
        .setBatchSize(batchSize)

    callbacks.foreach((callback: Callback) => fitOptionsBuilder.addCallback(callback.self))

    self.fit(tf,train, test, fitOptionsBuilder.build())
  }

}

object Model {
   implicit def convert[T <: java.lang.Number](a: JModel[T]): Model[T] = Model[T](a)
}