/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.books.journalbooks
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-24  00:45
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.books.journalbooks

import cn.timaviciix.ebm.block.blockentitys.bookentitys.JournalBookBlockEntity
import cn.timaviciix.ebm.block.books.BaseBookBlock
import cn.timaviciix.ebm.block.books.BookBlockInterface
import cn.timaviciix.ebm.registers.blocks.BookRegister
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos

class ClassicJournalBookBlock : BaseBookBlock(BookType.JournalBook),
    BlockEntityProvider, BookBlockInterface {

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
        return if (pos != null && state != null) {
            return JournalBookBlockEntity(BookRegister.EntityTypes.JOURNAL_CLASSIC_JOURNAL_BOOK_TYPE, pos, state)
        } else {
            null
        }
    }

}