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

import cn.timaviciix.ebm.util.CompressUtil.decompressString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDateTime

data class BookData(
    val bookId: String,
    val author: String,
    val createDate: String = LocalDateTime.toString(),
    val bookNbtType: BookNbtType,
    var copyPermission: Boolean,
    private var bytesNbtChunks: MutableList<ByteArray>,
    private val preloadPages: Int = 5,
    private var pageCacheTag: Pair<Int, Int> = Pair(1, 10),
    private var pageCache: MutableMap<Int, String> = mutableMapOf(),
    private var currentPage: Int = 1,
    var pageTag: Map<String, Int> = mapOf(),
) {

    /**
     * Normal Config Getter & Setter
     */
    val maxCharCount = bookNbtType.maxPage * bookNbtType.charsPerPage
    private val maxPage = bookNbtType.maxPage

    @Transient
    var currentContent: String = ""

    // Get Content Max Page Count When Author Save Book Content! Or Auto Save
    fun getContentMaxPage(): Int = bytesNbtChunks.size

    /**
     * Page Cache Functions
     */

    //@Imp:Outer Interface
    //Public Function
    fun getPageContent(pageNum: Int): String {
        setCurrentPage(pageNum)
        return pageCache[pageNum].orEmpty()
    }

    //@Imp: Init
    //Active When Player Opening Book
    init {
        initPageCache()
    }

    private fun initPageCache() {
        updatePageCacheTag()
        CoroutineScope(Dispatchers.IO).launch {
            loadPageCaches()
        }
    }

    private fun setCurrentPage(pageNum: Int) {
        currentPage = pageNum
        updatePageCacheTag()
        CoroutineScope(Dispatchers.IO).launch {
            loadPageCaches()
        }
    }

    // Update Pages Cache Tags [(currentPage)-5,currentPage,(currentPage+5)]
    private fun updatePageCacheTag(): Pair<Int, Int> {
        val minPageCache = if (currentPage < preloadPages) {
            1
        } else {
            currentPage - preloadPages
        }

        val maxPageCache = if (currentPage < maxPage) {
            currentPage + preloadPages
        } else {
            maxPage
        }

        pageCacheTag = Pair(minPageCache, maxPageCache)

        return pageCacheTag

    }

    //Clean Cache List & load New Content in
    private suspend fun loadPageCaches() = withContext(Dispatchers.IO) {
        //clean Dirt Content Method
        if (currentPage in pageCacheTag.first..pageCacheTag.second) {

            for (i in pageCache) {
                if (i.key < pageCacheTag.first || i.key > pageCacheTag.second) {
                    pageCache.remove(i.key)
                }
            }

        } else {
            pageCache.clear()
        }

        for (i in pageCacheTag.first..pageCacheTag.second) {
            pageCache[i] = bytesNbtChunks[i].decompressString()
        }
    }


    //@Imp: Save Bus

}
