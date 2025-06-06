/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-15  13:09
 *@Description: 书本注册容器，引用了owo库的注册容器ItemRegistryContainer
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.items.registers

import cn.timaviciix.ebm.item.books.BookcaseItem
import cn.timaviciix.ebm.registers.items.ItemRegistryHandler.Companion.registrySelf
import io.wispforest.owo.registration.reflect.ItemRegistryContainer


//@Imp:Necessary Parameterless Constructors
class BookcaseRegister : ItemRegistryContainer {
    companion object {
        // Book Items
//        val CLASSIC_JOURNAL_BOOK: JournalBook = registrySelf(Companion::CLASSIC_JOURNAL_BOOK)
//
//        val CUT_SIZE_BOOK_PAPER: GeneralBook = registrySelf(Companion::CUT_SIZE_BOOK_PAPER)
//
//        val CRIMSON_FLAME_GILDED_BOOK: LightBook = registrySelf(Companion::CRIMSON_FLAME_GILDED_BOOK)
//        val GOLDEN_BOUGH_FOREST_BOOK: LightBook = registrySelf(Companion::GOLDEN_BOUGH_FOREST_BOOK)
//
//        val REFINED_LEATHER_BOOK: GeneralBook = registrySelf(Companion::REFINED_LEATHER_BOOK)
//        val QUARTZ_LEATHER_BOOK: GeneralBook = registrySelf(Companion::QUARTZ_LEATHER_BOOK)
//        val LUXURIOUS_GILDED_BOOK: GeneralBook = registrySelf(Companion::LUXURIOUS_GILDED_BOOK)
//        val QUARTZ_GILDED_BOOK: GeneralBook = registrySelf(Companion::QUARTZ_GILDED_BOOK)
//        val STURDY_OBSIDIAN_GILDED_BOOK: GeneralBook = registrySelf(Companion::STURDY_OBSIDIAN_GILDED_BOOK)
//
//        val MAJESTIC_GILDED_BOOK: LargeBook = registrySelf(Companion::MAJESTIC_GILDED_BOOK)
//        val QUARTZ_RUBY_ENCRUSTED_BOOK: LargeBook = registrySelf(Companion::QUARTZ_RUBY_ENCRUSTED_BOOK)
//        val SAPPHIRE_GILDED_BOOK: LargeBook = registrySelf(Companion::SAPPHIRE_GILDED_BOOK)
//        val RUBY_GILDED_BOOK: LargeBook = registrySelf(Companion::RUBY_GILDED_BOOK)

        // Bookcase Items
        val CRIMSON_FLAME_GILDED_BOOKCASE: BookcaseItem = registrySelf(::CRIMSON_FLAME_GILDED_BOOKCASE)
        val GOLDEN_BOUGH_FOREST_BOOKCASE: BookcaseItem = registrySelf(::GOLDEN_BOUGH_FOREST_BOOKCASE)
        val REFINED_LEATHER_BOOKCASE: BookcaseItem = registrySelf(::REFINED_LEATHER_BOOKCASE)
        val QUARTZ_LEATHER_BOOKCASE: BookcaseItem = registrySelf(::QUARTZ_LEATHER_BOOKCASE)
        val LUXURIOUS_GILDED_BOOKCASE: BookcaseItem = registrySelf(::LUXURIOUS_GILDED_BOOKCASE)
        val QUARTZ_GILDED_BOOKCASE: BookcaseItem = registrySelf(::QUARTZ_GILDED_BOOKCASE)
        val STURDY_OBSIDIAN_GILDED_BOOKCASE: BookcaseItem = registrySelf(::STURDY_OBSIDIAN_GILDED_BOOKCASE)
        val MAJESTIC_GILDED_BOOKCASE: BookcaseItem = registrySelf(::MAJESTIC_GILDED_BOOKCASE)
        val QUARTZ_RUBY_ENCRUSTED_BOOKCASE: BookcaseItem = registrySelf(::QUARTZ_RUBY_ENCRUSTED_BOOKCASE)
        val SAPPHIRE_GILDED_BOOKCASE: BookcaseItem = registrySelf(::SAPPHIRE_GILDED_BOOKCASE)
        val RUBY_GILDED_BOOKCASE: BookcaseItem = registrySelf(::RUBY_GILDED_BOOKCASE)


    }
}