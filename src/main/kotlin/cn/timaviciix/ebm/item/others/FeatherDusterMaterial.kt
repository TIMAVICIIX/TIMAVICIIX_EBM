/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.others
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-25  03:16
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.others

import net.minecraft.item.Items
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient

class FeatherDusterMaterial():ToolMaterial {

    override fun getDurability(): Int {
        return 455
    }

    override fun getMiningSpeedMultiplier(): Float {
        return 5.0f
    }

    override fun getAttackDamage(): Float {
        return 0f
    }

    override fun getMiningLevel(): Int {
        return 3
    }

    override fun getEnchantability(): Int {
        return 22
    }

    override fun getRepairIngredient(): Ingredient {
        return Ingredient.ofItems(Items.FEATHER,Items.STICK)
    }
}