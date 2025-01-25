/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.blocks
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-25  07:43
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks

import cn.timaviciix.ebm.block.blockentitys.others.BlueCatPendantBlockEntity
import cn.timaviciix.ebm.block.others.BlueCatPendantBlock
import cn.timaviciix.ebm.block.others.EuropeanStyleCeramicTeaSetBlock
import cn.timaviciix.ebm.registers.blocks.BaseBlockRegister.Companion.registryEntitySelf
import cn.timaviciix.ebm.registers.blocks.BaseBlockRegister.Companion.registrySelf
import cn.timaviciix.ebm.util.GlobalData
import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer
import io.wispforest.owo.registration.reflect.BlockRegistryContainer
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

class OtherBlocksRegister : BlockRegistryContainer {

    companion object {
        val EUROPEAN_STYLE_CERAMIC_TEA_SET: EuropeanStyleCeramicTeaSetBlock =
            registrySelf(::EUROPEAN_STYLE_CERAMIC_TEA_SET, 0xf8f3d4)

    }


}