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
import cn.timaviciix.ebm.block.blockentitys.bookentitys.JournalBookBlockEntity
import cn.timaviciix.ebm.block.blockentitys.bookentitys.LargeBookBlockEntity
import cn.timaviciix.ebm.block.blockentitys.bookentitys.LightBookBlockEntity
import cn.timaviciix.ebm.block.books.generalbooks.*
import cn.timaviciix.ebm.block.books.journalbooks.ClassicJournalBookBlock
import cn.timaviciix.ebm.block.books.largebooks.MajesticGildedBookBlock
import cn.timaviciix.ebm.block.books.largebooks.QuartzRubyEncrustedBookBlock
import cn.timaviciix.ebm.block.books.largebooks.RubyGildedBookBlock
import cn.timaviciix.ebm.block.books.largebooks.SapphireGildedBookBlock
import cn.timaviciix.ebm.block.books.lightbooks.CrimsonFlameGildedBookBlock
import cn.timaviciix.ebm.block.books.lightbooks.GoldenBoughForestBookBlock
import cn.timaviciix.ebm.item.blockitems.BaseBlockItem
import cn.timaviciix.ebm.registers.blocks.BaseBlockRegister.Companion.registryEntitySelf
import cn.timaviciix.ebm.registers.blocks.BaseBlockRegister.Companion.registrySelf
import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer
import io.wispforest.owo.registration.reflect.BlockRegistryContainer
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.BlockItem

class BookRegister() : BlockRegistryContainer {

    companion object {
        val blockItemsConsist: MutableList<Pair<BlockItem,String>> = mutableListOf()


        val CLASSIC_JOURNAL_BOOK: ClassicJournalBookBlock =
            registrySelf(::CLASSIC_JOURNAL_BOOK, {
                blockItemsConsist.add(it)
            }, 0xdbe2ef,BaseBlockItem.Companion.BlockItemClassify.Books)


        val REFINED_LEATHER_BOOK: RefinedLeatherBookBlock = registrySelf(::REFINED_LEATHER_BOOK, {
            blockItemsConsist.add(it)
        }, 0xff8264, BaseBlockItem.Companion.BlockItemClassify.Books)


        val LUXURIOUS_GILDED_BOOK: LuxuriousGildedBookBlock = registrySelf(::LUXURIOUS_GILDED_BOOK, {
            blockItemsConsist.add(it)
        }, 0xff8264, BaseBlockItem.Companion.BlockItemClassify.Books)


        val QUARTZ_GILDED_BOOK: QuartzGildedBookBlock = registrySelf(::QUARTZ_GILDED_BOOK, {
            blockItemsConsist.add(it)
        }, 0xff8264, BaseBlockItem.Companion.BlockItemClassify.Books)


        val STURDY_OBSIDIAN_GILDED_BOOK: SturdyObsidianGildedBookBlock = registrySelf(::STURDY_OBSIDIAN_GILDED_BOOK, {
            blockItemsConsist.add(it)
        }, 0xff8264,BaseBlockItem.Companion.BlockItemClassify.Books)


        val QUARTZ_LEATHER_BOOK: QuartzLeatherBookBlock = registrySelf(::QUARTZ_LEATHER_BOOK, {
            blockItemsConsist.add(it)
        }, 0xff8264, BaseBlockItem.Companion.BlockItemClassify.Books)


        val MAJESTIC_GILDED_BOOK: MajesticGildedBookBlock = registrySelf(::MAJESTIC_GILDED_BOOK, {
            blockItemsConsist.add(it)
        }, 0xe84545, BaseBlockItem.Companion.BlockItemClassify.Books)


        val QUARTZ_RUBY_ENCRUSTED_BOOK: QuartzRubyEncrustedBookBlock = registrySelf(::QUARTZ_RUBY_ENCRUSTED_BOOK, {
            blockItemsConsist.add(it)
        }, 0xe84545, BaseBlockItem.Companion.BlockItemClassify.Books)


        val RUBY_GILDED_BOOK: RubyGildedBookBlock = registrySelf(::RUBY_GILDED_BOOK, {
            blockItemsConsist.add(it)
        }, 0xe84545, BaseBlockItem.Companion.BlockItemClassify.Books)


        val SAPPHIRE_GILDED_BOOK: SapphireGildedBookBlock = registrySelf(::SAPPHIRE_GILDED_BOOK, {
            blockItemsConsist.add(it)
        }, 0xe84545, BaseBlockItem.Companion.BlockItemClassify.Books)


        val GOLDEN_BOUGH_FOREST_BOOK: GoldenBoughForestBookBlock = registrySelf(::GOLDEN_BOUGH_FOREST_BOOK, {
            blockItemsConsist.add(it)
        }, 0x10ddc2, BaseBlockItem.Companion.BlockItemClassify.Books)


        val CRIMSON_FLAME_GILDED_BOOK: CrimsonFlameGildedBookBlock = registrySelf(::CRIMSON_FLAME_GILDED_BOOK, {
            blockItemsConsist.add(it)
        }, 0xfa4659, BaseBlockItem.Companion.BlockItemClassify.Books)

    }

