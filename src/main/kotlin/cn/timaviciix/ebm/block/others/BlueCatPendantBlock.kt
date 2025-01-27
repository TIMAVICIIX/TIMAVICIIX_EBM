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
import cn.timaviciix.ebm.registers.others.SoundRegister
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundCategory
import net.minecraft.util.ActionResult
import net.minecraft.util.DyeColor
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World

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
            val effectSide = ctx.side

            val belowPos = pos.down()

            if (world.getBlockState(belowPos).isSolidBlock(world, belowPos)) {
                return null
            }

            if (effectSide == Direction.DOWN) {
                return super.getPlacementState(ctx)
            }
        }

        return null
    }


    @Deprecated("Deprecated in Java")
    override fun onUse(
        state: BlockState?,
        world: World?,
        pos: BlockPos?,
        player: PlayerEntity?,
        hand: Hand?,
        hit: BlockHitResult?
    ): ActionResult {
        world?.playSound(null, pos, SoundRegister.BELL_LING, SoundCategory.PLAYERS, 5.0f, 1.0f)
        return ActionResult.SUCCESS
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