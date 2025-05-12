/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.blockentitys.bookentitys
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-05  23:58
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.blockentitys.bookentitys

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.math.BlockPos

abstract class BaseBookBlockEntity(
    entityType: BlockEntityType<*>,
    pos: BlockPos,
    state: BlockState
) : BlockEntity(entityType, pos, state) {

    abstract val typeCode : Int
    abstract val maxPage: Int

}