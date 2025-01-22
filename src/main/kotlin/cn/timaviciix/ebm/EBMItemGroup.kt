/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-16  12:55
 *@Description: EBM的Item Group
 *@Version: 1.0
 */

package cn.timaviciix.ebm

import cn.timaviciix.ebm.registers.blocks.BookRegister
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
            Icon.of(ItemStack(BookRegister.REFINED_LEATHER_BOOK))
        }.initializer {
            it.addButton(ItemGroupButton.github(it, "https://github.com/TIMAVICIIX/TIMAVICIIX_EBM"))
        }.build()

        //@Imp:Deprecated
//        fun addItemsToIG(entries:FabricItemGroupEntries){
//            entries.apply {
//                add(BookRegister.CLASSIC_JOURNAL_BOOK)
//                add(BookRegister.CRIMSON_FLAME_GILDED_BOOK)
//                add(BookRegister.GOLDEN_BOUGH_FOREST_BOOK)
//                add(BookRegister.LUXURIOUS_GILDED_BOOK)
//                add(BookRegister.MAJESTIC_GILDED_BOOK)
//                add(BookRegister.MAJESTIC_GILDED_BOOK)
//                add(BookRegister.QUART_GILDED_BOOK)
//                add(BookRegister.QUARTZ_RUBY_ENCRUSTED_BOOK)
//                add(BookRegister.REFINED_LEATHER_BOOK)
//                add(BookRegister.RUBY_GILDED_BOOK)
//                add(BookRegister.SAPPHIRE_GILDED_BOOK)
//                add(BookRegister.STURDY_OBSIDIAN_GILDED_BOOK)
//            }
//        }
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