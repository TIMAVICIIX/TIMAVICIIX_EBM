/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.worktables
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-27  16:20
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.worktables

import cn.timaviciix.ebm.block.BaseDirectBlock
import cn.timaviciix.ebm.block.blockentitys.worktables.StampingDeskBlockEntity
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.DyeColor
import net.minecraft.util.math.BlockPos

class StampingDeskBlock : BaseDirectBlock(GlobalData.getGeneralBlockSetting(DyeColor.CYAN)) {

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
        return if (pos != null && state != null) {
            StampingDeskBlockEntity(pos, state)
        } else {
            null
        }
    }

}