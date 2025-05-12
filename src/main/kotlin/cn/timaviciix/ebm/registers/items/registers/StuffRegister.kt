/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.stuff
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  13:15
 *@Description: 耗材注册
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.items.registers

import cn.timaviciix.ebm.item.stuff.CopierBody
import cn.timaviciix.ebm.item.stuff.InkBox
import cn.timaviciix.ebm.item.stuff.TonerCartridge
import cn.timaviciix.ebm.registers.items.ItemRegistryHandler.Companion.registrySelf
import io.wispforest.owo.registration.reflect.ItemRegistryContainer

class StuffRegister : ItemRegistryContainer {

    companion object {
        val COPIER_BODY: CopierBody = registrySelf(::COPIER_BODY)
        val TONER_CARTRIDGE: TonerCartridge = registrySelf(::TONER_CARTRIDGE)
        val INK_BOX: InkBox = registrySelf(::INK_BOX)

    }

}