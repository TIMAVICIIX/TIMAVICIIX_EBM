package cn.timaviciix.ebm.client.rich_text.content_stream.interfaces

abstract class BlockInterpreter<T, E, X> {
    constructor(
        elementTagName: String,
        vararg formatInterpreter: FormatInterpreter<X>
    ) {
        this.elementTagName = elementTagName
        this.formatInterpreters = formatInterpreter.asList()
    }

    constructor(
        elementTagName: String,
        formatInterpreters: List<FormatInterpreter<X>>
    ) {
        this.elementTagName = elementTagName
        this.formatInterpreters = formatInterpreters
    }

    private val elementTagName: String
    protected val formatInterpreters: List<FormatInterpreter<X>>


    abstract fun interpret(originContent: T, resolvedValueTaker: (T) -> Unit): E

    fun getTagRegex() = Regex("<$elementTagName>(.*?)</$elementTagName>")
    fun getTag() = Pair("<$elementTagName>", "</$elementTagName>")

    fun cancelTag(segment:String):String{ return segment.replace(getTag().first,"").replace(getTag().second,"") }
}