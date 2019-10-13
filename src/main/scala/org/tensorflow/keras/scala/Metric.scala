package org.tensorflow.keras.scala

import scala.language.implicitConversions
import org.tensorflow.keras.metrics.{Metrics, Metric => JMetrc}

case class Metric(self: JMetrc)

object Metric {
  implicit def convert(a: Metrics): Metric = Metric(Metrics.select(a))

  implicit def convert(a: JMetrc): Metric = Metric(a)
}