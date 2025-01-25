/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-24  23:54
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.item.ToolMaterials
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.text.TextColor

open class BaseSwordItem(
    nameColor: Int,
    attackDamage: Int,
    settings: Settings,
    toolMaterial: ToolMaterial = ToolMaterials.DIAMOND,
    speed: Float = -2.4f
) : SwordItem(toolMaterial, attackDamage, speed, settings) {

    private var nameStyle: Style = Style.EMPTY.withColor(TextColor.fromRgb(nameColor))

    override fun getName(): Text {
        return super.getName().copy().setStyle(nameStyle)
    }

    override fun getName(stack: ItemStack?): Text {
        return super.getName(stack).copy().setStyle(nameStyle)
    }

}