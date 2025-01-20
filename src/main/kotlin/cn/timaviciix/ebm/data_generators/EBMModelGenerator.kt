/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_generators
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  12:43
 *@Description: Model数据据生成器
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_generators

import cn.timaviciix.ebm.registers.items.CopierRegister
import cn.timaviciix.ebm.registers.items.OtherItemRegister
import cn.timaviciix.ebm.registers.items.StuffRegister
import cn.timaviciix.ebm.registers.items.WorkTablesRegister
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models

class EBMModelGenerator(output: FabricDataOutput) : FabricModelProvider(output) {

    override fun generateBlockStateModels(p0: BlockStateModelGenerator?) {
        p0.apply {

        }
    }

    override fun generateItemModels(p0: ItemModelGenerator?) {
        p0?.apply {

            register(CopierRegister.PORTABLE_COPIER, Models.GENERATED)
            register(CopierRegister.VERTICAL_COPIER, Models.GENERATED)

            register(OtherItemRegister.FEATHER_DUSTER, Models.GENERATED)
            register(OtherItemRegister.BLUE_CAT_PENDANT, Models.GENERATED)
            register(OtherItemRegister.EUROPEAN_STYLE_CERAMIC_TEA_SET, Models.GENERATED)

            register(StuffRegister.COPIER_BODY, Models.GENERATED)
            register(StuffRegister.TONER_CARTRIDGE, Models.GENERATED)
            register(StuffRegister.INK_BOX, Models.GENERATED)

            register(WorkTablesRegister.LIBRARIAN_DESK, Models.GENERATED)
            register(WorkTablesRegister.STAMPING_DESK, Models.GENERATED)
            register(WorkTablesRegister.BINDING_MACHINE, Models.GENERATED)

        }
    }
}