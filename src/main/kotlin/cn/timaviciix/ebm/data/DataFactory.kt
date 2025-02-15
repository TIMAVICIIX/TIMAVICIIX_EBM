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
import kotlinx.datetime.LocalDateTime
import net.minecraft.nbt.NbtCompound

object DataFactory {

    fun getOrCreateBookData(
        nbt: NbtCompound,
        playerName: String = "Unknown",
        printInfo: Boolean = false
    ): BookData {
        nbt.apply {
            val bookId = getString(BookDataConfig.BOOK_UUID_NBT_ID).ifBlank { GeneralUtil.Uuid.generateShortUUID() }
            val author = getString(BookDataConfig.BOOK_AUTHOR_NBT_ID).ifBlank { playerName }
            val createDate = getString(BookDataConfig.BOOK_CREATE_DATE_NBT_ID).ifBlank {
                GlobalData.LOGGER.info(
                    """
                    [Data Factory]Trying Create New Book Data Struct
                """.trimIndent()
                )
                LocalDateTime.toString()
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
            val bytesNbtChunks = NbtHandler.loadByteArrayFromNbt(this, BookDataConfig.BOOK_CONTENT_NBT_ID)

            if (printInfo) {
                GlobalData.LOGGER.info(
                    """
                [Data Factory]
                BOOK ID:$bookId
                Author:$author
                Create Date:$createDate
                Book Nbt Type:${bookNbtType.backgroundAndStorageType}
                Copy Permission:$copyPermission
                Byte Nbt Chunks Size${bytesNbtChunks.size}
                """.trimIndent()
                )
            }

            return BookData(
                bookId = bookId,
                author = author,
                createDate = createDate,
                bookNbtType = bookNbtType,
                copyPermission = copyPermission,
                bytesNbtChunks = bytesNbtChunks
            )


        }


    }

}