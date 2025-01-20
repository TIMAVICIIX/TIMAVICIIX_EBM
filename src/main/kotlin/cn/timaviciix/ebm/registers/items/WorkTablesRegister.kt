/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.worktables
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  13:26
 *@Description: 工作台注册器
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.items

import cn.timaviciix.ebm.item.worktables.BindingMachine
import cn.timaviciix.ebm.item.worktables.LibrarianDesk
import cn.timaviciix.ebm.item.worktables.StampingDesk
import io.wispforest.owo.registration.reflect.ItemRegistryContainer
import cn.timaviciix.ebm.registers.items.BaseItemRegister.Companion.registrySelf
class WorkTablesRegister:ItemRegistryContainer {

    companion object{

        val LIBRARIAN_DESK: LibrarianDesk = registrySelf(Companion::LIBRARIAN_DESK)
        val STAMPING_DESK: StampingDesk = registrySelf(Companion::STAMPING_DESK)
        val BINDING_MACHINE: BindingMachine = registrySelf(Companion::BINDING_MACHINE)

    }

}