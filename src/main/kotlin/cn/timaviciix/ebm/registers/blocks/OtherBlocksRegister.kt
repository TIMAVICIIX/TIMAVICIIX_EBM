/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.blocks
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-25  07:43
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks

import cn.timaviciix.ebm.block.others.EuropeanStyleCeramicTeaSet
import cn.timaviciix.ebm.registers.blocks.BaseBlockRegister.Companion.registrySelf
import io.wispforest.owo.registration.reflect.BlockRegistryContainer

class OtherBlocksRegister : BlockRegistryContainer {

    companion object {
        val EUROPEAN_STYLE_CERAMIC_TEA_SET: EuropeanStyleCeramicTeaSet =
            registrySelf(::EUROPEAN_STYLE_CERAMIC_TEA_SET, 0xf8f3d4)
    }

}