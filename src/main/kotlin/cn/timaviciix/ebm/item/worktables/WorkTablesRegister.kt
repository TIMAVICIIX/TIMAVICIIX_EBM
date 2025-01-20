/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.worktables
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  13:26
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.worktables

import io.wispforest.owo.registration.reflect.ItemRegistryContainer
import cn.timaviciix.ebm.item.BaseRegister.Companion.registrySelf
class WorkTablesRegister:ItemRegistryContainer {

    companion object{

        val LIBRARIAN_DESK:LibrarianDesk = registrySelf(::LIBRARIAN_DESK)
        val STAMPING_DESK:StampingDesk = registrySelf(::STAMPING_DESK)
        val BINDING_MACHINE:BindingMachine = registrySelf(::BINDING_MACHINE)

    }

}