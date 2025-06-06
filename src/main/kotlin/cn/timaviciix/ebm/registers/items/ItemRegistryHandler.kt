/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  01:09
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.items

import cn.timaviciix.ebm.item.BaseItem
import cn.timaviciix.ebm.tooltip.ItemsTooltip
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.createInstance

interface ItemRegistryHandler {

    companion object {

        @Deprecated("you can use new RegistryMethod to do this ↓ ")
        fun <T : BaseItem> registrySelf(registryConsist: Pair<String, KClass<T>>): T {
            val name = registryConsist.first
            val context = registryConsist.second.createInstance()

            return Registry.register(Registries.ITEM, Identifier(GlobalData.MOD_ID, name), context)
        }

        inline fun <reified T : Item> registrySelf(
            property: KProperty<*>,
            idOperation: (itemInfoConsist: Pair<Item, String>) -> Unit = { ItemsTooltip.tooltipItemBusList.add(it) }
        ): T {

            val itemId = property.name.lowercase()
            val targetItem = T::class.createInstance()

            val registryItem = Registry.register(Registries.ITEM, Identifier(GlobalData.MOD_ID, itemId), targetItem)

            idOperation(Pair(registryItem, itemId))

            return registryItem
        }

        inline fun <reified T : ArmorItem> registryArmorSelf(
            property: KProperty<*>,
            armorEntity: T,
            itemInfoConsist: Pair<Item, String> = Pair(armorEntity, property.name.lowercase()),
            idOperation: (itemInfoConsist: Pair<Item, String>) -> Unit = { ItemsTooltip.tooltipItemBusList.add(it) }
        ): T {

            idOperation(itemInfoConsist)

            return Registry.register(
                Registries.ITEM,
                Identifier(GlobalData.MOD_ID, property.name.lowercase()),
                armorEntity
            )

        }
    }


}