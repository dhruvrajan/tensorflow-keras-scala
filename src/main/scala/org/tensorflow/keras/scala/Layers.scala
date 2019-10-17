package org.tensorflow.keras.scala
import org.tensorflow.keras.layers.{Dense, Flatten, Input, Layers => JLayers}


object Layers {
    object defaults {
        lazy val dense: Dense.Options = Dense.Options.defaults()
    }

    def input[T <: Number](firstDim: Long, units: Long*): Input[T] = JLayers.input[T](firstDim, units: _*)

    def flatten[T <: Number](): Flatten[T] = JLayers.flatten[T]

    def dense[T <: Number](units: Int,
        activation: Activation[T] = defaults.dense.getActivation[T],
        kernelInitializer: Initializer = defaults.dense.getBiasInitializer,
        biasInitializer: Initializer = defaults.dense.getKernelInitializer): Dense[T] = {


        JLayers.dense[T](units, activation.self, kernelInitializer.self, biasInitializer.self)
    }
}