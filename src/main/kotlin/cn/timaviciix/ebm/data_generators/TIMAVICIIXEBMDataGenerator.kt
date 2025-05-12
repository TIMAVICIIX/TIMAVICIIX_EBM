package cn.timaviciix.ebm.data_generators

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object TIMAVICIIXEBMDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {

        fabricDataGenerator.createPack().apply {

            //addProvider(::EBMModelGenerator)

        }

    }
}