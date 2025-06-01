/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  00:12
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data.templates.original_templates

import cn.timaviciix.ebm.data.nbt.NbtResolver
import cn.timaviciix.ebm.data.nbt.NbtStorageType
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.nbt.NbtCompound

class ElementTemplate<T>(
    val id: String,
    val storageType: NbtStorageType,
    private val nbtResolver: NbtResolver<T>,
    private val defaultValue: T?
) {
    private var elementValue: T? = null

    fun injectValue(targetValue: T) {
        elementValue = targetValue
    }

    fun getValue():T? = elementValue

    fun safetyGetValue(outDefault: T): T = elementValue ?: defaultValue ?: outDefault
    fun riskyGetValue(): T? = elementValue ?: defaultValue
    fun valueToString(): String = elementValue.toString()


    fun readFromNbt(nbt: NbtCompound) {
        elementValue = nbtResolver.readFrom(nbt)
    }

    fun saveToNbt(nbt: NbtCompound) {
        if (elementValue != null) {
            nbtResolver.saveTo(nbt, elementValue!!)
        } else if (defaultValue != null) {
            nbtResolver.saveTo(nbt, defaultValue)
            GlobalData.LOGGER.info("Use Default Element Value:[$id]")
        } else {
            GlobalData.LOGGER.info("Can't Find Element Value:[$id]")
        }
    }

}