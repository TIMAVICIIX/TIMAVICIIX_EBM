/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.rich_text.content_stream.mamage
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-06-07  23:25
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.rich_text.content_stream.interpreters

import cn.timaviciix.ebm.client.rich_text.content_stream.interfaces.BlockInterpreter

object BlockInterpreters {
    val all: MutableList<BlockInterpreter<*, *>> = mutableListOf<BlockInterpreter<*, *>>().apply {
        add(TextInterpreter)
        add(ImageInterpreter)
        add(CodeLineInterpreter)
    }
}


