/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.others
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-25  23:47
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.others

import cn.timaviciix.ebm.block.blockentitys.others.BlueCatPendantBlockEntity
import cn.timaviciix.ebm.block.others.BlueCatPendantBlock
import cn.timaviciix.ebm.registers.blocks.BlockRegistryHandler
import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer
import io.wispforest.owo.registration.reflect.BlockRegistryContainer
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.BlockItem


//we need /*BaseBlockRegister*/ to registry block           this guy↓
class AnimationIBsRegister() : BlockRegistryContainer {
    companion object {
        //manual Registry
        val BLUE_CAT_PENDANT: BlueCatPendantBlock = BlockRegistryHandler.registrySelf(::BLUE_CAT_PENDANT,0x3f72af)

    }

    class EntityTypes() : BlockEntityRegistryContainer {
        companion object {
            val BLUE_CAT_PENDANT_ANIME_ENTITY_TYPE: BlockEntityType<BlueCatPendantBlockEntity> =
                BlockRegistryHandler.registryEntitySelf(
                    ::BLUE_CAT_PENDANT,
                    FabricBlockEntityTypeBuilder.create(
                        ::BlueCatPendantBlockEntity,
                        BLUE_CAT_PENDANT
                    ).build()
                )
        }
    }

}