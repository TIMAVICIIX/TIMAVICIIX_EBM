/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.rich_text.content_stream.interpreters
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-06-09  16:23
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.rich_text.content_stream.interpreters

import cn.timaviciix.ebm.client.rich_text.content_stream.interfaces.BlockInterpreter
import cn.timaviciix.ebm.client.rich_text.content_stream.interfaces.FieldBinder

object CodeLineInterpreter :
    BlockInterpreter<String, CodeLineInterpreter.CodeLineData>("codeLine") {

    data class CodeLineData(
        var lineContent: String = "",
        var type: String = ""
    )

    override fun resolveContent(segment: String): CodeLineData {
        val data = CodeLineData()
        FieldBinder.applyFieldBindings(segment, data, listOf(
            FieldBinder(CodeLineData::lineContent) { it },
            FieldBinder(CodeLineData::type) { it }
        ))
        return data
    }


}