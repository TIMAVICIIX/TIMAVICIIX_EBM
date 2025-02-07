/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.blockentitys.bookentitys
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-05  23:58
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.blockentitys.bookentitys

import cn.timaviciix.ebm.data.book.BookBlockType
import cn.timaviciix.ebm.data.book.BookDataConfig.MAX_SIZE_PER_NBT
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.math.BlockPos

open class BaseBookBlockEntity(
    entityType: BlockEntityType<*>,
    pos: BlockPos,
    state: BlockState,
    private val bookBlockType: BookBlockType = BookBlockType.GeneralBook
) : BlockEntity(entityType, pos, state) {



}