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
import org.dom4j.Document
import org.dom4j.DocumentHelper
import org.dom4j.Element
import org.dom4j.io.SAXReader
import org.dom4j.tree.DefaultElement
import java.io.InputStream

object IOBus {

    fun WarpedTemplate.read(nbt: NbtCompound, inputStream: InputStream): WarpedTemplate {
        this.readFromNbt(nbt)
        this.readFromXml(inputStream)
        return this
    }

    fun WarpedTemplate.save(nbt: NbtCompound) {
        saveToNbt(nbt)
        saveToXml()
    }


    private fun WarpedTemplate.readFromNbt(nbt: NbtCompound): WarpedTemplate {
        this.elements.forEach { element ->
            element.readFromNbt(nbt)
        }
        return this
    }


    private fun WarpedTemplate.saveToNbt(nbt: NbtCompound): Boolean {
        this.elements.forEach { element ->
            element.saveToNbt(nbt)
        }
        return true
    }

    private fun WarpedTemplate.readFromXml(inputStream: InputStream): WarpedTemplate {
        val saxReader = SAXReader()
        val document: Document = saxReader.read(inputStream)
        this.elements.forEach {
            it.readFromXml(document.rootElement)
        }
        return this
    }

    private fun WarpedTemplate.saveToXml() {
        val document = DocumentHelper.createDocument()
        val ebm = document.addElement("ebm")
        this.elements.forEach {
            it.saveToXml(ebm)
        }
    }

    private fun readFromXml() {

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


    inline fun <reified T> readElementFromXpath(
        root: Element,
        xpath: List<String>,
        crossinline converter: (Element) -> T?
    ): T? {
        var current = root
        for (nodeName in xpath) {
            val next = current.element(nodeName) ?: return null
            current = next
        }
        return converter(current)
    }
}
