/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.components
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  00:25
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.components.xml

import cn.timaviciix.ebm.util.GlobalData
import org.dom4j.Document
import org.dom4j.Element

//XmlResolver是元数据类组件，判断元素是否需要XML存储，提供一个EMPTY预制类，不需要XML存储时直接入参EMPTY，需要时则入参其存储的xpath
class XmlResolver(private val xpath: MutableList<String>, private val attributes: Map<String, String>) {
    constructor(
        signalXpath: String,
        attributes: Map<String, String>
    ) : this(xpath = mutableListOf<String>().also { it.add(signalXpath) }, attributes = attributes)

    companion object {
        val EMPTY = XmlResolver(mutableListOf(), mapOf())
        val DEFAULT_ROOT by XmlResolverDelegate(mapOf())
    }

    fun saveToXml(document: Document, value: String): Document {
        document.let {
            if (xpath.isNotEmpty()) {
                var targetDom: Element? = null
                xpath.forEach { path ->
                    targetDom = targetDom?.addElement(path) ?: document.addElement(path)
                }
                targetDom!!.text = value
                attributes.forEach { (key, value) ->
                    @Suppress("DEPRECATION")
                    targetDom!!.setAttributeValue(key, value)
                }
            } else {
                GlobalData.LOGGER.info("XML Storage Miss Element:${xpath.last()}")
            }
        }
        return document
    }

}