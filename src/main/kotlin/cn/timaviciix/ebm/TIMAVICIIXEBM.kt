package cn.timaviciix.ebm

import cn.timaviciix.ebm.util.GlobalData
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object TIMAVICIIXEBM : ModInitializer {

    private val logger = LoggerFactory.getLogger(GlobalData.MOD_ID)

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("Hello Fabric world!")
	}
}