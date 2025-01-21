/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  01:09
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks

import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.util.Identifier
import kotlin.reflect.KProperty
import kotlin.reflect.full.createInstance


interface BaseBlockRegister {

    companion object {

        fun generateBlockItem(
            nameColor: Int,
            block: Block,
            identifier: Identifier,
            operation: (BlockItem.() -> Unit) = {}
        ) {

            val targetBlockItem = object : BlockItem(
                block,
                GlobalData.OWO_ITEM_SIGNAL_SETTING
            ) {
                val nameStyle: Style = Style.EMPTY.withColor(TextColor.fromRgb(nameColor))
                override fun getName(): Text {
                    return super.getName().copy().setStyle(nameStyle)
                }

                override fun getName(stack: ItemStack?): Text {
                    return super.getName(stack).copy().setStyle(nameStyle)
                }
            }

            operation.let {
                targetBlockItem.operation()
            }

            Registry.register(
                Registries.ITEM,
                identifier,
                targetBlockItem
            )
        }

        inline fun <reified T : Block> registrySelf(property: KProperty<*>,nameColor:Int=0xeeeeee): T {

            val blockId = property.name.lowercase()
            val targetBlock = T::class.createInstance()

            generateBlockItem(nameColor = nameColor, block = targetBlock, identifier = Identifier(GlobalData.MOD_ID, blockId))

            return Registry.register(Registries.BLOCK, Identifier(GlobalData.MOD_ID, blockId), targetBlock)

        }

    }


}