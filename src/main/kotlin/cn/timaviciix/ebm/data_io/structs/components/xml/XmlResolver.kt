/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.components
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  00:25
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.components.xml

import cn.timaviciix.ebm.data_io.io.IOBus
import cn.timaviciix.ebm.util.GlobalData
import org.dom4j.Element

//XmlResolver是元数据类组件，判断元素是否需要XML存储，提供一个EMPTY预制类，不需要XML存储时直接入参EMPTY，需要时则入参其存储的xpath
class XmlResolver(private val xpath: MutableList<String>, private val attributes: Map<String, String>) :
    XmlResolveSupplier<String> {
    constructor(
        signalXpath: String,
        attributes: Map<String, String>
    ) : this(xpath = mutableListOf<String>().also { it.add(signalXpath) }, attributes = attributes)

    companion object {
        val EMPTY = XmlResolver(mutableListOf(), mapOf())
    }

    override val supplierAttributes: Map<String, String> = attributes
    override val supplierXpath: MutableList<String> = xpath

    override fun saveToXml(root: Element, value: String) {
        saveXml(root, value)
    }

    private fun saveXml(
        root: Element,
        value: String,
    ) {
        root.let {
            if (xpath.isNotEmpty()) {
                val targetDom = IOBus.ensureElement(root, xpath)
                targetDom.text = value
                attributes.forEach { attr ->
                    @Suppress("DEPRECATION")
                    targetDom.setAttributeValue(attr.key, attr.value)
                }
            } else {
                GlobalData.LOGGER.info("XML Storage Miss Element:${xpath.last()}")
            }
        }
    }

}