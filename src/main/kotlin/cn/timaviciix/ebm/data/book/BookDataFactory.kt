/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data.book
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-09  00:29
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data.book

import cn.timaviciix.ebm.data.NbtHandler
import cn.timaviciix.ebm.util.GeneralUtil
import kotlinx.datetime.LocalDateTime
import net.minecraft.item.ItemStack

object BookDataFactory {

    fun getOrCreateBookData(stack: ItemStack, shortUUID: String): BookData {
        stack.orCreateNbt.apply {
            val bookId = getString(BookDataConfig.BOOK_UUID_NBT_ID).ifBlank { GeneralUtil.Uuid.generateShortUUID() }
            val author = getString(BookDataConfig.BOOK_AUTHOR_NBT_ID).ifBlank { "Unknown" }
            val createDate = getString(BookDataConfig.BOOK_CREATE_DATE_NBT_ID).ifBlank { LocalDateTime.toString() }
            val copyPermission = getBoolean(BookDataConfig.BOOK_COPY_PERMISSION_NBT_ID)
            val bookNbtType = when (getInt(BookDataConfig.BOOK_BLOCK_TYPE_NBT_ID)) {
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

            val bytesNbtChunks = NbtHandler.loadByteArrayFromNbt(this, BookDataConfig.BOOK_CONTENT_NBT_ID)

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