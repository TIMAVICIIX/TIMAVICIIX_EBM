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
class XmlResolver(
    private val xpath: MutableList<String>,
    private val attributes: Map<String, String>
) : XmlResolveSupplier<String> {

    constructor(
        signalXpath: String,
        attributes: Map<String, String>
    ) : this(
        xpath = mutableListOf(signalXpath),
        attributes = attributes
    )

    companion object {
        val EMPTY = XmlResolver(mutableListOf(), emptyMap())
    }

    override val supplierXpath: MutableList<String> = xpath
    override val supplierAttributes: Map<String, String> = attributes

    override fun saveToXml(root: Element, value: String) {
        if (xpath.isEmpty()) {
            GlobalData.LOGGER.info("XML Storage Miss Element Path")
            return
        }
        val targetElement = IOBus.ensureElement(root, xpath)
        targetElement.text = value
        attributes.forEach { (key, value) ->
            targetElement.addAttribute(key, value)
        }
    }


    override fun readFromXml(root: Element): XmlReadOut<String>? {
        val targetElement = navigateToElement(root) ?: return null
        return XmlReadOut(
            targetElement.text!!,
            mutableMapOf<String, String>().apply {
                targetElement.attributes().forEach {
                    plus(it.namespace to it.value)
                }
            },
            mutableMapOf()
        )
    }

    private fun navigateToElement(root: Element): Element? {
        var current = root
        for (nodeName in xpath) {
            current = current.element(nodeName) ?: return null
        }
        return current
    }
}