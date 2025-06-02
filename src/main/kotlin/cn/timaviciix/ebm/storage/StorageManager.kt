/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data.book.storage
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-04-29  18:13
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.storage

import cn.timaviciix.ebm.config.ModConfig
import cn.timaviciix.ebm.storage.naming.UUIDNamingStrategy
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtIo
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Path
import java.util.*

object StorageManager {

    private val rootPath: Path get() = Path.of(ModConfig.config.storageRootPath)

    private fun getCategoryPath(category: StorageCategory): Path {
        return rootPath.resolve(category.folderName)
    }

    fun initStorageFolders() {
        StorageCategory.entries.forEach { category ->
            val categoryPath = getCategoryPath(category)
            Files.createDirectories(categoryPath)
        }
    }

    fun writeNbtData(
        playerUUID: UUID,
        category: StorageCategory,
        dataId: String,
        nbt: NbtCompound,
        naming: NamingStrategy = UUIDNamingStrategy
    ): Boolean {
        return try {
            val playerFolder = getCategoryPath(category).resolve(playerUUID.toString())
            Files.createDirectories(playerFolder)

            val file = playerFolder.resolve(naming.fileNameFor(dataId)).toFile()
            FileOutputStream(file).use { out -> NbtIo.writeCompressed(nbt, out) }
            true
        } catch (e: Exception) {
            GlobalData.LOGGER.error("Failed to write NBT data ($category/$dataId) for player $playerUUID", e)
            false
        }
    }

    fun readNbtData(
        playerUUID: UUID,
        category: StorageCategory,
        dataId: String,
        naming: NamingStrategy = UUIDNamingStrategy
    ): NbtCompound? {
        return try {
            val file = getCategoryPath(category)
                .resolve(playerUUID.toString())
                .resolve(naming.fileNameFor(dataId))
                .toFile()

            if (!file.exists()) return null
            FileInputStream(file).use { NbtIo.readCompressed(it) }

        } catch (e: Exception) {
            GlobalData.LOGGER.error("Failed to read NBT data ($category/$dataId) for player $playerUUID", e)
            null
        }
    }
}
