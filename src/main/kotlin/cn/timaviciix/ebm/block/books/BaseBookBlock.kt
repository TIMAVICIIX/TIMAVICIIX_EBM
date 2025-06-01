/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.books
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-22  22:56
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.books

import cn.timaviciix.ebm.block.BaseDirectBlock
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.DyeColor
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import org.slf4j.LoggerFactory

abstract class BaseBookBlock(private val bookType: BookType) :
    BaseDirectBlock(GlobalData.getGeneralBlockSetting(DyeColor.BROWN)) {

    enum class BookType(val bodyShape: VoxelShape) {
        JournalBook(
            VoxelShapes.union(
                VoxelShapes.cuboid(0.25, 0.0, 0.1875, 0.75, 0.015625, 0.8125),
                VoxelShapes.cuboid(0.75, 0.015625, 0.1875, 0.765625, 0.078125, 0.8125),
                VoxelShapes.cuboid(0.25, 0.078125, 0.1875, 0.75, 0.09375, 0.8125),
                VoxelShapes.cuboid(0.265625, 0.015625, 0.203125, 0.75, 0.078125, 0.796875)
            )
        ),
        GeneralBook(VoxelShapes.cuboid(0.25, 0.0, 0.25, 0.75, 0.25, 0.75)),
        LargeBook(VoxelShapes.cuboid(0.1875, 0.0, 0.1875, 0.8125, 0.34, 0.8125)),
        LightBook(VoxelShapes.cuboid(0.3125, 0.0, 0.25, 0.6875, 0.24, 0.75))
    }

    @Deprecated("Deprecated in Java")
    @Suppress("Don't find reason to deprecated this method")
    override fun getCollisionShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        return bookType.bodyShape
    }


    @Deprecated("Deprecated in Java")
    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        return bookType.bodyShape
    }

}