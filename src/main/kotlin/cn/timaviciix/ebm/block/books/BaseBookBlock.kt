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
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.DyeColor
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldView
import org.slf4j.LoggerFactory

abstract class BaseBookBlock(private val bookType: BookType) :
    BaseDirectBlock(GlobalData.getGeneralBlockSetting(DyeColor.BROWN)) {

    private val logger = LoggerFactory.getLogger(GlobalData.MOD_ID)


    enum class BookType(val bodyShape: VoxelShape) {
        GeneralBook(VoxelShapes.cuboid(0.25, 0.0, 0.25, 0.75, 0.25, 0.75)),
        LargeBook(VoxelShapes.cuboid(0.1875, 0.0, 0.1875, 0.8125, 0.4, 0.8125)),
        LightBook(VoxelShapes.cuboid(0.3125,0.0,0.25,0.6875,0.3,0.75))
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