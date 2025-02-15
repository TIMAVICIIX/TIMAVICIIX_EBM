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

import cn.timaviciix.ebm.data.handler.NbtHandler
import cn.timaviciix.ebm.util.CompressUtil.decompressString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDateTime
import net.minecraft.item.ItemStack
import kotlin.properties.Delegates

data class BookData(
    val bookId: String,
    val createDate: String = LocalDateTime.toString(),
    val bookNbtType: BookNbtType,
    var author: String,
    var copyPermission: CopyPermission,
    private var bytesNbtChunks: MutableList<ByteArray>,
    private val preloadPages: Int = 5,
    private var pageCacheTag: Pair<Int, Int> = Pair(1, 10),
    private var pageCache: MutableMap<Int, String> = mutableMapOf(),
    var pageTag: Map<String, Int> = mapOf(),
) {

    /**
     * Normal Config Getter & Setter
     */
    @Transient
    var currentContent: String = ""
    var currentPage: Int by Delegates.observable(1) { _, _, newValue ->
        updatePageCacheTag()
        CoroutineScope(Dispatchers.IO).launch {
            loadPageCaches()
        }
        currentContent = pageCache[newValue].orEmpty()
    }
    val maxCharCount = bookNbtType.maxPage * bookNbtType.charsPerPage
    private val maxPage = bookNbtType.maxPage

    // Get Content Max Page Count When Author Save Book Content! Or Auto Save
    fun getContentMaxPage(): Int = bytesNbtChunks.size

    /**
     * Page Cache Functions
     */

    //@Imp: Init
    //Active When Player Opening Book
    init {
        initPageCache()
    }

    private fun initPageCache() {
        updatePageCacheTag()
        loadPageCaches()

        currentContent = pageCache[currentPage].orEmpty()
    }

    // Update Pages Cache Tags [(currentPage)-5,currentPage,(currentPage+5)]
    private fun updatePageCacheTag(): Pair<Int, Int> {
        val minPageCache = if (currentPage < preloadPages) {
            1
        } else {
            currentPage - preloadPages
        }

        val maxPageCache = if (currentPage < maxPage) {
            val targetPageCache = currentPage + preloadPages
            if (bytesNbtChunks.size + 1 < targetPageCache) {
                bytesNbtChunks.size + 1
            } else {
                targetPageCache
            }
        } else {
            maxPage
        }

        pageCacheTag = Pair(minPageCache, maxPageCache)

        return pageCacheTag

    }

    //Clean Cache List & load New Content in
    private fun loadPageCaches() {
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
            if (i > bytesNbtChunks.size) {
                pageCache[i] = ""
            } else {
                pageCache[i] = bytesNbtChunks[i].decompressString()
            }
        }

    }


    //@Imp: Save Bus
    fun save(itemStack: ItemStack) {

        itemStack.orCreateNbt.let { nbt ->

            NbtHandler.apply {
                bookId.saveToNbt(
                    nbt,
                    BookDataConfig.BOOK_UUID_NBT_ID
                )
                author.saveToNbt(
                    nbt,
                    BookDataConfig.BOOK_AUTHOR_NBT_ID
                )
                createDate.saveToNbt(
                    nbt,
                    BookDataConfig.BOOK_CREATE_DATE_NBT_ID
                )
                bookNbtType.backgroundAndStorageType.saveToNbt(
                    nbt,
                    BookDataConfig.BOOK_ITEM_TYPE_NBT_ID
                )
                copyPermission.saveToNbt(nbt)
                saveToNbt(
                    nbt,
                    bytesNbtChunks,
                    BookDataConfig.BOOK_CONTENT_NBT_ID,
                    BookDataConfig.BOOK_SIZE_NBT_ID
                )

            }
        }
    }

}
