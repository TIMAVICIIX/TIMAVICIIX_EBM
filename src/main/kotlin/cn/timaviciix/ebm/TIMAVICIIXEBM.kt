package cn.timaviciix.ebm

import cn.timaviciix.ebm.registers.blocks.BookRegister
import cn.timaviciix.ebm.registers.blocks.CopierRegister
import cn.timaviciix.ebm.registers.items.BookcaseRegister
import cn.timaviciix.ebm.registers.items.OtherItemRegister
import cn.timaviciix.ebm.registers.items.StuffRegister
import cn.timaviciix.ebm.registers.others.SoundRegister
import cn.timaviciix.ebm.tooltip.ToolTipBus
import cn.timaviciix.ebm.util.GlobalData
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object TIMAVICIIXEBM : ModInitializer {

    private val logger = LoggerFactory.getLogger(GlobalData.MOD_ID)

    //use sort registryFeather
    class RegistryPair(val registryOperation: () -> Unit, val sort: Int)

    private val mutableRegistryPairList: MutableList<RegistryPair> = mutableListOf()


    override fun onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        logger.info("Welcome back TIMAVICIIX's EBM")

        //OWO lib cant cast kotlin construction and object class ?
        //@Imp:Resolved !!!

        //item registers
        //@Imp: Will Resolve it then!
        //FieldRegistrationHandler.register(WorkTablesRegister::class.java, GlobalData.MOD_ID, false)

        //@Imp:Books also is Blocks!!! use block entity instead
        //FieldRegistrationHandler.register(BookRegister::class.java, GlobalData.MOD_ID, false)


        //Use New Registry Method by registry sorted
        addRegistrySortPair(0) {
            //Sound Register
            SoundRegister.soundRegistryInterface()
            FieldRegistrationHandler.register(StuffRegister::class.java, GlobalData.MOD_ID, false)
            FieldRegistrationHandler.register(OtherItemRegister::class.java, GlobalData.MOD_ID, false)
        }

        addRegistrySortPair(1) {
            //block registers
            FieldRegistrationHandler.register(CopierRegister::class.java, GlobalData.MOD_ID, false)
            FieldRegistrationHandler.register(CopierRegister.EntityTypes::class.java, GlobalData.MOD_ID, false)
        }

        addRegistrySortPair(3) {
            FieldRegistrationHandler.register(BookcaseRegister::class.java, GlobalData.MOD_ID, false)
            //FieldRegistrationHandler.register(OtherItemRegister::class.java, GlobalData.MOD_ID, false)
        }

        addRegistrySortPair(2) {
            FieldRegistrationHandler.register(BookRegister::class.java, GlobalData.MOD_ID, false)
            FieldRegistrationHandler.register(BookRegister.EntityTypes::class.java, GlobalData.MOD_ID, false)
        }

        launchAutoRegistry()

        //ToolTip Init
        ToolTipBus.initAllTooltip()

        //buildItemGroup
        EBMItemGroup(null, null).buildAndInitItemGroup()


    }

    private fun addRegistrySortPair(sort: Int = 0, registryOperation: () -> Unit) {
        mutableRegistryPairList.add(RegistryPair(registryOperation, sort))
    }

    private fun launchAutoRegistry() {
        mutableRegistryPairList.sortBy { it.sort }

        for (consist in mutableRegistryPairList) {
            consist.registryOperation()
        }
    }


}