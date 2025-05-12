/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.items
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-29  01:51
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.items.registers

import cn.timaviciix.ebm.item.armors.PenguinArmorItem
import cn.timaviciix.ebm.registers.items.ItemRegistryHandler.Companion.registryArmorSelf
import cn.timaviciix.ebm.util.GlobalData
import io.wispforest.owo.registration.reflect.ItemRegistryContainer
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterials

class ArmorRegister : ItemRegistryContainer {

    companion object {

        val PENGUIN_HELMET: PenguinArmorItem = registryArmorSelf(
            Companion::PENGUIN_HELMET,
            PenguinArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.HELMET, GlobalData.OWO_ITEM_SIGNAL_SETTING)
        )
        val PENGUIN_CHESTPLATE: PenguinArmorItem = registryArmorSelf(
            Companion::PENGUIN_CHESTPLATE,
            PenguinArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, GlobalData.OWO_ITEM_SIGNAL_SETTING)
        )

        val PENGUIN_LEGGINGS: PenguinArmorItem = registryArmorSelf(
            Companion::PENGUIN_LEGGINGS,
            PenguinArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS, GlobalData.OWO_ITEM_SIGNAL_SETTING)
        )

        val PENGUIN_BOOTS: PenguinArmorItem = registryArmorSelf(
            Companion::PENGUIN_BOOTS,
            PenguinArmorItem(ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS, GlobalData.OWO_ITEM_SIGNAL_SETTING)
        )


    }

}