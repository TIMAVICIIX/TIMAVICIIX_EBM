package cn.timaviciix.ebm.client.rich_text.content_stream.interfaces

import cn.timaviciix.ebm.client.rich_text.content_stream.structs.InterpreterRank

interface ContentInterpreter<T> {

    val elementTag:String
    val elementRank:InterpreterRank

    val interpretedContent:T?

}