/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.io
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-05  21:10
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.io

import cn.timaviciix.ebm.data_io.structs.components.nbt.NbtResolver
import cn.timaviciix.ebm.data_io.structs.templates.original_templates.WarpedTemplate
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.nbt.NbtCompound

object IOBus {

    fun WarpedTemplate.saveToNbt(nbt:NbtCompound):Boolean{
        this.elements.forEach { element ->
            val elementValue = element.safetyGetValue()
            if (elementValue != null) {
                // 通过 TypeToken 检查类型
                if (element.typeToken.type == elementValue::class.java.genericSuperclass) {
                    @Suppress("UNCHECKED_CAST")
                    (element.nbtResolver as NbtResolver<Any>).saveTo(nbt, elementValue)
                } else {
                    throw IllegalStateException("Type mismatch for element ${element.id}")
                }
            }else{
                throw IllegalStateException("Element mismatch of value ${element.id}")
            }
        }
        return true
    }

}