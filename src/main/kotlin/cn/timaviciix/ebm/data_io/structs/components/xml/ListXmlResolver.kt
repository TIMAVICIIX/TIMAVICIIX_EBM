/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.components.xml
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-15  23:23
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.components.xml

import cn.timaviciix.ebm.data_io.io.IOBus
import cn.timaviciix.ebm.util.GlobalData
import org.dom4j.Element

class ListXmlResolver(
    private val warpXpath: MutableList<String>,
    private val itemElementId: String,
    private val itemStringAttributes: Map<String, String>,
    private val itemDigitAttributes: Map<String, Pair<Int, Boolean>>
) : XmlResolveSupplier<MutableList<String>> {

    constructor(
        signalXpath: String,
        itemElementId: String,
        itemStringAttributes: Map<String, String>,
        itemDigitAttributes: Map<String, Pair<Int, Boolean>>
    ) : this(mutableListOf(signalXpath), itemElementId, itemStringAttributes, itemDigitAttributes)

    override val supplierAttributes: Map<String, String> = itemStringAttributes
    override val supplierXpath: MutableList<String> = warpXpath

    override fun saveToXml(root: Element, value: MutableList<String>) {
        saveXml(root, value)
    }

    override fun readFromXml(root: Element): XmlReadOut<MutableList<String>>? {
        if (warpXpath.isEmpty()) return null

        val list = mutableListOf<String>()
        var current = root

        for (nodeName in warpXpath) {
            val next = current.element(nodeName) ?: return null
            current = next
        }

        // 提取所有子元素 itemElementId
        val items = current.elements(itemElementId)
        for (item in items) {
            list.add(item.text)
        }

        return XmlReadOut(list, mapOf(), mapOf())

    }

    private fun saveXml(
        root: Element,
        value: MutableList<String>,
    ) {
        root.let {
            if (warpXpath.isNotEmpty()) {
                val warpElement = IOBus.ensureElement(root, warpXpath)
                val incrementCache = mutableMapOf<String, Int>()
                value.forEach {
                    val currentItem = warpElement.addElement(itemElementId)
                    currentItem.text = it

                    itemStringAttributes.forEach { (key, value) ->
                        currentItem.addAttribute(key, value)
                    }
                    itemDigitAttributes.forEach { (key, value) ->
                        if (value.second) {
                            currentItem.addAttribute(key, value.first.toString())
                        } else {
                            if (incrementCache.contains(key)) {
                                currentItem.addAttribute(key, incrementCache[key]!!.plus(1).toString())
                            } else {
                                currentItem.addAttribute(key, value.first.toString())
                                incrementCache[key] = value.first
                            }
                        }
                    }
                }
            } else {
                GlobalData.LOGGER.info("XML Storage Miss Element:${warpXpath.last()}")
            }
        }

    }


}