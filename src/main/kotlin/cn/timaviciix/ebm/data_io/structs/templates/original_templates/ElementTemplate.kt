/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  00:12
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.templates.original_templates

import cn.timaviciix.ebm.data_io.structs.components.NbtResolver
import cn.timaviciix.ebm.data_io.structs.components.XmlResolver

class ElementTemplate<T>(
    val id: String,
    val xmlResolver: XmlResolver,
    val nbtResolver: NbtResolver,
    val attributes: MutableMap<String, String> = mutableMapOf()
) {
    var elementValue: T? = null
        private set

    fun injectValue(targetValue: T) {
        elementValue = targetValue
    }

    fun valueToString(): String = elementValue.toString()

}