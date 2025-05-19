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
class XmlResolver<T>(
    private val xpath: MutableList<String>,
    private val attributes: Map<String, String>,
    private val readSupplier: (inputValue: String?, default: T?) -> T? = { inputValue, default ->
        try {
            @Suppress("UNCHECKED_CAST")
            inputValue as? T
        } catch (e: Exception) {
            GlobalData.LOGGER.error("Can't cast this value!")
            default ?: throw TypeCastException()
        }
    },
    private val saveSupplier: (outputValue: T) -> String = {
        try {
            it.toString()
        } catch (e: Exception) {
            GlobalData.LOGGER.error("Can't cast this value!")
            throw TypeCastException()
        }
    }
) : XmlResolveSupplier<T> {

    constructor(
        signalXpath: String,
        attributes: Map<String, String>,
        readSupplier: (inputValue: String?, default: T?) -> T?,
        saveSupplier: (outputValue: T) -> String
    ) : this(
        xpath = mutableListOf(signalXpath),
        attributes = attributes,
        readSupplier = readSupplier,
        saveSupplier = saveSupplier
    )

    companion object {
        val EMPTY = XmlResolver<String>(mutableListOf(), emptyMap(), { _, default -> default }, { it })
    }

    override val supplierXpath: MutableList<String> = xpath
    override val supplierAttributes: Map<String, String> = attributes

    override fun saveToXml(root: Element, value: T) {
        if (xpath.isEmpty()) {
            GlobalData.LOGGER.info("XML Storage Miss Element Path")
            return
        }
        val targetElement = IOBus.ensureElement(root, xpath)
        targetElement.text = saveSupplier(value)
        attributes.forEach { (key, value) ->
            targetElement.addAttribute(key, value)
        }
    }


    override fun readFromXml(root: Element, default: T?): XmlReadOut<T>? {
        val targetElement = navigateToElement(root) ?: return null
        return XmlReadOut(
            readSupplier(targetElement.text, default),
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