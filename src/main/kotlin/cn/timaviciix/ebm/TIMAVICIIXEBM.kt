package cn.timaviciix.ebm

import cn.timaviciix.ebm.config.ConfigCreator
import cn.timaviciix.ebm.data.book.storage.io.StorageFolderCreator
import cn.timaviciix.ebm.network.Receiver
import cn.timaviciix.ebm.registers.blocks.BookRegister
import cn.timaviciix.ebm.registers.blocks.CopierRegister
import cn.timaviciix.ebm.registers.blocks.OtherBlocksRegister
import cn.timaviciix.ebm.registers.blocks.WorkTableRegister
import cn.timaviciix.ebm.registers.items.ArmorRegister
import cn.timaviciix.ebm.registers.items.BookcaseRegister
import cn.timaviciix.ebm.registers.items.OtherItemRegister
import cn.timaviciix.ebm.registers.items.StuffRegister
import cn.timaviciix.ebm.registers.others.AnimationIBsRegister
import cn.timaviciix.ebm.registers.others.SoundRegister
import cn.timaviciix.ebm.tooltip.ToolTipBus
import cn.timaviciix.ebm.util.GlobalData
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory
import software.bernie.geckolib.GeckoLib

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

        /**
         * step1:registry Animation Block
         * step2:registry Animation BlockEntity & BlockEntityType
         *
         * step3:Take an Animation Model（has resources from block_bench）
         * step4:Take an Animation Render
         * step5:registry this two guys 👆
         */

        //load Config And Create Data Storage Folder
        addRegistrySortPair(89) {
            ConfigCreator.loadConfig()
            StorageFolderCreator.createStorageFolder()
        }

        //Animation Blocks Register
        addRegistrySortPair(90) {
            FieldRegistrationHandler.register(AnimationIBsRegister::class.java, GlobalData.MOD_ID, false)
            FieldRegistrationHandler.register(AnimationIBsRegister.EntityTypes::class.java, GlobalData.MOD_ID, false)

        }

        addRegistrySortPair(91) {
            Receiver.registryServerReceiver()
        }

        addRegistrySortPair(92) {
            //Sound Register
            SoundRegister.soundRegistryInterface()
        }

        addRegistrySortPair(100) {
            FieldRegistrationHandler.register(StuffRegister::class.java, GlobalData.MOD_ID, false)
            FieldRegistrationHandler.register(OtherItemRegister::class.java, GlobalData.MOD_ID, false)
            FieldRegistrationHandler.register(OtherBlocksRegister::class.java, GlobalData.MOD_ID, false)
        }

        addRegistrySortPair(200) {
            //block registers
            FieldRegistrationHandler.register(CopierRegister::class.java, GlobalData.MOD_ID, false)
            FieldRegistrationHandler.register(CopierRegister.EntityTypes::class.java, GlobalData.MOD_ID, false)
        }

        addRegistrySortPair(300) {
            FieldRegistrationHandler.register(BookcaseRegister::class.java, GlobalData.MOD_ID, false)
            //FieldRegistrationHandler.register(OtherItemRegister::class.java, GlobalData.MOD_ID, false)
        }

        addRegistrySortPair(390) {
            FieldRegistrationHandler.register(ArmorRegister::class.java, GlobalData.MOD_ID, false)
        }

        addRegistrySortPair(400) {
            FieldRegistrationHandler.register(BookRegister::class.java, GlobalData.MOD_ID, false)
            FieldRegistrationHandler.register(BookRegister.EntityTypes::class.java, GlobalData.MOD_ID, false)
        }

        addRegistrySortPair(210) {
            FieldRegistrationHandler.register(WorkTableRegister::class.java, GlobalData.MOD_ID, false)
            FieldRegistrationHandler.register(
                WorkTableRegister.Companion.EntityTypes::class.java,
                GlobalData.MOD_ID,
                false
            )
        }

        launchAutoRegistry()

        //ToolTip Init
        ToolTipBus.initAllTooltip()

        //buildItemGroup
        EBMItemGroup(null, null).buildAndInitItemGroup()

        //Initialize Gecko
        GeckoLib.initialize()


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