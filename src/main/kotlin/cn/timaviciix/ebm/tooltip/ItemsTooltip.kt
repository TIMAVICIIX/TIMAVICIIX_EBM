/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.tooltip
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-17  12:47
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.tooltip

import cn.timaviciix.ebm.item.books.BookRegister
import cn.timaviciix.ebm.util.GlobalData
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object ItemsTooltip {

    fun initializeTooltip() {

        ItemTooltipCallback.EVENT.register { stack, context, lines ->
            BookRegister.apply {
                when(stack.item){

                    CLASSIC_JOURNAL_BOOK -> {
                        lines.apply {
                            add(Text.translatable(ToolTipBus.getTooltipPath(GlobalData.ItemId.CLASSIC_JOURNAL_BOOK)))
                        }
                    }

                }
            }


        }

    }

}