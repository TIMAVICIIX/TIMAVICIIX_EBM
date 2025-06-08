/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.rich_text.content_stream.interfaces
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-06-07  23:10
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.rich_text.content_stream.interfaces

class FormatInterpreter<T>(
    val formatTagName: String,
    val formatOperation: (T) -> T
) {

    fun getRegex() = Regex("<$formatTagName>(.*?)</$formatTagName>", RegexOption.DOT_MATCHES_ALL)

    private fun getTagName() = Pair("<$formatTagName>", "</$formatTagName>")
    fun cancelTag(segment: String): String {
        return segment.replace(getTagName().first, "").replace(getTagName().second, "")
    }
}