/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-15  13:09
 *@Description: 书本注册容器，引用了owo库的注册容器ItemRegistryContainer
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.books

import cn.timaviciix.ebm.util.GlobalData
import cn.timaviciix.ebm.util.annotations.RegistryItem
import io.wispforest.owo.registration.reflect.ItemRegistryContainer
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier


//@Imp:Necessary Parameterless Constructors
class BookRegister() : ItemRegistryContainer {

    companion object {
        //Book Items
        val CLASSIC_JOURNAL_BOOK = registryMe(GlobalData.ItemId.CLASSIC_JOURNAL_BOOK, JournalBook())

        val CUT_SIZE_BOOK_PAPER = registryMe(GlobalData.ItemId.CUT_SIZE_BOOK_PAPER, GeneralBook())

        val CRIMSON_FLAME_GILDED_BOOK = registryMe(GlobalData.ItemId.CRIMSON_FLAME_GILDED_BOOK, LightBook())
        val GOLDEN_BOUGH_FOREST_BOOK = registryMe(GlobalData.ItemId.GOLDEN_BOUGH_FOREST_BOOK, LightBook())

        val REFINED_LEATHER_BOOK = registryMe(GlobalData.ItemId.REFINED_LEATHER_BOOK, GeneralBook())
        val QUARTZ_LEATHER_BOOK = registryMe(GlobalData.ItemId.QUARTZ_LEATHER_BOOK,GeneralBook())
        val LUXURIOUS_GILDED_BOOK = registryMe(GlobalData.ItemId.LUXURIOUS_GILDED_BOOK, GeneralBook())
        val QUART_GILDED_BOOK = registryMe(GlobalData.ItemId.QUARTZ_GILDED_BOOK, GeneralBook())
        val STURDY_OBSIDIAN_GILDED_BOOK = registryMe(GlobalData.ItemId.STURDY_OBSIDIAN_GILDED_BOOK, GeneralBook())

        val MAJESTIC_GILDED_BOOK = registryMe(GlobalData.ItemId.MAJESTIC_GILDED_BOOK, LargeBook())
        val QUARTZ_RUBY_ENCRUSTED_BOOK = registryMe(GlobalData.ItemId.QUARTZ_RUBY_ENCRUSTED_BOOK, LargeBook())
        val SAPPHIRE_GILDED_BOOK = registryMe(GlobalData.ItemId.SAPPHIRE_GILDED_BOOK, LargeBook())
        val RUBY_GILDED_BOOK = registryMe(GlobalData.ItemId.RUBY_GILDED_BOOK, LargeBook())


        //Bookcase Items
        val CRIMSON_FLAME_GILDED_BOOKCASE = registryMe(GlobalData.ItemId.CRIMSON_FLAME_GILDED_BOOKCASE, BookcaseItem())
        val GOLDEN_BOUGH_FOREST_BOOKCASE = registryMe(GlobalData.ItemId.GOLDEN_BOUGH_FOREST_BOOKCASE, BookcaseItem())

        val REFINED_LEATHER_BOOKCASE = registryMe(GlobalData.ItemId.REFINED_LEATHER_BOOKCASE, BookcaseItem())
        val QUARTZ_LEATHER_BOOKCASE = registryMe(GlobalData.ItemId.QUARTZ_LEATHER_BOOKCASE,BookcaseItem())
        val LUXURIOUS_GILDED_BOOKCASE = registryMe(GlobalData.ItemId.LUXURIOUS_GILDED_BOOKCASE, BookcaseItem())
        val QUART_GILDED_BOOKCASE = registryMe(GlobalData.ItemId.QUARTZ_GILDED_BOOKCASE, BookcaseItem())
        val STURDY_OBSIDIAN_GILDED_BOOKCASE = registryMe(GlobalData.ItemId.STURDY_OBSIDIAN_GILDED_BOOKCASE, BookcaseItem())

        val MAJESTIC_GILDED_BOOKCASE = registryMe(GlobalData.ItemId.MAJESTIC_GILDED_BOOKCASE, BookcaseItem())
        val QUARTZ_RUBY_ENCRUSTED_BOOKCASE = registryMe(GlobalData.ItemId.QUARTZ_RUBY_ENCRUSTED_BOOKCASE, BookcaseItem())
        val SAPPHIRE_GILDED_BOOKCASE = registryMe(GlobalData.ItemId.SAPPHIRE_GILDED_BOOKCASE, BookcaseItem())
        val RUBY_GILDED_BOOKCASE = registryMe(GlobalData.ItemId.RUBY_GILDED_BOOKCASE, BookcaseItem())

        private fun registryMe(name: String, item: Item): Item {
            return Registry.register(Registries.ITEM, Identifier(GlobalData.MOD_ID, name), item)
        }
    }

}