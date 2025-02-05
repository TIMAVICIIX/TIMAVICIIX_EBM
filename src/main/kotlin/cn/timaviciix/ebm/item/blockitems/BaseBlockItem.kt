/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-27  17:30
 *@Description: 基本BlockItem类
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.blockitems

import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.util.ActionResult
import org.slf4j.LoggerFactory

open class BaseBlockItem(
    block: Block,
    settings: Settings,
    nameColor: Int = 0xeeeeee,
    val itemClassify: BlockItemClassify = BlockItemClassify.Unknown
) :
    BlockItem(block, settings) {

    private val logger = LoggerFactory.getLogger(GlobalData.MOD_ID)

    companion object {

        //
        enum class BlockItemClassify {
            Unknown,
            Books,
            Worktables,
            Others,
            Copiers
        }
    }

    private val nameStyle: Style = Style.EMPTY.withColor(TextColor.fromRgb(nameColor))
    override fun getName(): Text {
        return super.getName().copy().setStyle(nameStyle)
    }

    override fun getName(stack: ItemStack?): Text {
        return super.getName(stack).copy().setStyle(nameStyle)
    }


}