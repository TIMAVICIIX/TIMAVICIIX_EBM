/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  01:09
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks

import cn.timaviciix.ebm.block.books.BaseBookBlock
import cn.timaviciix.ebm.item.blockitems.BaseBlockItem
import cn.timaviciix.ebm.item.blockitems.BookBlockItem
import cn.timaviciix.ebm.tooltip.ItemsTooltip
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


object BlockRegistryHandler {


    /**
     * @param settings Note that this class generates a single-stack item by default
     */
    fun generateBlockItem(
        nameColor: Int,
        block: Block,
        identifier: Identifier,
        operation: (BlockItem.() -> Unit) = {},
        settings: OwoItemSettings = GlobalData.OWO_ITEM_SIGNAL_SETTING,
        itemClassify: BaseBlockItem.Companion.BlockItemClassify = BaseBlockItem.Companion.BlockItemClassify.Unknown
    ): BaseBlockItem {

        //@Imp:Solve the abstraction problem of BaseBlockItem and represent BlockItem into multiple types

        val targetBlockItem =
            BaseBlockItem(
                block,
                settings,
                nameColor = nameColor,
                itemClassify = itemClassify
            )


        operation.let {
            targetBlockItem.operation()
        }

        return Registry.register(
            Registries.ITEM,
            identifier,
            targetBlockItem
        )
    }

     private fun generateBlockItem(
        nameColor: Int,
        block: Block,
        identifier: Identifier,
        operation: (BlockItem.() -> Unit) = {},
        maxPage: Int,
        typeCode: Int,
        settings: OwoItemSettings = GlobalData.OWO_ITEM_SIGNAL_SETTING,
    ): BaseBlockItem {

        //@Imp:Solve the abstraction problem of BaseBlockItem and represent BlockItem into multiple types

        val targetBlockItem =
            BookBlockItem(
                block,
                settings,
                nameColor,
                typeCode,
                maxPage
            )

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
        nameColor: Int = 0xeeeeee,
        blockItemOperation: (Pair<BlockItem, String>) -> Unit = { ItemsTooltip.tooltipBlockBusList.add(it) },
        itemClassify: BaseBlockItem.Companion.BlockItemClassify = BaseBlockItem.Companion.BlockItemClassify.Unknown
    ): T {

        val blockId = property.name.lowercase()
        val targetBlock = T::class.createInstance()

        val blockItemPart = generateBlockItem(
            nameColor = nameColor,
            block = targetBlock,
            identifier = Identifier(GlobalData.MOD_ID, blockId),
            itemClassify = itemClassify
        )

        blockItemOperation(Pair(blockItemPart, blockId))

        return Registry.register(Registries.BLOCK, Identifier(GlobalData.MOD_ID, blockId), targetBlock)

    }

    fun <T: BaseBookBlock> registrySelf(
        id:String,
        block: T,
        blockItemOperation: (Pair<BlockItem, String>) -> Unit = { ItemsTooltip.tooltipBlockBusList.add(it) },
        nameColor: Int = 0xeeeeee,
        maxPage: Int,
        typeCode: Int
    ): T {

        val blockItemPart = generateBlockItem(
            nameColor = nameColor,
            block = block,
            identifier = Identifier(GlobalData.MOD_ID, id),
            maxPage = maxPage,
            typeCode = typeCode
        )

        blockItemOperation(Pair(blockItemPart, id))

        return Registry.register(Registries.BLOCK, Identifier(GlobalData.MOD_ID, id), block)

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


