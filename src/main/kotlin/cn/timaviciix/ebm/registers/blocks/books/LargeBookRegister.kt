/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.blocks.books
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  15:14
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks.books

import cn.timaviciix.ebm.block.blockentitys.bookentitys.LargeBookBlockEntity
import cn.timaviciix.ebm.block.books.largebooks.MajesticGildedBookBlock
import cn.timaviciix.ebm.block.books.largebooks.QuartzRubyEncrustedBookBlock
import cn.timaviciix.ebm.block.books.largebooks.RubyGildedBookBlock
import cn.timaviciix.ebm.block.books.largebooks.SapphireGildedBookBlock
import cn.timaviciix.ebm.registers.blocks.BlockRegistryHandler
import cn.timaviciix.ebm.registers.blocks.books.delegates.LargeBookDelegateRegister
import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer
import io.wispforest.owo.registration.reflect.BlockRegistryContainer
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntityType

class LargeBookRegister() : BlockRegistryContainer {

    companion object {
        val MAJESTIC_GILDED_BOOK by LargeBookDelegateRegister(::MajesticGildedBookBlock)
        val QUARTZ_RUBY_ENCRUSTED_BOOK by LargeBookDelegateRegister(::QuartzRubyEncrustedBookBlock)
        val RUBY_GILDED_BOOK by LargeBookDelegateRegister(::RubyGildedBookBlock)
        val SAPPHIRE_GILDED_BOOK by LargeBookDelegateRegister(::SapphireGildedBookBlock)
    }

    class EntityTypes() : BlockEntityRegistryContainer {

        companion object {

            val LARGE_MAJESTIC_GILDED_BOOK_TYPE: BlockEntityType<LargeBookBlockEntity> =
                BlockRegistryHandler.registryEntitySelf(
                    ::MAJESTIC_GILDED_BOOK,
                    FabricBlockEntityTypeBuilder.create({ pos, state ->
                        LargeBookBlockEntity(
                            ParamEntityTypes.LARGE_MAJESTIC_GILDED_BOOK_TYPE.blockEntityType,
                            pos, state
                        )
                    }, MAJESTIC_GILDED_BOOK).build()
                )
            val LARGE_QUARTZ_RUBY_ENCRUSTED_BOOK_TYPE: BlockEntityType<LargeBookBlockEntity> =
                BlockRegistryHandler.registryEntitySelf(
                    ::QUARTZ_RUBY_ENCRUSTED_BOOK,
                    FabricBlockEntityTypeBuilder.create({ pos, state ->
                        LargeBookBlockEntity(
                            ParamEntityTypes.LARGE_QUARTZ_RUBY_ENCRUSTED_BOOK_TYPE.blockEntityType,
                            pos, state
                        )
                    }, QUARTZ_RUBY_ENCRUSTED_BOOK).build()
                )
            val LARGE_RUBY_GILDED_BOOK_TYPE: BlockEntityType<LargeBookBlockEntity> =
                BlockRegistryHandler.registryEntitySelf(
                    ::RUBY_GILDED_BOOK,
                    FabricBlockEntityTypeBuilder.create({ pos, state ->
                        LargeBookBlockEntity(
                            ParamEntityTypes.LARGE_RUBY_GILDED_BOOK_TYPE.blockEntityType,
                            pos, state
                        )
                    }, RUBY_GILDED_BOOK).build()
                )
            val LARGE_SAPPHIRE_GILDED_BOOK_TYPE: BlockEntityType<LargeBookBlockEntity> =
                BlockRegistryHandler.registryEntitySelf(
                    ::SAPPHIRE_GILDED_BOOK,
                    FabricBlockEntityTypeBuilder.create({ pos, state ->
                        LargeBookBlockEntity(
                            ParamEntityTypes.LARGE_SAPPHIRE_GILDED_BOOK_TYPE.blockEntityType,
                            pos, state
                        )
                    }, SAPPHIRE_GILDED_BOOK).build()
                )

            enum class ParamEntityTypes(val blockEntityType: BlockEntityType<*>) {
                LARGE_MAJESTIC_GILDED_BOOK_TYPE(EntityTypes.LARGE_MAJESTIC_GILDED_BOOK_TYPE),
                LARGE_QUARTZ_RUBY_ENCRUSTED_BOOK_TYPE(EntityTypes.LARGE_QUARTZ_RUBY_ENCRUSTED_BOOK_TYPE),
                LARGE_RUBY_GILDED_BOOK_TYPE(EntityTypes.LARGE_RUBY_GILDED_BOOK_TYPE),
                LARGE_SAPPHIRE_GILDED_BOOK_TYPE(EntityTypes.LARGE_SAPPHIRE_GILDED_BOOK_TYPE)
            }

        }

    }

}