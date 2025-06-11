/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.rich_text.content_stream.interpreters
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-06-09  16:24
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.rich_text.content_stream.interpreters

import cn.timaviciix.ebm.client.rich_text.content_stream.interfaces.BlockInterpreter
import cn.timaviciix.ebm.client.rich_text.content_stream.interfaces.FieldBinder
import cn.timaviciix.ebm.client.rich_text.content_stream.interfaces.FieldBinder.Companion.applyFieldBindings

object CodeBlockInterpreter : BlockInterpreter<String, CodeBlockInterpreter.CodeBlockData>("codeBlock") {

    data class CodeBlockData(
        var codeType: String = "",
        var codeContent: String = ""
    )

    override fun resolveContent(segment: String): CodeBlockData {
        val codeBlock = CodeBlockData()
        applyFieldBindings(segment, codeBlock, listOf(
            FieldBinder(CodeBlockData::codeType) { it },
            FieldBinder(CodeBlockData::codeContent) { it }
        ))
        return codeBlock
    }

}