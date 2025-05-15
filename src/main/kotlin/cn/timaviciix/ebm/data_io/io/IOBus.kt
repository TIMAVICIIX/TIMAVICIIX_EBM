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
import org.dom4j.Element
import org.dom4j.tree.DefaultElement

object IOBus {

    fun WarpedTemplate.read(nbt: NbtCompound): WarpedTemplate {
        return readFromNbt(this, nbt)
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
        val ebm = document.addElement("ebm")
        targetTemplate.elements.forEach {
            it.saveToXml(ebm)
        }
    }

    fun compressDocument() {

    }

    fun compressedAsyncConn() {

    }

    //This Method ensure Signal Element in Document
    fun ensureElement(root: Element, xpath: List<String>): Element {
        var current = root
        for (nodeName in xpath) {
            val next = current.element(nodeName)
            current = if (next != null) {
                next
            } else {
                val created = DefaultElement(nodeName)
                current.add(created)
                created
            }
        }
        return current
    }
}
