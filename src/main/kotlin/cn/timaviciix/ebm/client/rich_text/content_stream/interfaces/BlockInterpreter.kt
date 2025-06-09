package cn.timaviciix.ebm.client.rich_text.content_stream.interfaces

import kotlin.reflect.KMutableProperty1

/**
 *@param T 源解释类型
 *@param E 解释输出类型
 * */
abstract class BlockInterpreter<T, E>(private val elementTagName: String) {

    abstract fun resolveContent(segment: String): E

    open fun interpret(originContent: T, resolvedValueTaker: (String) -> Unit): List<Pair<IntRange, E>> {
        val tagRegex = getTagRegex()
        var resolvedString = originContent.toString()
        val resultList = mutableListOf<Pair<IntRange, E>>()

        tagRegex.findAll(originContent.toString()).forEach { match ->
            val segment = cancelTag(match.value)
            val parsed = resolveContent(segment)
            resultList += (match.range to parsed)
            resolvedString = resolvedString.replaceRange(match.range, "")
        }

        resolvedValueTaker(resolvedString)
        return resultList
    }

    fun getTagRegex(): Regex = Regex("<$elementTagName>(.*?)</$elementTagName>")
    fun getTag(): Pair<String, String> = "<$elementTagName>" to "</$elementTagName>"
    fun cancelTag(segment: String): String = segment.removePrefix(getTag().first).removeSuffix(getTag().second)
}
