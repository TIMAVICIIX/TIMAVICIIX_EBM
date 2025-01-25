/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.tooltip
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-17  12:47
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.tooltip

import cn.timaviciix.ebm.registers.items.OtherItemRegister
import cn.timaviciix.ebm.util.GlobalData
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.minecraft.text.Text

object ItemsTooltip {

    fun initializeTooltip() {

        ItemTooltipCallback.EVENT.register { stack, _, lines ->

            when (stack.item) {

                OtherItemRegister.INVERTED_FEATHER_DUSTER -> {
                    lines.add(Text.translatable(generateDescPath("inverted_feather_duster")))
                }

            }

        }

    }

    private fun generateDescPath(name: String): String {

        return "item." + GlobalData.MOD_ID +"."+ name +  GlobalData.DESC_SUFFIX

    }

}