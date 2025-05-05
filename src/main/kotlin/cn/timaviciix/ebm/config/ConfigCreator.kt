/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.config
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-04-29  18:42
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.config

import cn.timaviciix.ebm.util.GlobalData
import kotlinx.serialization.json.Json
import net.fabricmc.loader.api.FabricLoader
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object ConfigCreator {

    private val configPath = Paths.get(FabricLoader.getInstance().configDir.toString(), "${GlobalData.MOD_ID}.json")
    var modConfigEntity = TIMAVICIIXEBMConfig.initConfig()

    fun loadConfig() {
        try {
            if (Files.exists(configPath)) {
                val text = Files.readString(configPath)
                modConfigEntity = Json.decodeFromString<TIMAVICIIXEBMConfig>(text).also { it.checkEnable() }
                GlobalData.LOGGER.info("[ConfigCreator]Load Config Success:${modConfigEntity}")
            } else {
                saveConfig() // 保存默认配置
            }
        } catch (e: IOException) {
            GlobalData.LOGGER.info("[ConfigCreator]File Create or Read Failed!${e.printStackTrace()}")
        }
    }

    private fun saveConfig() {
        val configJsonContent = Json.encodeToString(TIMAVICIIXEBMConfig.serializer(), modConfigEntity)
        GlobalData.LOGGER.info("[ConfigCreator]Create Default Config File!\n$configJsonContent")
        Files.writeString(configPath, configJsonContent)
    }

}