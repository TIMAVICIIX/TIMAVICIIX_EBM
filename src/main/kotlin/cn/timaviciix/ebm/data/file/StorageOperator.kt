/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data.book.storage
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-04-29  18:13
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data.file

import cn.timaviciix.ebm.config.ConfigCreator
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtIo
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Path
import java.util.*

object StorageOperator {

    val bookStoragePath: Path = Path.of(ConfigCreator.modConfigEntity.bookStoragePath)
    val bookDataStoragePath: Path = bookStoragePath.resolve("book_data")

    fun createStorageFolder() {
        if (!Files.exists(bookStoragePath)) {
            Files.createDirectory(bookStoragePath)
        }
        if (!Files.exists(bookDataStoragePath)) {
            Files.createDirectory(bookDataStoragePath)
        }
    }

    fun writePlayerExternalBook(playerUUID: UUID, bookId: String, nbt: NbtCompound): Boolean {
        return try {
            val playerFolder = bookDataStoragePath.resolve(playerUUID.toString())
            Files.createDirectories(playerFolder) // 若不存在就创建

            val targetFile = playerFolder.resolve("$bookId.nbt").toFile()
            FileOutputStream(targetFile).use { out ->
                NbtIo.writeCompressed(nbt, out)
            }

            true
        } catch (e: Exception) {
            GlobalData.LOGGER.error("Storage Write failed:")
            e.printStackTrace()
            false
        }
    }

    fun readPlayerExternalBook(playerUUID: UUID, bookId: String): NbtCompound? {
        return try {
            val filePath = bookDataStoragePath
                .resolve(playerUUID.toString())
                .resolve("$bookId.nbt")
                .toFile()

            if (!filePath.exists()) return null

            FileInputStream(filePath).use { input ->
                NbtIo.readCompressed(input)
            }
        } catch (e: Exception) {
            GlobalData.LOGGER.error("Storage Read failed:")
            e.printStackTrace()
            null
        }
    }
}