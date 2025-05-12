/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.blocks
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-25  07:43
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks.blocks

import cn.timaviciix.ebm.block.others.EuropeanStyleCeramicTeaSetBlock
import cn.timaviciix.ebm.registers.blocks.BlockRegistryHandler.registrySelf
import io.wispforest.owo.registration.reflect.BlockRegistryContainer

class OtherBlocksRegister() : BlockRegistryContainer {

    companion object {
        val EUROPEAN_STYLE_CERAMIC_TEA_SET: EuropeanStyleCeramicTeaSetBlock =
            registrySelf(::EUROPEAN_STYLE_CERAMIC_TEA_SET, 0xf8f3d4)

    }


}