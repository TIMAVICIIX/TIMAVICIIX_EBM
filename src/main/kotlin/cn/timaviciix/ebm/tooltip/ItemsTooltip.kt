/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.tooltip
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-17  12:47
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.tooltip

import cn.timaviciix.ebm.util.GlobalData
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.text.Text

object ItemsTooltip {

    val tooltipBlockBusList:MutableList<Pair<BlockItem,String>> = mutableListOf()
    val tooltipItemBusList:MutableList<Pair<Item,String>> = mutableListOf()

    fun initializeTooltip() {
        ItemTooltipCallback.EVENT.register { stack, _, lines ->


            for (item in tooltipBlockBusList) {
                if (stack.item == item.first) {
                    val originRequestPath = generateDescPath(true, item.second)
                    val translatedText = Text.translatable(originRequestPath)

                    if (originRequestPath != translatedText.string) {
                        lines.add(translatedText)
                    }

                }
            }

            for (item in tooltipItemBusList) {
                if (stack.item == item.first) {
                    val originRequestPath = generateDescPath(false, item.second)
                    val translatedText = Text.translatable(originRequestPath)

                    if (originRequestPath != translatedText.string) {
                        lines.add(translatedText)
                    }

                }
            }

        }

    }

    private fun generateDescPath(isBlock: Boolean, name: String): String {

        return if (isBlock) "block." + GlobalData.MOD_ID + "." + name + GlobalData.DESC_SUFFIX else "item." + GlobalData.MOD_ID + "." + name + GlobalData.DESC_SUFFIX

    }

}