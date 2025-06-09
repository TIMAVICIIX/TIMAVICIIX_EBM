/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.rich_text.content_stream.interpreters
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-06-08  23:53
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.rich_text.content_stream.interpreters

import cn.timaviciix.ebm.client.rich_text.content_stream.interfaces.BlockInterpreter

object TextInterpreter : BlockInterpreter<String, TextInterpreter.TextData>("text") {

    data class TextData(
        val textContent: String,
        val boldPositions: List<Int>,
        val italicPositions: List<Int>,
        val underLinePositions: List<Int>
    )

    override fun resolveContent(segment: String): TextData {
        val (plain, bold, italic, underline) = parseFormatTags(segment)
        return TextData(plain, bold.toList(), italic.toList(), underline.toList())
    }

    private fun parseFormatTags(content: String): Quad<String, Set<Int>, Set<Int>, Set<Int>> {
        val boldRegex = Regex("<bold>(.*?)</bold>")
        val italicRegex = Regex("<italic>(.*?)</italic>")
        val underlineRegex = Regex("<underline>(.*?)</underline>")

        val formatRanges = mutableListOf<Triple<Regex, String, MutableSet<Int>>>()
        formatRanges += Triple(boldRegex, "bold", mutableSetOf())
        formatRanges += Triple(italicRegex, "italic", mutableSetOf())
        formatRanges += Triple(underlineRegex, "underline", mutableSetOf())

        val cleanBuilder = StringBuilder()
        var indexOffset = 0

        fun processRecursive(input: String, globalOffset: Int): String {
            var localInput = input
            formatRanges.forEach { (regex, _, indexSet) ->
                regex.findAll(localInput).forEach { match ->
                    val innerStart = match.range.first
                    val innerEnd = match.range.last + 1

                    val rawInner = match.groupValues[1]
                    val parsed =
                        processRecursive(rawInner, globalOffset + cleanBuilder.length + innerStart - indexOffset)

                    val start = cleanBuilder.length
                    cleanBuilder.append(parsed)
                    val end = cleanBuilder.length
                    indexSet += (start until end)

                    indexOffset += innerEnd - innerStart - parsed.length
                    localInput = localInput.replaceRange(match.range, parsed)
                }
            }
            return localInput
        }

        val stripped = processRecursive(content, 0)
        cleanBuilder.clear()
        cleanBuilder.append(stripped)

        return Quad(
            cleanBuilder.toString(),
            formatRanges[0].third,
            formatRanges[1].third,
            formatRanges[2].third
        )
    }

    data class Quad<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)
}
