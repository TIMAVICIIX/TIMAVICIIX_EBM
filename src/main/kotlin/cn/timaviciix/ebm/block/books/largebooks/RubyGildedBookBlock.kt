/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.books.largebooks
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-23  00:02
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.books.largebooks

import cn.timaviciix.ebm.block.blockentitys.bookentitys.LargeBookBlockEntity
import cn.timaviciix.ebm.block.books.BaseBookBlock
import cn.timaviciix.ebm.block.books.BookBlockInterface
import cn.timaviciix.ebm.registers.blocks.BookRegister
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos

class RubyGildedBookBlock : BaseBookBlock(BookType.LargeBook),
    BlockEntityProvider, BookBlockInterface {
    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
        return if (pos != null && state != null) {
            LargeBookBlockEntity(BookRegister.EntityTypes.LARGE_RUBY_GILDED_BOOK_TYPE, pos, state)
        } else {
            null
        }
    }
}