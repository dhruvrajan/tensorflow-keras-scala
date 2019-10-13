package org.tensorflow.keras.scala

import org.tensorflow.keras.losses.{Losses, Loss => JLoss}

import scala.language.implicitConversions

case class Loss(self: JLoss)

object Loss {
  implicit def convert(a: Losses): Loss = Loss(Losses.select(a))

  implicit def convert(a: JLoss): Loss = Loss(a)
}