/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.components.xml
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-09  16:23
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.components.xml

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class XmlResolverDelegate<T>(
    private val attributes: Map<String, String>,
    private val readSupplier: (inputValue: String?, default: T?) -> T?,
    private val saveSupplier: (outputValue: T) -> String
) : ReadOnlyProperty<Any?, XmlResolver<T>> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): XmlResolver<T> {
        return XmlResolver(property.name, attributes, readSupplier, saveSupplier)
    }
}