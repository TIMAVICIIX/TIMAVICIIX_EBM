/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  01:09
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks

import cn.timaviciix.ebm.item.blockitems.BaseBlockItem
import cn.timaviciix.ebm.util.GlobalData
import io.wispforest.owo.itemgroup.OwoItemSettings
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import kotlin.reflect.KProperty
import kotlin.reflect.full.createInstance


interface BaseBlockRegister {

    companion object {


        /**
         * @param needSneaking Check Player Sneaking or not when placed block
         * @param settings Note that this class generates a single-stack item by default
         */
        fun generateBlockItem(
            nameColor: Int,
            block: Block,
            identifier: Identifier,
            operation: (BlockItem.() -> Unit) = {},
            needSneaking: Boolean,
            settings: OwoItemSettings = GlobalData.OWO_ITEM_SIGNAL_SETTING,
            itemClassify:BaseBlockItem.Companion.BlockItemClassify = BaseBlockItem.Companion.BlockItemClassify.Unknown
        ): BaseBlockItem {

            //@Imp:Solve the abstraction problem of BaseBlockItem and represent BlockItem into multiple types

            val targetBlockItem =
                BaseBlockItem(block, settings, nameColor = nameColor, needSneakingPlace = needSneaking,itemClassify = itemClassify)

            operation.let {
                targetBlockItem.operation()
            }

            return Registry.register(
                Registries.ITEM,
                identifier,
                targetBlockItem
            )
        }

        //save an interface for animationItem
        inline fun <reified T : Block> registrySelf(
            property: KProperty<*>,
            blockItemOperation: (Pair<BlockItem, String>) -> Unit,
            nameColor: Int = 0xeeeeee,
            needSneaking: Boolean = false,
            itemClassify: BaseBlockItem.Companion.BlockItemClassify = BaseBlockItem.Companion.BlockItemClassify.Unknown
        ): T {

            val blockId = property.name.lowercase()
            val targetBlock = T::class.createInstance()

            val blockItemPart = generateBlockItem(
                nameColor = nameColor,
                block = targetBlock,
                identifier = Identifier(GlobalData.MOD_ID, blockId),
                needSneaking = needSneaking,
                itemClassify = itemClassify
            )

            blockItemOperation(Pair(blockItemPart, blockId))

            return Registry.register(Registries.BLOCK, Identifier(GlobalData.MOD_ID, blockId), targetBlock)

        }

        fun <T : BlockEntityType<*>> registryEntitySelf(
            property: KProperty<*>,
            targetEntityType: T
        ): T {

            val entityId = property.name.lowercase()

            return Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier(GlobalData.MOD_ID, entityId),
                targetEntityType
            )

        }

    }


}