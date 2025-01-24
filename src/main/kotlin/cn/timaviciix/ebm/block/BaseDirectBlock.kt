/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  18:03
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block

import cn.timaviciix.ebm.util.GlobalData
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.DirectionProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.BlockMirror
import net.minecraft.util.BlockRotation
import net.minecraft.util.math.Direction
import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class BaseDirectBlock(settings: FabricBlockSettings) : Block(settings) {

    companion object {
        //val logger: Logger = LoggerFactory.getLogger(GlobalData.MOD_ID)
        val FACING: DirectionProperty = Properties.HORIZONTAL_FACING
    }

    init {
        stateManager.defaultState.with(FACING, Direction.NORTH)
    }

    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        return defaultState.with(FACING, ctx?.horizontalPlayerFacing?.opposite)
        //logger.info("Block Direction:${tempBlockState.get(FACING)}")
    }

    @Deprecated("Deprecated in Java", ReplaceWith("BlockRenderType.MODEL", "net.minecraft.block.BlockRenderType"))
    override fun getRenderType(state: BlockState?): BlockRenderType {
        return BlockRenderType.MODEL
    }

    @Deprecated("Deprecated in Java", ReplaceWith("", ""))
    override fun rotate(state: BlockState?, rotation: BlockRotation?): BlockState {
        return state?.with(FACING, rotation?.rotate(state.get(FACING)))!!
    }

    @Deprecated("Deprecated in Java", ReplaceWith("", ""))
    override fun mirror(state: BlockState?, mirror: BlockMirror?): BlockState {
        return state?.rotate(mirror?.getRotation(state.get(FACING)))!!
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder?.add(FACING)
    }

}