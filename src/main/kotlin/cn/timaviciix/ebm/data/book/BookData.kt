package cn.timaviciix.ebm.data.book

import cn.timaviciix.ebm.util.GeneralUtil
import kotlinx.datetime.LocalDateTime

data class BookData(
    val bookId: String = GeneralUtil.Uuid.generateShortUUID(),
    val author: String = "",
    val createDate: String = LocalDateTime.toString(),
    val bookBlockType: BookBlockType,
    val preloadPages: Int = 2,
    var copyPermission: Boolean = false,
    var bytesNbtChunks: MutableList<ByteArray> = mutableListOf(),
    var pageCacheTag: Pair<Int, Int> = Pair(1, 10),
    var pageCache: MutableList<Pair<Int, String>>,
    var currentPage: Int = 1,
    var pageTag: Int = 0,
) {

    val maxCharCount = bookBlockType.maxPage * bookBlockType.charsPerPage
    val maxPage = bookBlockType.maxPage

    @Transient
    var currentContent: String = ""

    // Get Content Max Page Count When Author Save Book Content! Or Auto Save
    fun getContentMaxPage(contentCharNum: Int): Int = if (contentCharNum % bookBlockType.charsPerPage == 0) {
        contentCharNum / bookBlockType.charsPerPage
    } else {
        (contentCharNum / bookBlockType.charsPerPage) + 1
    }


    // Get Nbt Parts Count
    fun getNbtSplitPartCount(contentCharNum: Int): Int = bytesNbtChunks.size


    // Update Pages Cache Tags [(currentPage)-5,currentPage,(currentPage+5)]
    fun updatePageCacheTag(currentPage: Int, pageCount: Int): Pair<Int, Int> {
        val minPageCache = if (currentPage < 5) {
            1
        } else {
            currentPage - 5
        }

        val maxPageCache = if (currentPage < maxPage) {
            currentPage + 5
        } else {
            maxPage
        }

        pageCacheTag = Pair(minPageCache, maxPageCache)

        return pageCacheTag

    }

}
