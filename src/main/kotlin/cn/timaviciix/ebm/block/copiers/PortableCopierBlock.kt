/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.copiers
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  17:49
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.copiers

import cn.timaviciix.ebm.block.BaseDirectBlock
import cn.timaviciix.ebm.block.blockentitys.copierentitys.PortableCopierBlockEntity
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.DyeColor
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView

class PortableCopierBlock : BaseDirectBlock(GlobalData.getGeneralBlockSetting(DyeColor.GRAY)), BlockEntityProvider {

    private val bodyShape = VoxelShapes.cuboid(0.125, 0.0, 0.125, 0.875, 0.75, 0.875)

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
        return if (pos != null && state !== null) {
            return PortableCopierBlockEntity(pos, state)
        } else {
            null
        }
    }

    @Deprecated("Deprecated in Java")
    @Suppress("Don't find reason to deprecated this method")
    override fun getCollisionShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        return bodyShape
    }



    @Deprecated("Deprecated in Java")
    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        return bodyShape
    }


}