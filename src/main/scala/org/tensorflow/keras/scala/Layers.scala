package org.tensorflow.keras.scala

import scala.language.implicitConversions
import org.tensorflow.keras.layers.{Dense, Layers => JLayers}


object Layers {
    def dense(units: Int, 
        activation: Activation[java.lang.Float] = Dense.Options.DEFAULT_ACTIVATION,
        kernelInitializer: Initializer[java.lang.Float] = Dense.Options.DEFAULT_KERNEL_INITIALIZER,
        biasInitializer: Initializer[java.lang.Float] = Dense.Options.DEFAULT_BIAS_INITIALIZER): Dense = {
    
        JLayers.dense(units, activation.self, kernelInitializer.self, biasInitializer.self)
    }
}