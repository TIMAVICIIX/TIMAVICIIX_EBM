/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.util
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-13  13:19
 *@Description: 该静态对象类容纳全局脚本数据，例如ModID
 *@Version: 1.0
 */

package cn.timaviciix.ebm.util

import cn.timaviciix.ebm.EBMItemGroup
import io.wispforest.owo.itemgroup.OwoItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.enums.Instrument
import net.minecraft.util.DyeColor
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object GlobalData {

    // Mod ID
    const val MOD_ID = "timaviciix_ebm"
    const val ITEM_GROUP_ID = "timaviciix_ebm_group"
    const val DESC_SUFFIX = "_desc"

    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

    //default Owo Setting
    val OWO_ITEM_BASE_64SETTING = OwoItemSettings().group(EBMItemGroup.EBM_ITEM_GROUP).maxCount(64)!!
    val OWO_ITEM_SIGNAL_SETTING = OwoItemSettings().group(EBMItemGroup.EBM_ITEM_GROUP).maxCount(1)!!

    fun getGeneralBlockSetting(
        mapColor: DyeColor = DyeColor.GRAY,
        instrument: Instrument = Instrument.BASEDRUM,
        opaque: Boolean = false
    ): FabricBlockSettings {

        return FabricBlockSettings.create().apply {
            collidable(true)
            mapColor(mapColor)
            instrument(instrument)
            strength(1.2F, 6.0F)
            if (!opaque) {
                nonOpaque()
            }
        }
    }

    object PacketsID {
        const val BOOK_READING_STATE_FROM_SERVER = MOD_ID + "book_reading_state_from_server"
        const val BOOK_READING_STATE_FROM_CLIENT = MOD_ID + "book_reading_state_from_client"
        const val BOOK_READING_INIT_STATE = MOD_ID + "book_reading_init"
    }


    //Item Group
    //@Imp:Deprecated
//    val EBM_ITEM_GROUP: OwoItemGroup = OwoItemGroup.builder(
//        Identifier(MOD_ID, "book")
//    ) {
//        Icon.of(ItemStack(BookRegister.REFINED_LEATHER_BOOK))
//    }.build()

    // Item id
//    object ItemId {
//        //books id
//        val CUT_SIZE_BOOK_PAPER = Pair("cut_size_book_paper",JournalBook::class)
//        val CLASSIC_JOURNAL_BOOK = Pair("classic_journal_book",JournalBook::class)
//        val REFINED_LEATHER_BOOK = Pair("refined_leather_book",GeneralBook::class)
//        val QUARTZ_LEATHER_BOOK = Pair("quartz_leather_book",GeneralBook::class)
//        val LUXURIOUS_GILDED_BOOK = Pair("luxurious_gilded_book",GeneralBook::class)
//        val QUARTZ_GILDED_BOOK = Pair("quartz_gilded_book.json",GeneralBook::class)
//        val STURDY_OBSIDIAN_GILDED_BOOK = Pair("sturdy_obsidian_gilded_book.json",GeneralBook::class)
//        val MAJESTIC_GILDED_BOOK = Pair("majestic_gilded_book",LargeBook::class)
//        val QUARTZ_RUBY_ENCRUSTED_BOOK = Pair("quartz_ruby_encrusted_book",LargeBook::class)
//        val SAPPHIRE_GILDED_BOOK = Pair("sapphire_gilded_book",LargeBook::class)
//        val RUBY_GILDED_BOOK = Pair("ruby_gilded_book.json",LargeBook::class)
//        val GOLDEN_BOUGH_FOREST_BOOK = Pair("golden_bough_forest_book",LightBook::class)
//        val CRIMSON_FLAME_GILDED_BOOK = Pair("crimson_flame_gilded_book",LightBook::class)
//
//        //bookcases id
//        val REFINED_LEATHER_BOOKCASE = Pair("refined_leather_bookcase",BookcaseItem::class)
//        val QUARTZ_LEATHER_BOOKCASE = Pair("quartz_leather_bookcase",BookcaseItem::class)
//        val LUXURIOUS_GILDED_BOOKCASE = Pair("luxurious_gilded_bookcase",BookcaseItem::class)
//        val QUARTZ_GILDED_BOOKCASE = Pair("quartz_gilded_bookcase",BookcaseItem::class)
//        val STURDY_OBSIDIAN_GILDED_BOOKCASE = Pair("sturdy_obsidian_gilded_bookcase",BookcaseItem::class)
//        val MAJESTIC_GILDED_BOOKCASE = Pair("majestic_gilded_bookcase",BookcaseItem::class)
//        val QUARTZ_RUBY_ENCRUSTED_BOOKCASE = Pair("quartz_ruby_encrusted_bookcase",BookcaseItem::class)
//        val SAPPHIRE_GILDED_BOOKCASE = Pair("sapphire_gilded_bookcase",BookcaseItem::class)
//        val RUBY_GILDED_BOOKCASE = Pair("ruby_gilded_bookcase",BookcaseItem::class)
//        val GOLDEN_BOUGH_FOREST_BOOKCASE = Pair("golden_bough_forest_bookcase",BookcaseItem::class)
//        val CRIMSON_FLAME_GILDED_BOOKCASE = Pair("crimson_flame_gilded_bookcase",BookcaseItem::class)
//
//        //other things id
//
//    }


}