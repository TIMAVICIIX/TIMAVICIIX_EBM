/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-15  13:09
 *@Description: 书本注册容器，引用了owo库的注册容器ItemRegistryContainer
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.books

import cn.timaviciix.ebm.util.GeneralUtil
import cn.timaviciix.ebm.util.GlobalData
import io.wispforest.owo.registration.reflect.ItemRegistryContainer


//@Imp:Necessary Parameterless Constructors
object BookRegister : ItemRegistryContainer {

    //Book Items
    val CLASSIC_JOURNAL_BOOK = JournalBook().registryMe(GlobalData.ItemId.CLASSIC_JOURNAL_BOOK)

    val REFINED_LEATHER_BOOK = GeneralBook().registryMe(GlobalData.ItemId.REFINED_LEATHER_BOOK)
    val LUXURIOUS_GILDED_BOOK = GeneralBook()
    val QUART_GILDED_BOOK = GeneralBook()
    val STURDY_OBSIDIAN_GILDED_BOOK = GeneralBook()

    val MAJESTIC_GILDED_BOOK = LargeBook()
    val QUARTZ_RUBY_ENCRUSTED_BOOK = LargeBook()
    val SAPPHIRE_GILDED_BOOK = LargeBook()
    val RUBY_GILDED_BOOK = LargeBook()

    val CRIMSON_FLAME_GILDED_BOOK = LightBook()
    val GOLDEN_BOUGH_FOREST_BOOK = LightBook()


    fun registryBooks(){

    }


}