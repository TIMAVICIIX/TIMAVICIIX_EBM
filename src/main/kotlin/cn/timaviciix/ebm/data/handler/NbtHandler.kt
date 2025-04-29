package cn.timaviciix.ebm.data.handler

import cn.timaviciix.ebm.data.book.BookDataConfig
import cn.timaviciix.ebm.data.book.CopyPermission
import cn.timaviciix.ebm.util.CompressUtil.compressString
import cn.timaviciix.ebm.util.CompressUtil.decompressString
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.nbt.NbtByteArray
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtList
import net.minecraft.nbt.NbtString
import java.util.*

object NbtHandler {

    //Save Nbt Methods
    fun MutableList<String>.saveToNbt(
        nbt: NbtCompound,
        contentId: String,
    ) {
        nbt.put(contentId, NbtList().apply {
            this@saveToNbt.forEach { add(NbtByteArray(compressString(it))) }
        })
    }

    fun CopyPermission.saveToNbt(nbt: NbtCompound) {
        nbt.apply {
            putBoolean(BookDataConfig.BOOK_IS_COPIES_NBT_ID,this@saveToNbt.isCopies)
            putBoolean(BookDataConfig.BOOK_STAMPING_STATE_NBT_ID, this@saveToNbt.stampingState)
            nbt.put(BookDataConfig.BOOK_COPY_GRANTEES_NBT_ID, NbtList().apply {
                this@saveToNbt.copyGrantees.forEach {
                    add(NbtString.of(it.toString()))
                }
            })
        }
    }

    fun Map<String, Int>.saveToNbt(nbt: NbtCompound, id: String) {
        val serializedMap = this.entries.joinToString(",") { "${it.key}:${it.value}" }
        nbt.putString(id, serializedMap)
    }

    fun Boolean.saveToNbt(nbt: NbtCompound, id: String) {
        nbt.putBoolean(id, this)
    }

    fun String.saveToNbt(nbt: NbtCompound, id: String) {
        nbt.putString(id, this)
    }

    fun Int.saveToNbt(nbt: NbtCompound, id: String) {
        nbt.putInt(id, this)
    }

    fun loadCopyPermissionFromNbt(nbt: NbtCompound,playerUUID:UUID?): CopyPermission {
        val stampingState = nbt.getBoolean(BookDataConfig.BOOK_STAMPING_STATE_NBT_ID)
        val isCopies = nbt.getBoolean(BookDataConfig.BOOK_IS_COPIES_NBT_ID)
        val list = nbt.getList(BookDataConfig.BOOK_COPY_GRANTEES_NBT_ID, 8)
        val copyGrantees = mutableListOf<UUID>()
        for (i in 0 until list.size) {
            val uuidStr = list.getString(i)
            try {
                copyGrantees.add(UUID.fromString(uuidStr))
            } catch (e: Exception) {
                GlobalData.LOGGER.info("$uuidStr Copy Permission Load Field!!!")
            }
        }
        if (copyGrantees.isEmpty() && playerUUID!=null){
            copyGrantees.add(playerUUID)
        }
        return CopyPermission.duplicateCopyPermission(stampingState, isCopies,copyGrantees)
    }

    fun loadStringMapFromNbt(nbt: NbtCompound, contentId: String): MutableMap<Int, String> {
        val listTag = nbt.get(contentId) as? NbtList ?: return mutableMapOf()

        listTag.let {
            return mutableMapOf<Int, String>().apply {
                for ((index, element) in it.withIndex()) {
                    val stringContent = (element as? NbtByteArray)?.byteArray?.decompressString()
                    stringContent?.let { this[index] = stringContent }
                }
            }
        }
    }

    fun loadPageTagsFromNbt(nbt: NbtCompound, id: String): Map<String, Int> {
        val map = mutableMapOf<String, Int>()
        val serializedMap = nbt.getString(id)

        if (serializedMap.isNotEmpty()) {
            for (entry in serializedMap.split(",")) {
                val (key, value) = entry.split(":")
                map[key] = value.toInt()
            }
        }

        return map
    }

}