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

class XmlResolverDelegate(private val attributes:Map<String,String>) : ReadOnlyProperty<Any?, XmlResolver> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): XmlResolver {
        return XmlResolver(property.name,attributes)
    }
}