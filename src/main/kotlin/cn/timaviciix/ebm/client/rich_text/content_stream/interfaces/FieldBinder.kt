/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.rich_text.content_stream.interfaces
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-06-09  16:03
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.rich_text.content_stream.interfaces

import kotlin.reflect.KMutableProperty1

data class FieldBinder<T, F>(
    val property: KMutableProperty1<T, F>,
    val tagName: String = property.name,
    val innerBinder: List<FieldBinder<*, *>>? = null, // 可选嵌套解析
    val customResolver: ((String, T) -> Unit)? = null, // 完全自定义解析方式
    val parser: (String) -> F,
) {
    val regex = Regex("<$tagName>(.*?)</$tagName>")

    fun cancelTag(content: String) = content.replace("<$tagName>", "").replace("</$tagName>", "")

    fun apply(segment: String, instance: T) {
        if (customResolver != null) {
            customResolver.invoke(segment, instance)
            return
        }

        var match = regex.find(segment)?.groupValues?.get(1) ?: return
        match = cancelTag(match)
        val value = parser(match)
        property.set(instance, value)
    }

    companion object {
        fun <T : Any> applyFieldBindings(segment: String, instance: T, bindings: List<FieldBinder<T, *>>) {
            bindings.forEach { binder ->
                @Suppress("UNCHECKED_CAST")
                (binder as FieldBinder<T, Any?>).apply(segment, instance)
            }
        }
    }
}


