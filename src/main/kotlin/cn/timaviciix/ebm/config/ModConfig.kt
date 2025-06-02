/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.config
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-06-02  16:46
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.config

import cn.timaviciix.ebm.util.GlobalData
import com.google.gson.GsonBuilder
import java.io.File

object ModConfig {
    private const val CONFIG_NAME = "${GlobalData.MOD_ID}.json"
    private val gson = GsonBuilder().setPrettyPrinting().create()
    private val configFile = File("config/$CONFIG_NAME")

    var config: Config = Config()

    data class Config(
        var storageRootPath: String = GlobalData.MOD_ID
    )

    fun load() {
        if (configFile.exists()) {
            try {
                val reader = configFile.bufferedReader()
                config = gson.fromJson(reader, Config::class.java)
                reader.close()
            } catch (e: Exception) {
                GlobalData.LOGGER.info("[timaviciix_ebm] Failed to load config: ${e.message}")
            }
        } else {
            save() // 如果文件不存在，保存默认配置
        }
        GlobalData.LOGGER.info("[timaviciix_ebm] Storage path: ${config.storageRootPath}")
    }

    fun save() {
        try {
            configFile.parentFile.mkdirs()
            configFile.writeText(gson.toJson(config))
        } catch (e: Exception) {
            GlobalData.LOGGER.info("[timaviciix_ebm] Failed to save config: ${e.message}")
        }
    }
}