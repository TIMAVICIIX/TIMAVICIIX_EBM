/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.rich_text.content_stream.mamage
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-06-08  00:15
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.rich_text.content_stream.interpreters

import cn.timaviciix.ebm.client.rich_text.content_stream.interfaces.FormatInterpreter
import net.minecraft.text.Text

object FormatInterpreters {
    val textFormat: List<FormatInterpreter<Text>> = listOf(
        FormatInterpreter("bold") { it.copy().styled { s -> s.withBold(true) } },
        FormatInterpreter("italic") { it.copy().styled { s -> s.withItalic(true) } },
        FormatInterpreter("underline") { it.copy().styled { s -> s.withUnderline(true) } },
    )
}
