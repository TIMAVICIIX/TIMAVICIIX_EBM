/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.copiers
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  18:02
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks

import cn.timaviciix.ebm.block.blockentitys.copierentitys.PortableCopierBlockEntity
import cn.timaviciix.ebm.block.blockentitys.copierentitys.VerticalCopierBlockEntity
import cn.timaviciix.ebm.block.copiers.PortableCopierBlock
import cn.timaviciix.ebm.block.copiers.VerticalCopierBlock
import cn.timaviciix.ebm.registers.blocks.BaseBlockRegister.Companion.registryEntitySelf
import cn.timaviciix.ebm.registers.blocks.BaseBlockRegister.Companion.registrySelf
import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer
import io.wispforest.owo.registration.reflect.BlockRegistryContainer
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.BlockItem

//@Imp:Necessary Construct
class CopierRegister() : BlockRegistryContainer {

    companion object {
        val blockItemsConsist: MutableList<Pair<BlockItem, String>> = mutableListOf()

        val PORTABLE_COPIER: PortableCopierBlock = registrySelf(::PORTABLE_COPIER, {
            blockItemsConsist.add(it)
        }, 0xa2d5f2)
        val VERTICAL_COPIER: VerticalCopierBlock = registrySelf(::VERTICAL_COPIER, {
            blockItemsConsist.add(it)
        }, 0xc9d6df)
    }

//    override fun createBlockItem(block: Block?, identifier: String?): BlockItem {
//        return if (block != null && identifier != null) {
//            generateBlockItem( block, Identifier(identifier), null)
//        } else if (block != null) {
//            BlockItem(block, GlobalData.OWO_ITEM_SIGNAL_SETTING)
//        } else {
//            throw NullPointerException("Don't have Block Item to Create!!!")
//        }
//    }


    class EntityTypes() : BlockEntityRegistryContainer {
        companion object {

            val PORTABLE_COPIER_TYPE: BlockEntityType<PortableCopierBlockEntity> = registryEntitySelf(
                ::PORTABLE_COPIER,
                FabricBlockEntityTypeBuilder.create(::PortableCopierBlockEntity, PORTABLE_COPIER).build()
            )

            val VERTICAL_COPIER_TYPE: BlockEntityType<VerticalCopierBlockEntity> = registryEntitySelf(
                ::VERTICAL_COPIER,
                FabricBlockEntityTypeBuilder.create(::VerticalCopierBlockEntity, VERTICAL_COPIER).build()
            )

        }
    }

}