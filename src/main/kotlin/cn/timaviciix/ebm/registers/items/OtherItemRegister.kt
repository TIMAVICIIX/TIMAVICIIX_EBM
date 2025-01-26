/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.others
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  13:05
 *@Description: 其他物品注册器
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.items

import cn.timaviciix.ebm.item.others.FeatherDuster
import cn.timaviciix.ebm.item.others.InvertedFeatherDuster
import cn.timaviciix.ebm.registers.items.BaseItemRegister.Companion.registrySelf
import io.wispforest.owo.registration.reflect.ItemRegistryContainer
import net.minecraft.item.Item

class OtherItemRegister : ItemRegistryContainer {

    companion object {
        val itemConsistList:MutableList<Pair<Item,String>> = mutableListOf()

        val INVERTED_FEATHER_DUSTER: InvertedFeatherDuster = registrySelf(::INVERTED_FEATHER_DUSTER){
            itemConsistList.add(it)
        }
        val FEATHER_DUSTER: FeatherDuster = registrySelf(::FEATHER_DUSTER){
            itemConsistList.add(it)
        }

    }

}