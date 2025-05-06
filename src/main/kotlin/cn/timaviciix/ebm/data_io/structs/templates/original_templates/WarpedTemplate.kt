/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.templates
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  00:42
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.templates.original_templates

import cn.timaviciix.ebm.data_io.structs.components.TemplateDelegate
import cn.timaviciix.ebm.data_io.structs.components.XmlResolver

abstract class WarpedTemplate {
    private val _elements = mutableListOf<ElementTemplate<*>>()
    val elements: List<ElementTemplate<*>> get() = _elements

    fun <T> register(template: ElementTemplate<T>): ElementTemplate<T> {
        _elements.add(template)
        return template
    }

    fun <T> template(
        xpath: XmlResolver = XmlResolver.EMPTY,
        attributes: MutableMap<String, String> = mutableMapOf()
    ): TemplateDelegate<T> {
        return TemplateDelegate(xpath, attributes)
    }
}
