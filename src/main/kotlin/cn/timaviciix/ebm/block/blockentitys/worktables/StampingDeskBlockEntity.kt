/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.blockentitys.worktables
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-27  16:21
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.blockentitys.worktables

import cn.timaviciix.ebm.registers.blocks.WorkTableRegister
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos

class StampingDeskBlockEntity(pos: BlockPos, state: BlockState) :
    BlockEntity(WorkTableRegister.Companion.EntityTypes.STAMPING_DESK_TYPE, pos, state) {
}