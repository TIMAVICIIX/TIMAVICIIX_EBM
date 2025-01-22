/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.copiers
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  01:08
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.items

import cn.timaviciix.ebm.item.copiers.PortableCopier
import cn.timaviciix.ebm.item.copiers.VerticalCopier
import io.wispforest.owo.registration.reflect.ItemRegistryContainer
import cn.timaviciix.ebm.registers.items.BaseItemRegister.Companion.registrySelf

@Deprecated("Testing!")
class CopierRegister:ItemRegistryContainer {

    companion object{

        val VERTICAL_COPIER: VerticalCopier = registrySelf(Companion::VERTICAL_COPIER)
        val PORTABLE_COPIER: PortableCopier = registrySelf(Companion::PORTABLE_COPIER)

    }

}