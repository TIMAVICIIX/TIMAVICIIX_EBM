/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.util
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-13  13:19
 *@Description: 该静态对象类容纳全局脚本数据，例如ModID
 *@Version: 1.0
 */

package cn.timaviciix.ebm.util

import cn.timaviciix.ebm.EBMItemGroup
import io.wispforest.owo.itemgroup.OwoItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.enums.Instrument
import net.minecraft.util.DyeColor
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object GlobalData {

    // Mod ID
    const val MOD_ID = "timaviciix_ebm"
    const val ITEM_GROUP_ID = "timaviciix_ebm_group"
    const val DESC_SUFFIX = "_desc"

    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

    //default Owo Setting
    val OWO_ITEM_BASE_64SETTING = OwoItemSettings().group(EBMItemGroup.EBM_ITEM_GROUP).maxCount(64)!!
    val OWO_ITEM_SIGNAL_SETTING = OwoItemSettings().group(EBMItemGroup.EBM_ITEM_GROUP).maxCount(1)!!

    fun getGeneralBlockSetting(
        mapColor: DyeColor = DyeColor.GRAY,
        instrument: Instrument = Instrument.BASEDRUM,
        opaque: Boolean = false
    ): FabricBlockSettings {

        return FabricBlockSettings.create().apply {
            collidable(true)
            mapColor(mapColor)
            instrument(instrument)
            strength(1.2F, 6.0F)
            if (!opaque) {
                nonOpaque()
            }
        }
    }


    //Item Group
    //@Imp:Deprecated
//    val EBM_ITEM_GROUP: OwoItemGroup = OwoItemGroup.builder(
//        Identifier(MOD_ID, "book")
//    ) {
//        Icon.of(ItemStack(BookRegister.REFINED_LEATHER_BOOK))
//    }.build()
}