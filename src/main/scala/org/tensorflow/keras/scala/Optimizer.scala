package org.tensorflow.keras.scala

import org.tensorflow.keras.optimizers.{Optimizers, Optimizer => JOptimizer}

import scala.language.implicitConversions

case class Optimizer[T <: java.lang.Number](self: JOptimizer[T])

object Optimizer {
  implicit def convert(a: Optimizers): Optimizer[java.lang.Float] = Optimizer[java.lang.Float](Optimizers.select(a))

  implicit def convert[T <: java.lang.Number](a: JOptimizer[T]): Optimizer[T] = Optimizer[T](a)
}