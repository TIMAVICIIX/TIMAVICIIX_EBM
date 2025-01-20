/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.copiers
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  01:04
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.copiers

import cn.timaviciix.ebm.block.copiers.PortableCopierBlock
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult

class PortableCopier : BaseCopier(0x3f72af, GlobalData.OWO_ITEM_SIGNAL_SETTING) {

    override fun useOnBlock(context: ItemUsageContext?): ActionResult {

        context?.let {

            val world = it.world
            it.player
            it.hand
            val pos = it.blockPos
            world.getBlockState(pos)
            if (!world.isClient) {

                val abovePos = pos.up()

                world.setBlockState(abovePos, PortableCopierBlock().stateManager.defaultState)

            }

        }

        return ActionResult.SUCCESS

    }

}