/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.stuff
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  13:15
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.stuff

import io.wispforest.owo.registration.reflect.ItemRegistryContainer
import cn.timaviciix.ebm.item.BaseRegister.Companion.registrySelf

class StuffRegister:ItemRegistryContainer {

    companion object{

        val COPIER_BODY:CopierBody = registrySelf(::COPIER_BODY)
        val TONER_CARTRIDGE:TonerCartridge = registrySelf(::TONER_CARTRIDGE)
        val INK_BOX:InkBox = registrySelf(::INK_BOX)

    }

}