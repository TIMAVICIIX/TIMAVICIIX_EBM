/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.util
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-13  13:19
 *@Description: 该静态对象类容纳全局脚本数据，例如ModID
 *@Version: 1.0
 */

package cn.timaviciix.ebm.util

object GlobalData {

    // Mod ID
    const val MOD_ID = "timaviciix_ebm"
    const val ITEM_GROUP_ID = "timaviciix_ebm_group"


    //Books attributes
    const val PAGE_COUNT = "page_count"
    const val AUTHOR = "author"
    const val COPY_PERMISSION = "copy_permission"
    const val BOOK_ID = "book_id"
    const val PAGE_STORAGE_SUFFIX = "page_"
    const val DESC_SUFFIX = "_desc"

    //Item Group
    //@Imp:Deprecated
//    val EBM_ITEM_GROUP: OwoItemGroup = OwoItemGroup.builder(
//        Identifier(MOD_ID, "book")
//    ) {
//        Icon.of(ItemStack(BookRegister.REFINED_LEATHER_BOOK))
//    }.build()

    // Item id
    object ItemId {
        const val CUT_SIZE_BOOK_PAPER = "cut_size_book_paper"
        const val CLASSIC_JOURNAL_BOOK = "classic_journal_book"
        const val REFINED_LEATHER_BOOK = "refined_leather_book"
        const val QUARTZ_LEATHER_BOOK = "quartz_leather_book"
        const val LUXURIOUS_GILDED_BOOK = "luxurious_gilded_book"
        const val QUARTZ_GILDED_BOOK = "quartz_gilded_book"
        const val STURDY_OBSIDIAN_GILDED_BOOK = "sturdy_obsidian_gilded_book"
        const val MAJESTIC_GILDED_BOOK = "majestic_gilded_book"
        const val QUARTZ_RUBY_ENCRUSTED_BOOK = "quartz_ruby_encrusted_book"
        const val SAPPHIRE_GILDED_BOOK = "sapphire_gilded_book"
        const val RUBY_GILDED_BOOK = "ruby_gilded_book"
        const val GOLDEN_BOUGH_FOREST_BOOK = "golden_bough_forest_book"
        const val CRIMSON_FLAME_GILDED_BOOK = "crimson_flame_gilded_book"

        const val REFINED_LEATHER_BOOKCASE = "refined_leather_bookcase"
        const val QUARTZ_LEATHER_BOOKCASE = "quartz_leather_bookcase"
        const val LUXURIOUS_GILDED_BOOKCASE = "luxurious_gilded_bookcase"
        const val QUARTZ_GILDED_BOOKCASE = "quartz_gilded_bookcase"
        const val STURDY_OBSIDIAN_GILDED_BOOKCASE = "sturdy_obsidian_gilded_bookcase"
        const val MAJESTIC_GILDED_BOOKCASE = "majestic_gilded_bookcase"
        const val QUARTZ_RUBY_ENCRUSTED_BOOKCASE = "quartz_ruby_encrusted_bookcase"
        const val SAPPHIRE_GILDED_BOOKCASE = "sapphire_gilded_bookcase"
        const val RUBY_GILDED_BOOKCASE = "ruby_gilded_bookcase"
        const val GOLDEN_BOUGH_FOREST_BOOKCASE = "golden_bough_forest_bookcase"
        const val CRIMSON_FLAME_GILDED_BOOKCASE = "crimson_flame_gilded_bookcase"
    }


}