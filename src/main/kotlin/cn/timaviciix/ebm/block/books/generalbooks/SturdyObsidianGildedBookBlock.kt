/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.books.generalbooks
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-22  01:41
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.books.generalbooks

import cn.timaviciix.ebm.block.BaseDirectBlock
import cn.timaviciix.ebm.block.blockentitys.bookentitys.GeneralBookBlockEntity
import cn.timaviciix.ebm.block.books.BaseBookBlock
import cn.timaviciix.ebm.block.books.BookBlockInterface
import cn.timaviciix.ebm.registers.blocks.BookRegister
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.DyeColor
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView

class SturdyObsidianGildedBookBlock : BaseBookBlock(BookType.GeneralBook),
    BlockEntityProvider, BookBlockInterface {

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
        return if (pos != null && state != null) {
            GeneralBookBlockEntity(BookRegister.EntityTypes.GENERAL_STURDY_OBSIDIAN_GILDED_BOOK_TYPE, pos, state)
        } else {
            null
        }
    }

}