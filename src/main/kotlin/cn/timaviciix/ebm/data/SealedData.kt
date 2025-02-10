/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-10  18:05
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data

import cn.timaviciix.ebm.data.book.BookData

sealed class SealedData<T>(val data: T) {
    class BookDataComponent(data: BookData) : SealedData<BookData>(data)
}