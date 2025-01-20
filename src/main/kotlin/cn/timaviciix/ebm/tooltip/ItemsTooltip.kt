/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.tooltip
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-17  12:47
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.tooltip

import cn.timaviciix.ebm.registers.items.BookRegister
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback

object ItemsTooltip {

    fun initializeTooltip() {

        ItemTooltipCallback.EVENT.register { stack, context, lines ->
            BookRegister.apply {
                when (stack.item) {
                }
            }


        }

    }

}