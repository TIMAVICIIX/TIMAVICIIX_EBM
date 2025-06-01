/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-16  12:55
 *@Description: EBM的Item Group
 *@Version: 1.0
 */

package cn.timaviciix.ebm

import cn.timaviciix.ebm.registers.blocks.books.BookBusRegister
import cn.timaviciix.ebm.registers.blocks.books.GeneralBookRegister
import cn.timaviciix.ebm.util.GlobalData
import io.wispforest.owo.itemgroup.Icon
import io.wispforest.owo.itemgroup.OwoItemGroup
import io.wispforest.owo.itemgroup.gui.ItemGroupButton
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

class EBMItemGroup(
    private val extendsParams: Any?,
    private val itemsAdvanceOperation: ((extendsParams: Any?) -> Any?)?
) {

    companion object {
        val EBM_ITEM_GROUP: OwoItemGroup = OwoItemGroup.builder(
            Identifier(GlobalData.MOD_ID, GlobalData.ITEM_GROUP_ID)
        ) {
            Icon.of(ItemStack(GeneralBookRegister.REFINED_LEATHER_BOOK))
        }.initializer {
            it.addButton(ItemGroupButton.github(it, "https://github.com/TIMAVICIIX/TIMAVICIIX_EBM"))
        }.build()
    }

    //extendsOperation check
    fun buildAndInitItemGroup() {
        itemsAdvanceOperation?.let {
            it(extendsParams)
        }
        EBM_ITEM_GROUP.initialize()
    }

    //Old Method

}