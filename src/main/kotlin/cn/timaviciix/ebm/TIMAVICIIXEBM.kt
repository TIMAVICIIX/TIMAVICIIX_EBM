package cn.timaviciix.ebm

import cn.timaviciix.ebm.registers.blocks.CopierRegister
import cn.timaviciix.ebm.registers.items.BookRegister
import cn.timaviciix.ebm.registers.items.OtherItemRegister
import cn.timaviciix.ebm.registers.items.StuffRegister
import cn.timaviciix.ebm.registers.items.WorkTablesRegister
import cn.timaviciix.ebm.tooltip.ToolTipBus
import cn.timaviciix.ebm.util.GlobalData
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object TIMAVICIIXEBM : ModInitializer {

    private val logger = LoggerFactory.getLogger(GlobalData.MOD_ID)

    override fun onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        logger.info("Welcome back TIMAVICIIX's EBM")

        //OWO lib cant cast kotlin construction and object class ?
        //@Imp:Resolved !!!

        //item registers
        FieldRegistrationHandler.register(WorkTablesRegister::class.java, GlobalData.MOD_ID, false)
        //FieldRegistrationHandler.register(CopierRegister::class.java,GlobalData.MOD_ID,false)
        FieldRegistrationHandler.register(StuffRegister::class.java, GlobalData.MOD_ID, false)
        FieldRegistrationHandler.register(BookRegister::class.java, GlobalData.MOD_ID, false)
        FieldRegistrationHandler.register(OtherItemRegister::class.java, GlobalData.MOD_ID, false)

        //block registers
        FieldRegistrationHandler.register(CopierRegister.EntityTypes::class.java, GlobalData.MOD_ID, false)
        FieldRegistrationHandler.register(CopierRegister::class.java, GlobalData.MOD_ID, false)



        //ToolTip Init
        ToolTipBus.initAllTooltip()

        //buildItemGroup
        EBMItemGroup(null, null).buildAndInitItemGroup()


    }


}