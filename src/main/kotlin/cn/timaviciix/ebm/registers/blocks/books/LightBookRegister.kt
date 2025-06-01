/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.blocks.books
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  15:30
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks.books

import cn.timaviciix.ebm.block.blockentitys.bookentitys.LightBookBlockEntity
import cn.timaviciix.ebm.block.books.lightbooks.CrimsonFlameGildedBookBlock
import cn.timaviciix.ebm.block.books.lightbooks.GoldenBoughForestBookBlock
import cn.timaviciix.ebm.registers.blocks.BlockRegistryHandler
import cn.timaviciix.ebm.registers.blocks.books.components.BookRegistrySupplier
import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer
import io.wispforest.owo.registration.reflect.BlockRegistryContainer
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntityType

class LightBookRegister() : BlockRegistryContainer {

    companion object : BookRegistrySupplier() {
        override val nameColor: Int = 0x46cdcf
        override val maxPage: Int = 500
        override val typeCode: Int = 2

        val GOLDEN_BOUGH_FOREST_BOOK: GoldenBoughForestBookBlock = registry(::GOLDEN_BOUGH_FOREST_BOOK)
        val CRIMSON_FLAME_GILDED_BOOK: CrimsonFlameGildedBookBlock = registry(::CRIMSON_FLAME_GILDED_BOOK)

    }

    class EntityTypes() : BlockEntityRegistryContainer {

        companion object {

            //light books
            val LIGHT_GOLDEN_BOUGH_FOREST_BOOK_TYPE: BlockEntityType<LightBookBlockEntity> =
                BlockRegistryHandler.registryEntitySelf(
                    ::GOLDEN_BOUGH_FOREST_BOOK,
                    FabricBlockEntityTypeBuilder.create({ pos, state ->
                        LightBookBlockEntity(
                            ParamEntityTypes.LIGHT_GOLDEN_BOUGH_FOREST_BOOK_TYPE.blockEntityType,
                            pos, state
                        )

                    }, GOLDEN_BOUGH_FOREST_BOOK).build()
                )
            val LIGHT_CRIMSON_FLAME_GILDED_BOOK_TYPE: BlockEntityType<LightBookBlockEntity> =
                BlockRegistryHandler.registryEntitySelf(
                    ::CRIMSON_FLAME_GILDED_BOOK,
                    FabricBlockEntityTypeBuilder.create({ pos, state ->
                        LightBookBlockEntity(
                            ParamEntityTypes.LIGHT_CRIMSON_FLAME_GILDED_BOOK_TYPE.blockEntityType,
                            pos, state
                        )

                    }, CRIMSON_FLAME_GILDED_BOOK).build()
                )

            enum class ParamEntityTypes(val blockEntityType: BlockEntityType<*>) {
                LIGHT_GOLDEN_BOUGH_FOREST_BOOK_TYPE(EntityTypes.LIGHT_GOLDEN_BOUGH_FOREST_BOOK_TYPE),
                LIGHT_CRIMSON_FLAME_GILDED_BOOK_TYPE(EntityTypes.LIGHT_CRIMSON_FLAME_GILDED_BOOK_TYPE)
            }
        }

    }

}