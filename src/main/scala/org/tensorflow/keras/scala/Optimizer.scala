package org.tensorflow.keras.scala

import scala.language.implicitConversions
import org.tensorflow.keras.optimizers.{Optimizers, Optimizer => JOptimizer}

case class Optimizer[T <: java.lang.Number](self: JOptimizer[T])

object Optimizer {
  implicit def convert(a: Optimizers): Optimizer[java.lang.Float] = Optimizer[java.lang.Float](Optimizers.select(a))

  implicit def convert[T <: java.lang.Number](a: JOptimizer[T]): Optimizer[T] = Optimizer[T](a)
}