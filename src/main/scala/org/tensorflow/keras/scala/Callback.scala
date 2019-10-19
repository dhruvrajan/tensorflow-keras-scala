package org.tensorflow.keras.scala

import scala.language.implicitConversions
import org.tensorflow.keras.callbacks.{Callback => JCallback, Callbacks}

case class Callback(self: JCallback)

object Callback {
  implicit def convert(callback: Callbacks): Callback = Callback(Callbacks.select(callback))
  implicit def convert(callback: JCallback): Callback = Callback(callback)
}


