package cn.timaviciix.ebm.data.book

import net.minecraft.nbt.NbtCompound
import java.util.*

data class BookData(
    val bookId: UUID,
    val author:String,
    val createDate:String,
    val bookBlockType: BookBlockType,
    val preloadPages:Int = 2,
    var copyPermission:Boolean,
    var bytesNbtChunks:MutableList<ByteArray>
){

    @Transient
    var currentContent:String = ""


}