    class EntityTypes() : BlockEntityRegistryContainer {

        companion object {
            //journal books
            val JOURNAL_CLASSIC_JOURNAL_BOOK_TYPE: BlockEntityType<JournalBookBlockEntity> = registryEntitySelf(
                ::CLASSIC_JOURNAL_BOOK,
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    JournalBookBlockEntity(
                        ParamEntityTypes.JOURNAL_CLASSIC_JOURNAL_BOOK_TYPE.blockEntityType,
                        pos, state
                    )
                }, CLASSIC_JOURNAL_BOOK).build()
            )

            //general books
            val GENERAL_REFINED_LEATHER_BOOK_TYPE: BlockEntityType<GeneralBookBlockEntity> = registryEntitySelf(
                ::REFINED_LEATHER_BOOK,
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    GeneralBookBlockEntity(
                        ParamEntityTypes.GENERAL_REFINED_LEATHER_BOOK_TYPE.blockEntityType,
                        pos, state
                    )
                }, REFINED_LEATHER_BOOK).build()
            )
            val GENERAL_LUXURIOUS_GILDED_BOOK_TYPE: BlockEntityType<GeneralBookBlockEntity> = registryEntitySelf(
                ::LUXURIOUS_GILDED_BOOK,
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    GeneralBookBlockEntity(
                        ParamEntityTypes.GENERAL_LUXURIOUS_GILDED_BOOK_TYPE.blockEntityType,
                        pos, state
                    )

                }, LUXURIOUS_GILDED_BOOK).build()
            )
            val GENERAL_QUARTZ_GILDED_BOOK_TYPE: BlockEntityType<GeneralBookBlockEntity> = registryEntitySelf(
                ::QUARTZ_GILDED_BOOK,
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    GeneralBookBlockEntity(
                        ParamEntityTypes.GENERAL_QUARTZ_GILDED_BOOK_TYPE.blockEntityType,
                        pos, state
                    )
                }, QUARTZ_GILDED_BOOK).build()
            )
            val GENERAL_STURDY_OBSIDIAN_GILDED_BOOK_TYPE: BlockEntityType<GeneralBookBlockEntity> = registryEntitySelf(
                ::STURDY_OBSIDIAN_GILDED_BOOK,
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    GeneralBookBlockEntity(
                        ParamEntityTypes.GENERAL_STURDY_OBSIDIAN_GILDED_BOOK_TYPE.blockEntityType,
                        pos, state
                    )
                }, STURDY_OBSIDIAN_GILDED_BOOK).build()
            )
            val GENERAL_QUARTZ_LEATHER_BOOK_TYPE: BlockEntityType<GeneralBookBlockEntity> = registryEntitySelf(
                ::QUARTZ_LEATHER_BOOK,
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    GeneralBookBlockEntity(
                        ParamEntityTypes.GENERAL_QUARTZ_LEATHER_BOOK_TYPE.blockEntityType,
                        pos, state
                    )
                }, QUARTZ_LEATHER_BOOK).build()
            )

            //large books
            val LARGE_MAJESTIC_GILDED_BOOK_TYPE: BlockEntityType<LargeBookBlockEntity> = registryEntitySelf(
                ::MAJESTIC_GILDED_BOOK,
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    LargeBookBlockEntity(
                        ParamEntityTypes.LARGE_MAJESTIC_GILDED_BOOK_TYPE.blockEntityType,
                        pos, state
                    )
                }, MAJESTIC_GILDED_BOOK).build()
            )
            val LARGE_QUARTZ_RUBY_ENCRUSTED_BOOK_TYPE: BlockEntityType<LargeBookBlockEntity> = registryEntitySelf(
                ::QUARTZ_RUBY_ENCRUSTED_BOOK,
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    LargeBookBlockEntity(
                        ParamEntityTypes.LARGE_QUARTZ_RUBY_ENCRUSTED_BOOK_TYPE.blockEntityType,
                        pos, state
                    )
                }, QUARTZ_RUBY_ENCRUSTED_BOOK).build()
            )
            val LARGE_RUBY_GILDED_BOOK_TYPE: BlockEntityType<LargeBookBlockEntity> = registryEntitySelf(
                ::RUBY_GILDED_BOOK,
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    LargeBookBlockEntity(
                        ParamEntityTypes.LARGE_RUBY_GILDED_BOOK_TYPE.blockEntityType,
                        pos, state
                    )
                }, RUBY_GILDED_BOOK).build()
            )
            val LARGE_SAPPHIRE_GILDED_BOOK_TYPE: BlockEntityType<LargeBookBlockEntity> = registryEntitySelf(
                ::SAPPHIRE_GILDED_BOOK,
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    LargeBookBlockEntity(
                        ParamEntityTypes.LARGE_SAPPHIRE_GILDED_BOOK_TYPE.blockEntityType,
                        pos, state
                    )
                }, SAPPHIRE_GILDED_BOOK).build()
            )

            //light books
            val LIGHT_GOLDEN_BOUGH_FOREST_BOOK_TYPE: BlockEntityType<LightBookBlockEntity> = registryEntitySelf(
                ::GOLDEN_BOUGH_FOREST_BOOK,
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    LightBookBlockEntity(
                        ParamEntityTypes.LIGHT_GOLDEN_BOUGH_FOREST_BOOK_TYPE.blockEntityType,
                        pos, state
                    )

                }, GOLDEN_BOUGH_FOREST_BOOK).build()
            )
            val LIGHT_CRIMSON_FLAME_GILDED_BOOK_TYPE: BlockEntityType<LightBookBlockEntity> = registryEntitySelf(
                ::CRIMSON_FLAME_GILDED_BOOK,
                FabricBlockEntityTypeBuilder.create({ pos, state ->
                    LightBookBlockEntity(
                        ParamEntityTypes.LIGHT_CRIMSON_FLAME_GILDED_BOOK_TYPE.blockEntityType,
                        pos, state
                    )

                }, CRIMSON_FLAME_GILDED_BOOK).build()
            )

            enum class ParamEntityTypes(val blockEntityType: BlockEntityType<*>) {
                JOURNAL_CLASSIC_JOURNAL_BOOK_TYPE(EntityTypes.JOURNAL_CLASSIC_JOURNAL_BOOK_TYPE),
                GENERAL_REFINED_LEATHER_BOOK_TYPE(EntityTypes.GENERAL_REFINED_LEATHER_BOOK_TYPE),
                GENERAL_LUXURIOUS_GILDED_BOOK_TYPE(EntityTypes.GENERAL_LUXURIOUS_GILDED_BOOK_TYPE),
                GENERAL_QUARTZ_GILDED_BOOK_TYPE(EntityTypes.GENERAL_QUARTZ_GILDED_BOOK_TYPE),
                GENERAL_STURDY_OBSIDIAN_GILDED_BOOK_TYPE(EntityTypes.GENERAL_STURDY_OBSIDIAN_GILDED_BOOK_TYPE),
                GENERAL_QUARTZ_LEATHER_BOOK_TYPE(EntityTypes.GENERAL_QUARTZ_LEATHER_BOOK_TYPE),
                LARGE_MAJESTIC_GILDED_BOOK_TYPE(EntityTypes.LARGE_MAJESTIC_GILDED_BOOK_TYPE),
                LARGE_QUARTZ_RUBY_ENCRUSTED_BOOK_TYPE(EntityTypes.LARGE_QUARTZ_RUBY_ENCRUSTED_BOOK_TYPE),
                LARGE_RUBY_GILDED_BOOK_TYPE(EntityTypes.LARGE_RUBY_GILDED_BOOK_TYPE),
                LARGE_SAPPHIRE_GILDED_BOOK_TYPE(EntityTypes.LARGE_SAPPHIRE_GILDED_BOOK_TYPE),
                LIGHT_GOLDEN_BOUGH_FOREST_BOOK_TYPE(EntityTypes.LIGHT_GOLDEN_BOUGH_FOREST_BOOK_TYPE),
                LIGHT_CRIMSON_FLAME_GILDED_BOOK_TYPE(EntityTypes.LIGHT_CRIMSON_FLAME_GILDED_BOOK_TYPE)
            }
        }

    }

}