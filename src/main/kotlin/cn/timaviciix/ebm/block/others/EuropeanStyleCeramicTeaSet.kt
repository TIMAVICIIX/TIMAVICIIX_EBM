/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.others
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-25  07:38
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.others

import cn.timaviciix.ebm.block.BaseDirectBlock
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.DyeColor
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView

class EuropeanStyleCeramicTeaSet : BaseDirectBlock(GlobalData.getGeneralBlockSetting(DyeColor.CYAN)) {

    private val bodyShape: VoxelShape = VoxelShapes.cuboid(0.1875, 0.0, 0.1875, 0.8125, 0.6, 0.8125)

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