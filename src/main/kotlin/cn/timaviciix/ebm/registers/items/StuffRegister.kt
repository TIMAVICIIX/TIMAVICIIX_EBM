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
import io.wispforest.owo.registration.reflect.ItemRegistryContainer
import cn.timaviciix.ebm.registers.items.BaseItemRegister.Companion.registrySelf

class StuffRegister:ItemRegistryContainer {

    companion object{

        val COPIER_BODY: CopierBody = registrySelf(Companion::COPIER_BODY)
        val TONER_CARTRIDGE: TonerCartridge = registrySelf(Companion::TONER_CARTRIDGE)
        val INK_BOX: InkBox = registrySelf(Companion::INK_BOX)

    }

}