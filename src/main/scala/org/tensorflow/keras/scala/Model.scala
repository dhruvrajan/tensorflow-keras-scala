package org.tensorflow.keras.scala

import scala.language.implicitConversions
import org.tensorflow.keras.models.{Model => JModel}

case class Model[T <: java.lang.Number](self: JModel[T]) {
  
}

object Model {
   implicit def convert[T <: java.lang.Number](a: JModel[T]): Model[T] = Model[T](a)
}