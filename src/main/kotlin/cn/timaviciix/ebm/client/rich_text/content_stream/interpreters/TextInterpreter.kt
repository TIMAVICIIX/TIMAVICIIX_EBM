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
import net.minecraft.text.MutableText
import net.minecraft.text.Text

object TextInterpreter :
    BlockInterpreter<String, List<Pair<IntRange, MutableText>>, Text>("text", FormatInterpreters.textFormat) {

    /**
     * @param originContent 压缩源文本
     * @param resolvedValueTaker 出口文本携带器，此函数将抛出处理后的源文本
     * @return 返回Text块列表，first为块再源文本中范围索引，second为块本身
     *
     * 1.得到文本后先获得text闭包的源文本段,并删除源文本段中该段
     * 2.在每个源text段中获取具有格式声明的文本段，通过格式解释器进行迭代解释并删除删除格式声明
     * 3.获得text中具有格式声明的Text列表并记录范围
     * 4.最后处理不带有格式的字符串，并组装为列表记录范围
     * 5.最后将两个列表交叉拼合，顺序与源text段中内容不变
     * */
    override fun interpret(
        originContent: String,
        resolvedValueTaker: (String) -> Unit
    ): List<Pair<IntRange, MutableText>> {

        val tagRegex = getTagRegex()

        var resolvedString = originContent
        val resultList: MutableList<Pair<IntRange, MutableText>> = mutableListOf()

        tagRegex.findAll(originContent).forEach {

            //1.源字符串中删除段，建立文本列表
            resolvedString = resolvedString.replaceRange(it.range, "")

            //2.段内删除段tag
            val textSegment = cancelTag(it.value)

            // 3. 调用递归格式处理器
            val resolvedText = applyFormatInterpreters(
                textSegment,
                baseBuilder = { str -> Text.literal(str) }
            )

            // 4. 保存结果，范围仍然是原始字符串中的 range
            resultList += (it.range to resolvedText)

        }

        // 5. 把删除后的字符串抛出（例如用于下一个处理器链）
        resolvedValueTaker(resolvedString)

        return resultList
    }

    private fun applyFormatInterpreters(
        content: String,
        baseBuilder: (String) -> MutableText,
    ): MutableText {
        for (formatter in formatInterpreters) {
            val regex = formatter.getRegex()
            if (regex.containsMatchIn(content)) {
                val output = mutableListOf<MutableText>()
                var lastIndex = 0

                regex.findAll(content).forEach { match ->
                    // 处理前段未匹配部分
                    if (match.range.first > lastIndex) {
                        val prefix = content.substring(lastIndex, match.range.first)
                        output += baseBuilder(prefix)
                    }

                    // 递归处理中间部分
                    val inner = applyFormatInterpreters(
                        match.groupValues[1],
                        baseBuilder
                    )

                    // 应用格式操作
                    val styled = formatter.formatOperation(inner as Text)
                    output += styled as MutableText

                    lastIndex = match.range.last + 1
                }

                // 处理最后的尾部
                if (lastIndex < content.length) {
                    output += baseBuilder(content.substring(lastIndex))
                }

                // 拼接所有子段
                return output.fold(Text.literal("")) { acc, t -> acc.append(t) }
            }
        }

        // 没有格式匹配，返回纯文本
        return baseBuilder(content)
    }
}