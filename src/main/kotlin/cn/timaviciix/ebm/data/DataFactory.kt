/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data.book
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-09  00:29
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data

import cn.timaviciix.ebm.data.book.BookData
import cn.timaviciix.ebm.data_io.data_configs.BookDataConfig
import cn.timaviciix.ebm.data_io.handler.NbtHandler
import cn.timaviciix.ebm.item.blockitems.BookBlockItem
import cn.timaviciix.ebm.util.GeneralUtil
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import java.time.LocalDateTime
import java.util.*

object DataFactory {

    fun getOrCreateBookData(
        stack: ItemStack,
        playerName: String = Text.translatable("data.timaviciix_ebm.author_name_unknown").string,
        playerUUID: UUID?,
        loadBaseData:Boolean = false,
        printInfo: Boolean = false,
    ): BookData {
        stack.orCreateNbt.apply {
            val bookId = getString(BookDataConfig.BOOK_UUID_NBT_ID).ifBlank { GeneralUtil.Uuid.generateShortUUID() }
            val bookName =
                getString(BookDataConfig.BOOK_NAME_NBT_ID).ifBlank { Text.translatable("data.timaviciix_ebm.book_name_unknown").string }
            val editor = getString(BookDataConfig.BOOK_EDITOR_NBT_ID).ifBlank { playerName }
            val editorId = getString(BookDataConfig.BOOK_EDITOR_ID_NBT_ID).ifBlank { GeneralUtil.Uuid.generateFullUUID().toString() }
            val author = getString(BookDataConfig.BOOK_AUTHOR_NBT_ID).ifBlank { editor }
            var lastReadingPage = getInt(BookDataConfig.BOOK_LAST_READING_PAGE_NBT_TAG)
            if (lastReadingPage == 0) {
                lastReadingPage = 1
            }

            val createDate = getString(BookDataConfig.BOOK_CREATE_DATE_NBT_ID).ifBlank {
                if (printInfo) {
                    GlobalData.LOGGER.info(
                        """
                    [Data Factory]Trying Create New Book Data Struct
                """.trimIndent()
                    )
                }
                LocalDateTime.now().toString()
            }
            val bookNbtType = (stack.item as BookBlockItem).bookNbtType

            val copyPermission = NbtHandler.loadCopyPermissionFromNbt(this,playerUUID)
            val contents = if(loadBaseData) mutableMapOf() else  NbtHandler.loadStringMapFromNbt(this, BookDataConfig.BOOK_CONTENT_NBT_ID)
            val pageTags = NbtHandler.loadPageTagsFromNbt(this, BookDataConfig.BOOK_PAGE_TAG_NBT_ID)

            if (printInfo) {

                GlobalData.LOGGER.info(
                    """
                [Data Factory]
                BOOK ID:$bookId
                BOOK NAME:$bookName
                Editor:$editor
                Editor UUID:$editorId
                Create Date:$createDate
                Book Nbt Type:${bookNbtType.name}
                Copy Permission:
                    stampingState:${copyPermission.stampingState}
                    isCopies:${copyPermission.isCopies}
                    copyPermission:${copyPermission.copyGrantees}
                Contents Size:${contents.size}
                Page Tags:$pageTags
                """.trimIndent()
                )
            }

            return BookData(
                bookId = bookId,
                bookName = bookName,
                editor = editor,
                editorId = editorId,
                author = author,
                createDate = createDate,
                bookNbtType = bookNbtType,
                copyPermission = copyPermission,
                contents = contents,
                pageTag = pageTags,
                lastReadingPage = lastReadingPage
            )
        }


    }

}