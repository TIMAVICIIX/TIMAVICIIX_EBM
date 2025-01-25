/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.books
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-22  12:40
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.books

import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.World

interface BookBlockInterface {

    fun isEntityBlock(world: World, pos: BlockPos): Boolean {
        val state = world.getBlockState(pos)
        return state.isSolidBlock(world, pos)
    }

}