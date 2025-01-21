/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.copiers
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-21  12:45
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.copiers

import cn.timaviciix.ebm.block.BaseBlock
import cn.timaviciix.ebm.block.blockentitys.VerticalCopierBlockEntity
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.DyeColor
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView

class VerticalCopierBlock : BaseBlock(GlobalData.getGeneralBlockSetting(DyeColor.BLACK)) {

    private val bodyShape = VoxelShapes.cuboid(0.0625, 0.0625, 0.0, 0.9375, 0.9375, 2.0)

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
        if (pos != null && state !== null) {

            return VerticalCopierBlockEntity(pos, state)

        }

        return null
    }

    @Deprecated("Deprecated in Java")
    override fun getCollisionShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        return bodyShape
    }

}