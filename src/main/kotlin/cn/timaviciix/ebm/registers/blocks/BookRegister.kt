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
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntityType

class BookRegister() : BlockRegistryContainer {

    companion object {
        val REFINED_LEATHER_BOOK: RefinedLeatherBookBlock = registrySelf(::REFINED_LEATHER_BOOK, 0xff8264)
        val LUXURIOUS_GILDED_BOOK: LuxuriousGildedBookBlock = registrySelf(::LUXURIOUS_GILDED_BOOK, 0xff8264)
        val QUARTZ_GILDED_BOOK: QuartzGildedBookBlock = registrySelf(::QUARTZ_GILDED_BOOK, 0xff8264)
        val STURDY_OBSIDIAN_GILDED_BOOK: SturdyObsidianGildedBookBlock =
            registrySelf(::STURDY_OBSIDIAN_GILDED_BOOK, 0xff8264)
        val QUARTZ_LEATHER_BOOK: QuartzLeatherBookBlock = registrySelf(::QUARTZ_LEATHER_BOOK, 0xff8264)
    }

    class EntityTypes() : BlockEntityRegistryContainer {

        companion object {
            val GENERAL_REFINED_LEATHER_BOOK_TYPE: BlockEntityType<GeneralBookBlockEntity> =
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    GeneralBookBlockEntity(
                        ParamEntityTypes.GENERAL_REFINED_LEATHER_BOOK_TYPE.blockEntityType,
                        pos, state
                    )
                }, REFINED_LEATHER_BOOK).build()
            val GENERAL_LUXURIOUS_GILDED_BOOK_TYPE: BlockEntityType<GeneralBookBlockEntity> =
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    GeneralBookBlockEntity(
                        ParamEntityTypes.GENERAL_LUXURIOUS_GILDED_BOOK_TYPE.blockEntityType,
                        pos, state
                    )

                }, LUXURIOUS_GILDED_BOOK).build()
            val GENERAL_QUARTZ_GILDED_BOOK_TYPE: BlockEntityType<GeneralBookBlockEntity> =
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    GeneralBookBlockEntity(
                        ParamEntityTypes.GENERAL_QUARTZ_GILDED_BOOK_TYPE.blockEntityType,
                        pos, state
                    )
                }, QUARTZ_GILDED_BOOK).build()
            val GENERAL_STURDY_OBSIDIAN_GILDED_BOOK_TYPE: BlockEntityType<GeneralBookBlockEntity> =
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    GeneralBookBlockEntity(
                        ParamEntityTypes.GENERAL_STURDY_OBSIDIAN_GILDED_BOOK_TYPE.blockEntityType,
                        pos, state
                    )
                }, STURDY_OBSIDIAN_GILDED_BOOK).build()
            val GENERAL_QUARTZ_LEATHER_BOOK_TYPE: BlockEntityType<GeneralBookBlockEntity> =
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    GeneralBookBlockEntity(
                        ParamEntityTypes.GENERAL_QUARTZ_LEATHER_BOOK_TYPE.blockEntityType,
                        pos, state
                    )
                }, QUARTZ_LEATHER_BOOK).build()

            enum class ParamEntityTypes(val blockEntityType: BlockEntityType<*>) {
                GENERAL_REFINED_LEATHER_BOOK_TYPE(EntityTypes.GENERAL_REFINED_LEATHER_BOOK_TYPE),
                GENERAL_LUXURIOUS_GILDED_BOOK_TYPE(EntityTypes.GENERAL_LUXURIOUS_GILDED_BOOK_TYPE),
                GENERAL_QUARTZ_GILDED_BOOK_TYPE(EntityTypes.GENERAL_QUARTZ_GILDED_BOOK_TYPE),
                GENERAL_STURDY_OBSIDIAN_GILDED_BOOK_TYPE(EntityTypes.GENERAL_STURDY_OBSIDIAN_GILDED_BOOK_TYPE),
                GENERAL_QUARTZ_LEATHER_BOOK_TYPE(EntityTypes.GENERAL_QUARTZ_LEATHER_BOOK_TYPE)
            }
        }

    }

}