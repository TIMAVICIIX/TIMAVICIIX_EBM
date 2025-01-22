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
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.util.ActionResult
import net.minecraft.util.Identifier
import kotlin.reflect.KProperty
import kotlin.reflect.full.createInstance


interface BaseBlockRegister {

    companion object {


        /**
         * @param needSneaking Check Player Sneaking or not when placed block
         */
        fun generateBlockItem(
            nameColor: Int,
            block: Block,
            identifier: Identifier,
            operation: (BlockItem.() -> Unit) = {},
            needSneaking: Boolean,
        ) {

            val targetBlockItem = if (needSneaking) {
                object : BlockItem(
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

                    override fun place(context: ItemPlacementContext?): ActionResult {
                        val player = context?.player
                        if (player != null && !player.isSneaking) {
                            return ActionResult.FAIL
                        }
                        return super.place(context)
                    }
                }
            } else {
                object : BlockItem(
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

        inline fun <reified T : Block> registrySelf(
            property: KProperty<*>,
            nameColor: Int = 0xeeeeee,
            needSneaking: Boolean = false
        ): T {

            val blockId = property.name.lowercase()
            val targetBlock = T::class.createInstance()

            generateBlockItem(
                nameColor = nameColor,
                block = targetBlock,
                identifier = Identifier(GlobalData.MOD_ID, blockId),
                needSneaking = needSneaking
            )

            return Registry.register(Registries.BLOCK, Identifier(GlobalData.MOD_ID, blockId), targetBlock)

        }

    }


}