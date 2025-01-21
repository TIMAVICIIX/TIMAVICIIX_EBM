/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.blockentitys
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-21  12:53
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.blockentitys.copierentitys

import cn.timaviciix.ebm.registers.blocks.CopierRegister
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos

class VerticalCopierBlockEntity(pos: BlockPos,state: BlockState):
    BlockEntity(CopierRegister.EntityTypes.VERTICAL_COPIER_TYPE,pos, state) {
}