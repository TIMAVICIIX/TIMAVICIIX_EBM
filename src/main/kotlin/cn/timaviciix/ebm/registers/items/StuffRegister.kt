/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.stuff
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  13:15
 *@Description: 耗材注册
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.items

import cn.timaviciix.ebm.item.stuff.CopierBody
import cn.timaviciix.ebm.item.stuff.InkBox
import cn.timaviciix.ebm.item.stuff.TonerCartridge
import cn.timaviciix.ebm.registers.items.BaseItemRegister.Companion.registrySelf
import io.wispforest.owo.registration.reflect.ItemRegistryContainer
import net.minecraft.item.Item

class StuffRegister : ItemRegistryContainer {

    companion object {
        val itemConsistList: MutableList<Pair<Item, String>> = mutableListOf()

        val COPIER_BODY: CopierBody = registrySelf(Companion::COPIER_BODY) {
            itemConsistList.add(it)
        }
        val TONER_CARTRIDGE: TonerCartridge = registrySelf(Companion::TONER_CARTRIDGE) {
            itemConsistList.add(it)
        }
        val INK_BOX: InkBox = registrySelf(Companion::INK_BOX) {
            itemConsistList.add(it)
        }

    }

}