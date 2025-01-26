/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.worktables
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-27  00:53
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.worktables

import cn.timaviciix.ebm.block.BaseDirectBlock
import cn.timaviciix.ebm.block.blockentitys.worktables.LibrarianDeskBlockEntity
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.DyeColor
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView

class LibrarianDeskBlock : BaseDirectBlock(GlobalData.getGeneralBlockSetting(DyeColor.BROWN)) {

    private val bodyShape: VoxelShape = VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
        return if (pos != null && state != null) {
            LibrarianDeskBlockEntity(pos, state)
        } else {
            null
        }
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