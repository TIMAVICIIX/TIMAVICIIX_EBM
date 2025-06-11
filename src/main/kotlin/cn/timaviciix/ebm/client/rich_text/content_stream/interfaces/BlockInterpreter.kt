package cn.timaviciix.ebm.client.rich_text.content_stream.interfaces

/**
 *@param T 源解释类型
 *@param E 解释输出类型
 * */
abstract class BlockInterpreter<T, E>(private val elementTagName: String) {

    abstract fun resolveContent(segment: String): E

    fun interpret(originContent: T, resolvedValueTaker: (String) -> Unit): List<Pair<IntRange, E>> {
        var resolvedString = originContent.toString()
        val resultList = mutableListOf<Pair<IntRange, E>>()

        regex.findAll(originContent.toString()).forEach { match ->
            val segment = cancelTag(match.value)
            val parsed = resolveContent(segment)
            resultList += (match.range to parsed)
            resolvedString = resolvedString.replaceRange(match.range, "")
        }

        resolvedValueTaker(resolvedString)
        return resultList
    }

    val regex = Regex("<$elementTagName>(.*?)</$elementTagName>")
    val tag = "<$elementTagName>" to "</$elementTagName>"
    fun cancelTag(segment: String): String = segment.removePrefix(tag.first).removeSuffix(tag.second)
}
