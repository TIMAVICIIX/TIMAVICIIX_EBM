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

    fun getMaxCharCount(): Int = bookBlockType.maxPage * bookBlockType.charsPerPage

    fun getMaxPage(): Int = bookBlockType.maxPage

    fun getContentMaxPage(contentCharNum: Int): Int = if (contentCharNum % bookBlockType.charsPerPage == 0) {
        contentCharNum / bookBlockType.charsPerPage
    } else {
        (contentCharNum / bookBlockType.charsPerPage) + 1
    }

    fun getNbtSplitPartCount(contentCharNum: Int): Int = contentCharNum / MAX_SIZE_PER_NBT


}