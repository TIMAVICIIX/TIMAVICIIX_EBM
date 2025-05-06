/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.components
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  01:08
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.components

import cn.timaviciix.ebm.data_io.structs.templates.original_templates.ElementTemplate
import cn.timaviciix.ebm.data_io.structs.templates.original_templates.WarpedTemplate
import kotlin.reflect.KProperty

class TemplateDelegate<T>(
    private val xpath: XmlResolver = XmlResolver.EMPTY,
    private val attributes: MutableMap<String, String> = mutableMapOf()
) {
    private lateinit var instance: ElementTemplate<T>

    operator fun getValue(thisRef: WarpedTemplate, property: KProperty<*>): ElementTemplate<T> {
        if (!::instance.isInitialized) {
            val id = property.name
            instance = ElementTemplate(id, xpath, attributes)
            thisRef.register(instance)
        }
        return instance
    }
}