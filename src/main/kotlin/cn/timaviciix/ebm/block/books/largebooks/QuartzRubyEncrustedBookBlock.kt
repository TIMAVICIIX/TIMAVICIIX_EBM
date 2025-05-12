/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.books.largebooks
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-23  00:01
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.books.largebooks

import cn.timaviciix.ebm.block.blockentitys.bookentitys.LargeBookBlockEntity
import cn.timaviciix.ebm.block.books.BaseBookBlock
import cn.timaviciix.ebm.block.books.BookBlockInterface
import cn.timaviciix.ebm.registers.blocks.books.LargeBookRegister
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos

class QuartzRubyEncrustedBookBlock : BaseBookBlock(BookType.LargeBook),
    BlockEntityProvider, BookBlockInterface {

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
        return if (pos != null && state != null) {
            LargeBookBlockEntity(LargeBookRegister.EntityTypes.LARGE_QUARTZ_RUBY_ENCRUSTED_BOOK_TYPE, pos, state)
        } else {
            null
        }
    }

}