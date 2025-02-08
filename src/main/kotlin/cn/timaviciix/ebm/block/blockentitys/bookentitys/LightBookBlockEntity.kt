/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.blockentitys.bookentitys
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-22  01:51
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.blockentitys.bookentitys

import cn.timaviciix.ebm.data.book.BookNbtType
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.math.BlockPos

class LightBookBlockEntity(
    entityType: BlockEntityType<*>,
    pos: BlockPos,
    state: BlockState
) :
    BaseBookBlockEntity(entityType, pos, state, BookNbtType.LightBook) {



}