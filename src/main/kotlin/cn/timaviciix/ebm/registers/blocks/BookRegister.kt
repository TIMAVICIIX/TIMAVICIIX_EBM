/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.blocks
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-22  01:52
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks

import cn.timaviciix.ebm.block.blockentitys.bookentitys.GeneralBookBlockEntity
import cn.timaviciix.ebm.block.books.generalbooks.*
import cn.timaviciix.ebm.registers.blocks.BaseBlockRegister.Companion.registrySelf
import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer
import io.wispforest.owo.registration.reflect.BlockRegistryContainer
import kotlinx.serialization.builtins.FloatArraySerializer
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntityType

class BookRegister():BlockRegistryContainer {

    companion object{
        val REFINED_LEATHER_BOOK:RefinedLeatherBookBlock = registrySelf(::REFINED_LEATHER_BOOK,0xff8264)
        val LUXURIOUS_GILDED_BOOK:LuxuriousGildedBookBlock = registrySelf(::LUXURIOUS_GILDED_BOOK,0xff8264)
        val QUARTZ_GILDED_BOOK:QuartzGildedBookBlock = registrySelf(::QUARTZ_GILDED_BOOK,0xff8264)
        val STURDY_OBSIDIAN_GILDED_BOOK:SturdyObsidianGildedBookBlock = registrySelf(::STURDY_OBSIDIAN_GILDED_BOOK,0xff8264)
        val QUARTZ_LEATHER_BOOK:QuartzLeatherBookBlock = registrySelf(::QUARTZ_LEATHER_BOOK,0xff8264)
    }

    class EntityTypes():BlockEntityRegistryContainer{

        companion object{
            val GENERAL_REFINED_LEATHER_BOOK_TYPE:BlockEntityType<GeneralBookBlockEntity> =
                FabricBlockEntityTypeBuilder.create(::GeneralBookBlockEntity, REFINED_LEATHER_BOOK).build()
            val GENERAL_LUXURIOUS_GILDED_BOOK_TYPE:BlockEntityType<GeneralBookBlockEntity> =
                FabricBlockEntityTypeBuilder.create(::GeneralBookBlockEntity, LUXURIOUS_GILDED_BOOK).build()
            val GENERAL_QUARTZ_GILDED_BOOK_TYPE:BlockEntityType<GeneralBookBlockEntity> =
                FabricBlockEntityTypeBuilder.create(::GeneralBookBlockEntity, QUARTZ_GILDED_BOOK).build()
            val GENERAL_STURDY_OBSIDIAN_GILDED_BOOK_TYPE:BlockEntityType<GeneralBookBlockEntity> =
                FabricBlockEntityTypeBuilder.create(::GeneralBookBlockEntity, STURDY_OBSIDIAN_GILDED_BOOK).build()
            val GENERAL_QUARTZ_LEATHER_BOOK_TYPE:BlockEntityType<GeneralBookBlockEntity> =
                FabricBlockEntityTypeBuilder.create(::GeneralBookBlockEntity, QUARTZ_LEATHER_BOOK).build()
        }

    }

}