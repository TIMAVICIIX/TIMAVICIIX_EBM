/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.blocks.books
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  15:08
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks.books

import cn.timaviciix.ebm.block.blockentitys.bookentitys.GeneralBookBlockEntity
import cn.timaviciix.ebm.block.books.generalbooks.LuxuriousGildedBookBlock
import cn.timaviciix.ebm.block.books.generalbooks.QuartzLeatherBookBlock
import cn.timaviciix.ebm.block.books.generalbooks.RefinedLeatherBookBlock
import cn.timaviciix.ebm.block.books.generalbooks.SturdyObsidianGildedBookBlock
import cn.timaviciix.ebm.registers.blocks.BlockRegistryHandler
import cn.timaviciix.ebm.registers.blocks.books.components.BookRegistrySupplier
import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer
import io.wispforest.owo.registration.reflect.BlockRegistryContainer
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntityType

class GeneralBookRegister() : BlockRegistryContainer {


    companion object : BookRegistrySupplier() {
        override val nameColor: Int = 0xff9a00
        override val maxPage: Int = 800
        override val typeCode: Int = 1

        val REFINED_LEATHER_BOOK: RefinedLeatherBookBlock = registry(::REFINED_LEATHER_BOOK)
        val LUXURIOUS_GILDED_BOOK: LuxuriousGildedBookBlock = registry(::LUXURIOUS_GILDED_BOOK)
        val QUARTZ_GILDED_BOOK: LuxuriousGildedBookBlock = registry(::QUARTZ_GILDED_BOOK)
        val STURDY_OBSIDIAN_GILDED_BOOK: SturdyObsidianGildedBookBlock = registry(::STURDY_OBSIDIAN_GILDED_BOOK)
        val QUARTZ_LEATHER_BOOK: QuartzLeatherBookBlock = registry(::QUARTZ_LEATHER_BOOK)
    }

    class EntityTypes() : BlockEntityRegistryContainer {
        companion object {

            //general books
            val GENERAL_REFINED_LEATHER_BOOK_TYPE: BlockEntityType<GeneralBookBlockEntity> =
                BlockRegistryHandler.registryEntitySelf(
                    ::REFINED_LEATHER_BOOK,
                    FabricBlockEntityTypeBuilder.create({ pos, state ->
                        GeneralBookBlockEntity(
                            ParamEntityTypes.GENERAL_REFINED_LEATHER_BOOK_TYPE.blockEntityType,
                            pos, state
                        )
                    }, REFINED_LEATHER_BOOK).build()
                )
            val GENERAL_LUXURIOUS_GILDED_BOOK_TYPE: BlockEntityType<GeneralBookBlockEntity> =
                BlockRegistryHandler.registryEntitySelf(
                    ::LUXURIOUS_GILDED_BOOK,
                    FabricBlockEntityTypeBuilder.create({ pos, state ->
                        GeneralBookBlockEntity(
                            ParamEntityTypes.GENERAL_LUXURIOUS_GILDED_BOOK_TYPE.blockEntityType,
                            pos, state
                        )

                    }, LUXURIOUS_GILDED_BOOK).build()
                )
            val GENERAL_QUARTZ_GILDED_BOOK_TYPE: BlockEntityType<GeneralBookBlockEntity> =
                BlockRegistryHandler.registryEntitySelf(
                    ::QUARTZ_GILDED_BOOK,
                    FabricBlockEntityTypeBuilder.create({ pos, state ->
                        GeneralBookBlockEntity(
                            ParamEntityTypes.GENERAL_QUARTZ_GILDED_BOOK_TYPE.blockEntityType,
                            pos, state
                        )
                    }, QUARTZ_GILDED_BOOK).build()
                )
            val GENERAL_STURDY_OBSIDIAN_GILDED_BOOK_TYPE: BlockEntityType<GeneralBookBlockEntity> =
                BlockRegistryHandler.registryEntitySelf(
                    ::STURDY_OBSIDIAN_GILDED_BOOK,
                    FabricBlockEntityTypeBuilder.create({ pos, state ->
                        GeneralBookBlockEntity(
                            ParamEntityTypes.GENERAL_STURDY_OBSIDIAN_GILDED_BOOK_TYPE.blockEntityType,
                            pos, state
                        )
                    }, STURDY_OBSIDIAN_GILDED_BOOK).build()
                )
            val GENERAL_QUARTZ_LEATHER_BOOK_TYPE: BlockEntityType<GeneralBookBlockEntity> =
                BlockRegistryHandler.registryEntitySelf(
                    ::QUARTZ_LEATHER_BOOK,
                    FabricBlockEntityTypeBuilder.create({ pos, state ->
                        GeneralBookBlockEntity(
                            ParamEntityTypes.GENERAL_QUARTZ_LEATHER_BOOK_TYPE.blockEntityType,
                            pos, state
                        )
                    }, QUARTZ_LEATHER_BOOK).build()
                )

            enum class ParamEntityTypes(val blockEntityType: BlockEntityType<*>) {
                GENERAL_REFINED_LEATHER_BOOK_TYPE(EntityTypes.GENERAL_REFINED_LEATHER_BOOK_TYPE),
                GENERAL_LUXURIOUS_GILDED_BOOK_TYPE(EntityTypes.GENERAL_LUXURIOUS_GILDED_BOOK_TYPE),
                GENERAL_QUARTZ_GILDED_BOOK_TYPE(EntityTypes.GENERAL_QUARTZ_GILDED_BOOK_TYPE),
                GENERAL_STURDY_OBSIDIAN_GILDED_BOOK_TYPE(EntityTypes.GENERAL_STURDY_OBSIDIAN_GILDED_BOOK_TYPE),
                GENERAL_QUARTZ_LEATHER_BOOK_TYPE(EntityTypes.GENERAL_QUARTZ_LEATHER_BOOK_TYPE),
            }
        }
    }

}