package org.tensorflow.keras.scala

import org.tensorflow.keras.optimizers.{Optimizers, Optimizer => JOptimizer}

import scala.language.implicitConversions

case class Optimizer[T <: Number](self: JOptimizer[T])

object Optimizer {
  implicit def convert[T <: Number](a: Optimizers): Optimizer[T] = Optimizer[T](Optimizers.select(a))

  implicit def convert[T <: Number](a: JOptimizer[T]): Optimizer[T] = Optimizer[T](a)
}