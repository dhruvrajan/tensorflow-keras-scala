package org.tensorflow.keras.scala

import org.tensorflow.utils.{Pair => JPair}

import scala.language.implicitConversions

object Pair {
  implicit def convert[T, S](pair: JPair[T, S]): (T, S) = (pair.first(), pair.second())
}
