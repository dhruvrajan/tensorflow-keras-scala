package org.tensorflow.keras.scala

import org.tensorflow.keras.activations.{Activations, Activation => JActivation}

import scala.language.implicitConversions


case class Activation[T <: Number](self: JActivation[T])

object Activation {
    implicit def convert[T <: Number](a: Activations): Activation[T] = Activation(Activations.select(a))

    implicit def convert[T <: Number](a: JActivation[T]): Activation[T] = Activation[T](a)
}