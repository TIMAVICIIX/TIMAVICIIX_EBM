/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data.book
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-08 00:49
 *@Description: 存储了书本中的各种数据，其中可变项包括复制权限、比特流内容压缩包、内容缓存标识，内容缓存列表、内容字符数、最大内容页数、当前内容、当前页面、书签(书签采用最少使用淘汰LRU)
 * 保存操作触发的变量更改有：比特流内容压缩包、内容字符数、最大内容页数、当前内容 翻页操作触发的变量更改有：内容缓存标识，内容缓存列表
 *@Version: 1.0
 */
package cn.timaviciix.ebm.data.book

import cn.timaviciix.ebm.data.handler.NbtHandler.saveToNbt
import net.minecraft.item.ItemStack

open class BookData(
    val bookId: String,
    val createDate: String,
    val bookNbtType: BookNbtType,
    var bookName: String,
    var editor: String,
    var editorId: String,
    var author: String,
    var copyPermission: CopyPermission,
    var pageTag: Map<String, Int>,
    var lastReadingPage: Int,
    var contents: MutableMap<Int, String>,
) {

    val maxCharCount = bookNbtType.maxPage * bookNbtType.charsPerPage
    private val maxPage = bookNbtType.maxPage

    fun getContentMaxPage(): Int = contents.size
    fun getLastReadingContent(): Pair<Int, String> {
        if (contents.size > lastReadingPage) {
            for (i in contents.toList().reversed()) {
                if (i.second.isNotEmpty()) return i
            }
            return Pair(1, "")
        } else if (contents.isEmpty()) {
            return Pair(1, "")
        } else {
            return contents.toList()[contents.size - 1]
        }
    }


    companion object {
        fun BookData.save(stack: ItemStack) {

            stack.orCreateNbt.let {
                BookDataConfig.apply {
                    this@save.bookId.saveToNbt(it, BOOK_UUID_NBT_ID)
                    this@save.bookName.saveToNbt(it, BOOK_NAME_NBT_ID)
                    this@save.editorId.saveToNbt(it, BOOK_EDITOR_ID_NBT_ID)
                    this@save.editor.saveToNbt(it, BOOK_EDITOR_NBT_ID)
                    this@save.author.saveToNbt(it, BOOK_AUTHOR_NBT_ID)
                    this@save.createDate.saveToNbt(it, BOOK_CREATE_DATE_NBT_ID)
                    this@save.contents.values.toMutableList().saveToNbt(it, BOOK_CONTENT_NBT_ID)
                    this@save.copyPermission.saveToNbt(it)
                    this@save.pageTag.saveToNbt(it, BOOK_PAGE_TAG_NBT_ID)
                }
            }

        }

    }

}
