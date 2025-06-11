/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.rich_text.content_stream.interpreters
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-06-11  17:16
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.rich_text.content_stream.interpreters

import cn.timaviciix.ebm.client.rich_text.content_stream.interfaces.BlockInterpreter
import cn.timaviciix.ebm.client.rich_text.content_stream.interfaces.FieldBinder
import cn.timaviciix.ebm.client.rich_text.content_stream.interfaces.FieldBinder.Companion.applyFieldBindings
/**
 * 目录块解释器
 * 目录块仅支持最多三级目录
 * 目录自带目录名称
 * 每个目录项具有条录名称，条录页属性
 * 条录页属性将在渲染层中重注入
 * */
object CatalogBlockInterpreter :
    BlockInterpreter<String, CatalogBlockInterpreter.CatalogBlockData>("catalogBlock") {

    data class CatalogItem(
        var level: Int = 0,
        var title: String = "",
        var index: Int = 0,
        var children: List<CatalogItem> = listOf(),
        var operation: () -> Unit = {}
    )

    data class CatalogBlockData(
        var catalogTitle: String = "",
        var catalogList: List<CatalogItem> = listOf()
    )

    override fun resolveContent(segment: String): CatalogBlockData {
        val data = CatalogBlockData()

        applyFieldBindings(segment, data, listOf(
            FieldBinder(CatalogBlockData::catalogTitle) { it }
        ))

        val topItems = parseItemList(segment)
        data.catalogList = topItems

        return data
    }

    private fun parseItemList(segment: String): List<CatalogItem> {
        val itemRegex = Regex("<item>(.*?)</item>", RegexOption.DOT_MATCHES_ALL)
        return itemRegex.findAll(segment).map { match ->
            parseSingleItem(match.groupValues[1])
        }.toList()
    }

    private fun parseSingleItem(itemSegment: String): CatalogItem {
        val item = CatalogItem()

        applyFieldBindings(itemSegment, item, listOf(
            FieldBinder(CatalogItem::level) { it.toIntOrNull() ?: 0 },
            FieldBinder(CatalogItem::title) { it },
            FieldBinder(CatalogItem::index) { it.toIntOrNull() ?: -1 }
        ))

        // 提取并递归解析 <inlineItem> 中的子 <item>
        val inlineItemRegex = Regex("<inlineItem>(.*?)</inlineItem>", RegexOption.DOT_MATCHES_ALL)
        val inlineItemContent = inlineItemRegex.find(itemSegment)?.groupValues?.get(1)
        if (!inlineItemContent.isNullOrBlank()) {
            item.children = parseItemList(inlineItemContent)
        }

        return item
    }
}
