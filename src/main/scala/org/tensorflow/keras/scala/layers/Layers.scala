package org.tensorflow.keras.scala.layers

import scala.language.implicitConversions
import org.tensorflow.keras.activations.{Activation, Activations}
import org.tensorflow.keras.initializers.{Initializer, Initializers}
import org.tensorflow.keras.layers.{Dense, Layers => KerasLayers}


package object layers {
    // given fromActivations : Conversion[Activations, Activation[java.lang.Float]] = Activations.select(_)

    // implicit def getActivation(a: Activations) : Activation[java.lang.Float] = Activations.select(a)

    implicit def getActivation(activation: Activations | Activation[java.lang.Float]): Activation[java.lang.Float] = {
        activation match {
            case a: Activations => Activations.select(a)
            case a: Activation[java.lang.Float] => a;
        }
    }

    implicit def getInitializer(initializer: Initializers | Initializer[java.lang.Float]): Initializer[java.lang.Float] = {
        initializer match {
            case a: Initializers => Initializers.select(a)
            case a: Initializer[java.lang.Float] => a;
        }
    }

    // implicit def activation(a: Activations): Activation[java.lang.Float] = Activations.select(a)

    def dense(units: Int, 
        activation: Activations | Activation[java.lang.Float] = Dense.Options.DEFAULT_ACTIVATION,
        kernelInitializer: Initializers | Initializer[java.lang.Float] = Dense.Options.DEFAULT_KERNEL_INITIALIZER,
        biasInitializer: Initializers | Initializer[java.lang.Float] = Dense.Options.DEFAULT_BIAS_INITIALIZER): Dense = {
    
        KerasLayers.dense(units, activation, kernelInitializer, biasInitializer)
    }

    def main() = {
        dense(10, Activations.linear)
    }
}