package cn.timaviciix.ebm.data.book.storage.struct

import cn.timaviciix.ebm.data.book.BookData

class StorageStruct private constructor(
    val basicInfos: MutableMap<StorageElements, String>,
    val contents: MutableMap<ContentElements, Pair<Int, String>>
) {
    companion object {

        fun buildStorageStruct(originalData: BookData): StorageStruct {
            val basicInfosMap = mutableMapOf<StorageElements, String>().apply {

                put(StorageElements.BOOK_ID, originalData.bookId)
                put(StorageElements.BOOK_NAME, originalData.bookName)
                put(StorageElements.EDITOR_ID, originalData.editorId)
                put(StorageElements.EDITOR_NAME, originalData.editor)
                put(StorageElements.AUTHOR_NAME, originalData.author)
                put(StorageElements.BOOK_NBT_TYPE, originalData.bookNbtType.typeCode.toString())
                put(StorageElements.LAST_READING_PAGE, originalData.lastReadingPage.toString())

            }

            val contentsMap = mutableMapOf<ContentElements, Pair<Int, String>>().apply {
                originalData.contents.forEach { (key, value) ->
                    put(ContentElements.PAGE, key to value)
                }
            }

            return StorageStruct(basicInfosMap, contentsMap)

        }

    }

}




