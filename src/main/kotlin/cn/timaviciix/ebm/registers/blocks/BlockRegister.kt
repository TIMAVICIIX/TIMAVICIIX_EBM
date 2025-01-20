/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.copiers
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  18:02
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks

import cn.timaviciix.ebm.block.copiers.PortableCopierBlock
import cn.timaviciix.ebm.registers.blocks.BaseBlockRegister.Companion.registrySelf

object BlockRegister {


    val PORTABLE_COPIER_BLOCK: PortableCopierBlock = registrySelf(::PORTABLE_COPIER_BLOCK)

    fun blockRegistryInterface(){

    }

}