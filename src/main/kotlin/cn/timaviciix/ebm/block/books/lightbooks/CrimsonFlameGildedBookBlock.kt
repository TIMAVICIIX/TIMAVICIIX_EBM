/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.books.lightbooks
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-23  00:46
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.books.lightbooks

import cn.timaviciix.ebm.block.blockentitys.bookentitys.LightBookBlockEntity
import cn.timaviciix.ebm.block.books.BaseBookBlock
import cn.timaviciix.ebm.block.books.BookBlockInterface
import cn.timaviciix.ebm.registers.blocks.books.LightBookRegister
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos

class CrimsonFlameGildedBookBlock : BaseBookBlock(BookType.LightBook),
    BlockEntityProvider, BookBlockInterface {
    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
        return if (pos != null && state != null) {
            LightBookBlockEntity(LightBookRegister.EntityTypes.LIGHT_CRIMSON_FLAME_GILDED_BOOK_TYPE, pos, state)
        } else {
            null
        }
    }
}