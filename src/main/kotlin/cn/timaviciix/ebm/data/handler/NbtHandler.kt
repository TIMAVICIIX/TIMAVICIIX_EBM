package cn.timaviciix.ebm.data.handler

import cn.timaviciix.ebm.data.book.BookDataConfig
import cn.timaviciix.ebm.data.book.CopyPermission
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.nbt.NbtByteArray
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtList
import net.minecraft.nbt.NbtString
import java.util.*

object NbtHandler {

    //Save Nbt Methods
    fun saveToNbt(
        nbt: NbtCompound,
        nbtByteArray: MutableList<ByteArray>,
        contentId: String,
        sizeId: String = contentId + "_size"
    ) {
        nbt.put(contentId, NbtList().apply {
            nbtByteArray.forEach { add(NbtByteArray(it)) }
        })
        nbt.putInt(sizeId, nbtByteArray.size)
    }

    fun CopyPermission.saveToNbt(nbt: NbtCompound) {
        nbt.apply {
            putBoolean(BookDataConfig.BOOK_STAMPING_STATE_NBT_ID, this@saveToNbt.getStampingState())

            nbt.put(BookDataConfig.BOOK_COPY_GRANTEES_NBT_ID, NbtList().apply {
                this@saveToNbt.getCopyGrantees().forEach {
                    add(NbtString.of(it.toString()))
                }
            })
        }
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

    fun loadCopyPermissionFromNbt(nbt: NbtCompound): CopyPermission {
        val stampingState = nbt.getBoolean(BookDataConfig.BOOK_STAMPING_STATE_NBT_ID)
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
        return CopyPermission.duplicateCopyPermission(stampingState, copyGrantees)
    }

    fun loadByteArrayFromNbt(nbt: NbtCompound, contentId: String): MutableList<ByteArray> {
        val listTag = nbt.get(contentId) as? NbtList ?: return mutableListOf()

        return listTag.mapNotNull { tag ->
            (tag as? NbtByteArray)?.byteArray
        }.toMutableList()
    }

}