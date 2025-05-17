/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.components.xml
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-16  01:21
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.components.xml

import org.dom4j.Element

interface XmlResolveSupplier<T> {
    val supplierXpath: MutableList<String>
    val supplierAttributes: Map<String, String>

    fun saveToXml(root: Element, value: T)
    fun readFromXml(root: Element): XmlReadOut<T>?
}