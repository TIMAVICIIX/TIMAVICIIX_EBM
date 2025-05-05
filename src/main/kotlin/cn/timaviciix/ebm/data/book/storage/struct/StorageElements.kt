/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data.book.storage.struct
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-05  01:17
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data.book.storage.struct


// Loading data from stack if book has own stack data,else load from storage data
enum class StorageElements {
    NONE,
    BOOK_ID,
    BOOK_NAME,
    EDITOR_ID,
    EDITOR_NAME,
    AUTHOR_NAME,
    BOOK_NBT_TYPE,
    LAST_READING_PAGE
}
