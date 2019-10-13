package org.tensorflow.keras.scala

import scala.language.implicitConversions
import org.tensorflow.keras.losses.{Losses, Loss => JLoss}

case class Loss(self: JLoss)

object Loss {
  implicit def convert(a: Losses): Loss = Loss(Losses.select(a))

  implicit def convert(a: JLoss): Loss = Loss(a)
}