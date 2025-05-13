/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.components
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  01:38
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.components.nbt

import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.nbt.*
import java.util.*

abstract class NbtResolver<T>() {

    protected var id: String? = null
    abstract fun saveTo(nbt: NbtCompound, value: T)
    abstract fun readFrom(nbt: NbtCompound, default: T? = null): T?

    fun injectId(inject: String) {
        id = inject
    }

    companion object {

        class NullResolver() : NbtResolver<String>() {
            override fun saveTo(nbt: NbtCompound, value: String) {
            }
            override fun readFrom(nbt: NbtCompound, default: String?): String? {
                return null
            }
        }

        class StringResolver() : NbtResolver<String>() {
            override fun readFrom(nbt: NbtCompound, default: String?): String? {
                return nbt.getString(id)
            }

            override fun saveTo(nbt: NbtCompound, value: String) {
                nbt.putString(id, value)
            }
        }

        class BooleanResolver() : NbtResolver<Boolean>() {
            override fun readFrom(nbt: NbtCompound, default: Boolean?): Boolean? {
                return nbt.getBoolean(id)
            }

            override fun saveTo(nbt: NbtCompound, value: Boolean) {
                nbt.putBoolean(id, value)
            }
        }

        class UUIDResolver() : NbtResolver<UUID>() {
            override fun saveTo(nbt: NbtCompound, value: UUID) {
                nbt.putUuid(id, value)
            }

            override fun readFrom(nbt: NbtCompound, default: UUID?): UUID? {
                return nbt.getUuid(id)
            }
        }

        class IntResolver() : NbtResolver<Int>() {
            override fun saveTo(nbt: NbtCompound, value: Int) {
                nbt.putInt(id, value)
            }

            override fun readFrom(nbt: NbtCompound, default: Int?): Int? {
                return nbt.getInt(id)
            }
        }

        class UUIDListResolver() : NbtResolver<List<UUID>>() {
            override fun readFrom(nbt: NbtCompound, default: List<UUID>?): List<UUID>? {
                if (!nbt.contains(id, NbtElement.LIST_TYPE.toInt())) {
                    return default
                }
                val nbtList = nbt.getList(id, NbtElement.STRING_TYPE.toInt())
                val uuidList = mutableListOf<UUID>()

                nbtList.forEach { element ->
                    try {
                        uuidList.add(UUID.fromString(element.asString()))
                    } catch (e: IllegalArgumentException) {
                        GlobalData.LOGGER.info("Find excepted UUID:${e.printStackTrace()}")
                    }
                }

                return uuidList.ifEmpty { default }
            }

            override fun saveTo(nbt: NbtCompound, value: List<UUID>) {
                val nbtList = NbtList()
                value.forEach { uuid ->
                    nbtList.add(NbtString.of(uuid.toString()))
                }
                nbt.put(id, nbtList)
            }
        }

        class IntListResolver() : NbtResolver<List<Int>>() {
            override fun readFrom(nbt: NbtCompound, default: List<Int>?): List<Int>? {
                if (!nbt.contains(id, NbtElement.LIST_TYPE.toInt())) {
                    return default
                }
                val nbtList = nbt.getList(id, NbtElement.INT_TYPE.toInt())
                val intList = mutableListOf<Int>()

                nbtList.forEach { element ->
                    try {
                        intList.add(element.asString().toInt())
                    } catch (e: IllegalArgumentException) {
                        GlobalData.LOGGER.info("Find excepted Int:${e.printStackTrace()}")
                    }
                }

                return intList.ifEmpty { default }
            }

            override fun saveTo(nbt: NbtCompound, value: List<Int>) {
                val nbtList = NbtList()
                value.forEach { int ->
                    nbtList.add(NbtInt.of(int))
                }
                nbt.put(id, nbtList)
            }
        }

    }

}