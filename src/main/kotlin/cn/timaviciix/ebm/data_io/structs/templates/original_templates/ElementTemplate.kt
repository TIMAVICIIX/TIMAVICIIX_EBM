/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  00:12
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.templates.original_templates

import cn.timaviciix.ebm.data_io.structs.components.TypeToken
import cn.timaviciix.ebm.data_io.structs.components.nbt.NbtResolver
import cn.timaviciix.ebm.data_io.structs.components.xml.XmlResolver

class ElementTemplate<T>(
    val id: String,
    val xmlResolver: XmlResolver,
    val nbtResolver: NbtResolver<T>,
    private val defaultValue: T?,
    val typeToken: TypeToken<T>,
    val attributes: MutableMap<String, String> = mutableMapOf()
) {
    private var elementValue: T? = null

    fun injectValue(targetValue: T) {
        elementValue = targetValue
    }

    fun safetyGetValue(): T? = elementValue ?: defaultValue

    fun valueToString(): String = elementValue.toString()


}