/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.others
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-25  12:58
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.others

import cn.timaviciix.ebm.block.BaseDirectBlock
import cn.timaviciix.ebm.block.blockentitys.others.BlueCatPendantBlockEntity
import cn.timaviciix.ebm.block.books.BookBlockInterface
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.block.entity.BlockEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.DyeColor
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView

class BlueCatPendantBlock : BaseDirectBlock(GlobalData.getGeneralBlockSetting(DyeColor.BLUE)),
    BlockEntityProvider, BookBlockInterface {

    private val bodyShape: VoxelShape = VoxelShapes.cuboid(0.3125, 0.0, 0.3125, 0.6875, 1.0, 0.6875)

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
        return if (pos != null && state != null) {
            BlueCatPendantBlockEntity(pos, state)
        } else {
            null
        }
    }

    @Deprecated(
        "Deprecated in Java",
        replaceWith = ReplaceWith("BlockRenderType.MODEL", "net.minecraft.block.BlockRenderType")
    )
    override fun getRenderType(state: BlockState?): BlockRenderType {
        return BlockRenderType.ENTITYBLOCK_ANIMATED
    }

    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        ctx?.let {
            val pos = ctx.blockPos
            val world = ctx.world

            val belowPos = pos.down()

            if (world.getBlockState(belowPos).isSolidBlock(world, belowPos)) {
                return null
            }

            return super.getPlacementState(ctx)

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

    @Deprecated("Deprecated in Java")
    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        return bodyShape
    }

    override fun getSoundGroup(state: BlockState?): BlockSoundGroup {
        return BlockSoundGroup.GLASS
    }
}