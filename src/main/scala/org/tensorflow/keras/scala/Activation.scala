package org.tensorflow.keras.scala

import scala.language.implicitConversions

import org.tensorflow.keras.mixin.ActivationFunction
import org.tensorflow.keras.activations.{Activations, Activation => JActivation}

case class Activation[T <: java.lang.Number](self: JActivation[T])

object Activation {
    implicit def convert(a: Activations): Activation[java.lang.Float] = Activation[java.lang.Float](Activations.select(a))

    implicit def convert[T <: java.lang.Number](a: JActivation[T]): Activation[T] = Activation[T](a)
}