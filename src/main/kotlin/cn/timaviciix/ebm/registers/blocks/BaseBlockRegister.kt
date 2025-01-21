/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  01:09
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks

import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.util.Rarity


interface BaseBlockRegister {

    companion object {

        fun generateBlockItem(block: Block, operation: (BlockItem.() -> Unit)?): BlockItem {

            val targetBlockItem = BlockItem(
                block,
                GlobalData.OWO_ITEM_SIGNAL_SETTING.rarity(Rarity.EPIC)
            )

            operation?.let {
                targetBlockItem.operation()
            }

            return targetBlockItem
        }

    }


}