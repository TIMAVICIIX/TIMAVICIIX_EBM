package cn.timaviciix.ebm.config


import cn.timaviciix.ebm.util.GlobalData
import kotlinx.serialization.Serializable
import net.fabricmc.loader.api.FabricLoader
import java.nio.file.Paths

@Serializable
data class TIMAVICIIXEBMConfig(
    var enable: Boolean,
    var bookStoragePath: String
) {

    override fun toString(): String {
        return """
            enable:$enable
            bookStoragePath:$bookStoragePath
        """.trimIndent()
    }

    fun checkEnable() {
        if (!enable) {
            GlobalData.LOGGER.info("[ConfigCreator]Use Default Path")
            bookStoragePath = getDefaultBookStoragePath()
        }
    }

    companion object {
        fun initConfig(): TIMAVICIIXEBMConfig {
            return TIMAVICIIXEBMConfig(
                enable = true,
                bookStoragePath = getDefaultBookStoragePath()
            )
        }

        private fun getDefaultBookStoragePath() = Paths.get(
            FabricLoader.getInstance().gameDir.toString(),
            GlobalData.MOD_ID
        )
            .toString()
    }

}