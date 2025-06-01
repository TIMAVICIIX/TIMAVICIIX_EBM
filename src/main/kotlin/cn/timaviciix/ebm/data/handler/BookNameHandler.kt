/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data.handler
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-04-28  22:08
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data.handler

import cn.timaviciix.ebm.data.data_configs.BookTooltipConfig
import cn.timaviciix.ebm.item.blockitems.BookBlockItem
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.item.ItemStack
import net.minecraft.text.Text

object BookNameHandler {
    fun renameItem(stack: ItemStack) {
        (stack.item as? BookBlockItem)?.let {
            it.warpedBookData.bookId.readFromNbt(stack.orCreateNbt)
            it.warpedBookData.bookName.readFromNbt(stack.orCreateNbt)
            val bookName = if (it.warpedBookData.bookName.riskyGetValue().isNullOrEmpty()){
                Text.translatable("data.timaviciix_ebm.book_name_unknown").string
            }else{
                it.warpedBookData.bookName.riskyGetValue()
            }
            val bookId = it.warpedBookData.bookId.riskyGetValue()
            if (bookId != null) {
                val bookPrefixName =
                    Text.translatable(BookTooltipConfig.DISPLAY_PREFIX_KEY + it.typeCode).string
                stack.setCustomName(
                    Text.literal("[$bookPrefixName]-${bookName}").setStyle(stack.name.style)
                )
            }
        }
    }

}