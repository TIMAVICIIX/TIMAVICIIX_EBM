/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.copiers
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  01:08
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.copiers

import io.wispforest.owo.registration.reflect.ItemRegistryContainer
import cn.timaviciix.ebm.item.BaseRegister.Companion.registrySelf

class CopierRegister:ItemRegistryContainer {

    companion object{

        val VERTICAL_COPIER:VerticalCopier = registrySelf(::VERTICAL_COPIER)
        val PORTABLE_COPIER:PortableCopier = registrySelf(::PORTABLE_COPIER)

    }

}