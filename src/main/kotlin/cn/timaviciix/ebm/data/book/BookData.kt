package cn.timaviciix.ebm.data.book

import net.minecraft.nbt.NbtCompound
import java.util.*

data class BookData(
    val bookId: UUID,
    val author:String,
    val createDate:String,
    val bookBlockType: BookBlockType,
    var copyPermission:Boolean,
    var nbtContent:MutableList<NbtCompound>,
    var currentContent:String
)
