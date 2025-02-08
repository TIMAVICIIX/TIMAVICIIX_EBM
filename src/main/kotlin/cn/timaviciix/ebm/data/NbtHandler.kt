package cn.timaviciix.ebm.data

import net.minecraft.nbt.NbtByteArray
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtList

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

    fun saveToNbt(nbt: NbtCompound, boolean: Boolean, id: String) {
        nbt.putBoolean(id, boolean)
    }

    fun saveToNbt(nbt: NbtCompound, string: String, id: String) {
        nbt.putString(id, string)
    }

    fun loadByteArrayFromNbt(nbt: NbtCompound, contentId: String): MutableList<ByteArray> {
        val listTag = nbt.get(contentId) as? NbtList ?: return mutableListOf()

        return listTag.mapNotNull { tag ->
            (tag as? NbtByteArray)?.byteArray
        }.toMutableList()
    }

}