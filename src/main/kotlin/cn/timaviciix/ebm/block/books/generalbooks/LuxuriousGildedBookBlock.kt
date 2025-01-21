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
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.DyeColor
import net.minecraft.util.math.BlockPos

class LuxuriousGildedBookBlock:BaseDirectBlock(GlobalData.getGeneralBlockSetting(DyeColor.BROWN)),BlockEntityProvider {

    override fun createBlockEntity(pos: BlockPos?, state: BlockState?): BlockEntity? {
        TODO("Not yet implemented")
    }

}