/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data.handler
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-04-28  22:08
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.handler

import cn.timaviciix.ebm.data.DataFactory
import cn.timaviciix.ebm.data.book.BookData.Companion.save
import cn.timaviciix.ebm.data_io.data_configs.BookTooltipConfig
import cn.timaviciix.ebm.item.blockitems.BookBlockItem
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.text.Text

object BookNameHandler {
    fun renameItem(stack: ItemStack, player: PlayerEntity) {
        DataFactory.getOrCreateBookData(stack, player.name.string, player.uuid).apply {
            GlobalData.LOGGER.info("Rename Book!")
            val bookPrefixName =
                Text.translatable(BookTooltipConfig.DISPLAY_PREFIX_KEY + (stack.item as BookBlockItem).typeCode).string
            stack.setCustomName(Text.literal("[$bookPrefixName]-${bookName}").setStyle(stack.name.style))
            save(stack)
        }
    }

}