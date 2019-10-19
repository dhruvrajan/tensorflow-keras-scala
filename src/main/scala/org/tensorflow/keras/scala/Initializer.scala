package org.tensorflow.keras.scala

import scala.language.implicitConversions
import org.tensorflow.keras.initializers.{Initializers, Initializer => JInitializer}

case class Initializer(self: JInitializer)

object Initializer {
    implicit def convert(a: Initializers): Initializer = Initializer(Initializers.select(a))
    implicit def convert(a: JInitializer): Initializer = Initializer(a)
}