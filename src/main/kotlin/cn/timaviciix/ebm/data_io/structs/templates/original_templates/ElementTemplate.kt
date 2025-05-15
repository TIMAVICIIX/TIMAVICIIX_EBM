/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  00:12
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.templates.original_templates

import cn.timaviciix.ebm.data_io.structs.components.nbt.NbtResolver
import cn.timaviciix.ebm.data_io.structs.components.xml.XmlResolveSupplier
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.nbt.NbtCompound
import org.dom4j.Element

class ElementTemplate<T>(
    val id: String,
    val xmlResolver: XmlResolveSupplier<T>,
    val nbtResolver: NbtResolver<T>,
    private val defaultValue: T?
) {
    private var elementValue: T? = null

    fun injectValue(targetValue: T) {
        elementValue = targetValue
    }

    fun safetyGetValue(): T? = elementValue ?: defaultValue

    fun valueToString(): String = elementValue.toString()

    fun readFrom(nbt: NbtCompound) {
        elementValue = nbtResolver.readFrom(nbt)
    }

    fun saveToNbt(nbt: NbtCompound) {
        if (elementValue != null) {
            nbtResolver.saveTo(nbt, elementValue!!)
        } else if (defaultValue != null) {
            nbtResolver.saveTo(nbt, defaultValue)
            GlobalData.LOGGER.info("Use Default Element Value[$id]")
        } else {
            GlobalData.LOGGER.info("Can't Find Element Value:[$id]")
        }
    }

    fun saveToXml(root: Element) {
        if (elementValue != null) {
            xmlResolver.saveToXml(root, elementValue!!)
        } else if (defaultValue != null) {
            xmlResolver.saveToXml(root, defaultValue)
            GlobalData.LOGGER.info("Use Default Element Value[$id]")
        } else {
            GlobalData.LOGGER.info("Can't Find Element Value:[$id]")
        }
    }

}