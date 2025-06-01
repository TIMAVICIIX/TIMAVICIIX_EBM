/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.blocks
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-22  01:52
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks.books

import cn.timaviciix.ebm.util.GlobalData
import io.wispforest.owo.registration.reflect.BlockRegistryContainer
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler
import net.minecraft.item.BlockItem

class BookBusRegister() : BlockRegistryContainer {

    companion object {

        fun registryAllBook() {
            //JournalBook
            FieldRegistrationHandler.register(JournalBookRegister::class.java, GlobalData.MOD_ID, false)
            FieldRegistrationHandler.register(JournalBookRegister.EntityTypes::class.java, GlobalData.MOD_ID, false)

            //GeneralBook
            FieldRegistrationHandler.register(GeneralBookRegister::class.java, GlobalData.MOD_ID, false)
            FieldRegistrationHandler.register(GeneralBookRegister.EntityTypes::class.java, GlobalData.MOD_ID, false)

            //LargeBook
            FieldRegistrationHandler.register(LargeBookRegister::class.java, GlobalData.MOD_ID, false)
            FieldRegistrationHandler.register(LargeBookRegister.EntityTypes::class.java, GlobalData.MOD_ID, false)

            //LightBook
            FieldRegistrationHandler.register(LightBookRegister::class.java, GlobalData.MOD_ID, false)
            FieldRegistrationHandler.register(LightBookRegister.EntityTypes::class.java, GlobalData.MOD_ID, false)
        }
    }


}