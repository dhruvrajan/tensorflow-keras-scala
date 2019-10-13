package org.tensorflow.keras.scala

import scala.language.implicitConversions

import org.tensorflow.keras.initializers.{Initializers, Initializer => JInitializer}

case class Initializer[T <: java.lang.Number](self: JInitializer[T])

object Initializer {
    implicit def convert(a: Initializers): Initializer[java.lang.Float] = Initializer[java.lang.Float](Initializers.select(a))

    implicit def convert[T <: java.lang.Number](a: JInitializer[T]): Initializer[T] = Initializer[T](a)
}