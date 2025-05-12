/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.blockentitys.worktables
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-27  03:38
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.blockentitys.worktables

import cn.timaviciix.ebm.registers.blocks.blocks.WorkTableRegister
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos

class BindingMachineBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(
    WorkTableRegister.Companion.EntityTypes.BINDING_MACHINE_TYPE, pos, state
) {
}