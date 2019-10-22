package org.tensorflow.keras.scala

import org.tensorflow.{Operand, Shape => JShape}
import org.tensorflow.keras.layers.{Layer => JLayer}
import org.tensorflow.op.Ops

case class Layer[T <: Number](self: JLayer[T]) {
  def build(inputShape: JShape, dtype: Class[T])(implicit tf: Ops) : Unit = {
    self.build(tf, inputShape, dtype)
  }

  def apply(inputs: Operand[T]*)(implicit tf: Ops): Operand[T] = {
    self.apply(tf, inputs: _*)
  }
}

object Layer {

}