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
import cn.timaviciix.ebm.data.book.BookDataConfig
import cn.timaviciix.ebm.data.book.BookNbtType
import cn.timaviciix.ebm.data.handler.NbtHandler
import cn.timaviciix.ebm.util.GeneralUtil
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.Text
import java.time.LocalDateTime

object DataFactory {

    fun getOrCreateBookData(
        nbt: NbtCompound,
        playerName: String = Text.translatable("data.timaviciix_ebm.author_name_unknown").string,
        printInfo: Boolean = false,
    ): BookData {
        nbt.apply {
            val bookId = getString(BookDataConfig.BOOK_UUID_NBT_ID).ifBlank { GeneralUtil.Uuid.generateShortUUID() }
            val bookName =
                getString(BookDataConfig.BOOK_NAME_NBT_ID).ifBlank { Text.translatable("data.timaviciix_ebm.book_name_unknown").string }
            val author = getString(BookDataConfig.BOOK_AUTHOR_NBT_ID).ifBlank { playerName }
            var lastReadingPage = getInt(BookDataConfig.BOOK_LAST_READING_PAGE_NBT_TAG)
            if (lastReadingPage == 0) {
                lastReadingPage = 1
            }

            val createDate = getString(BookDataConfig.BOOK_CREATE_DATE_NBT_ID).ifBlank {
                GlobalData.LOGGER.info(
                    """
                    [Data Factory]Trying Create New Book Data Struct
                """.trimIndent()
                )
                LocalDateTime.now().toString()
            }
            val bookNbtType = when (getInt(BookDataConfig.BOOK_ITEM_TYPE_NBT_ID)) {
                0 -> {
                    BookNbtType.JournalBook
                }

                1 -> {
                    BookNbtType.GeneralBook
                }

                2 -> {
                    BookNbtType.LightBook
                }

                3 -> {
                    BookNbtType.LargeBook
                }

                else -> {
                    BookNbtType.GeneralBook
                }
            }

            val copyPermission = NbtHandler.loadCopyPermissionFromNbt(this)
            val contents = NbtHandler.loadStringMapFromNbt(this, BookDataConfig.BOOK_CONTENT_NBT_ID)
            val pageTags = NbtHandler.loadPageTagsFromNbt(this, BookDataConfig.BOOK_PAGE_TAG_NBT_ID)

            if (printInfo) {
                GlobalData.LOGGER.info(
                    """
                [Data Factory]
                BOOK ID:$bookId
                BOOK NAME:$bookName
                Author:$author
                Create Date:$createDate
                Book Nbt Type:${bookNbtType.backgroundAndStorageType}
                Copy Permission:$copyPermission
                Contents Size:${contents.size}
                Page Tags:$pageTags
                """.trimIndent()
                )
            }

            return BookData(
                bookId = bookId,
                bookName = bookName,
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