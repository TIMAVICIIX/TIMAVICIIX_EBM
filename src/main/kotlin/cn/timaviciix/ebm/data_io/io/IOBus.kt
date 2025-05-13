/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.io
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-05  21:10
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.io

import cn.timaviciix.ebm.data_io.structs.templates.original_templates.WarpedTemplate
import net.minecraft.nbt.NbtCompound
import org.dom4j.DocumentHelper

object IOBus {

    fun WarpedTemplate.read(nbt: NbtCompound): WarpedTemplate {
        return readFromNbt(this,nbt)
        //TODO
    }

    fun WarpedTemplate.save(nbt: NbtCompound) {
        saveToNbt(this, nbt)
        saveToXml(this)
    }

    private fun readFromNbt(targetTemplate: WarpedTemplate, nbt: NbtCompound): WarpedTemplate {
        targetTemplate.elements.forEach { element ->
            element.readFrom(nbt)
        }
        return targetTemplate
    }


    private fun saveToNbt(targetTemplate: WarpedTemplate, nbt: NbtCompound): Boolean {
        targetTemplate.elements.forEach { element ->
            element.saveToNbt(nbt)
        }
        return true
    }

    private fun saveToXml(targetTemplate: WarpedTemplate) {
        val document = DocumentHelper.createDocument()
        targetTemplate.elements.forEach {
            it.xmlResolver.saveToXml(document, it.valueToString())
        }
    }

    fun compressDocument() {

    }

    fun compressedAsyncConn() {

    }
}
