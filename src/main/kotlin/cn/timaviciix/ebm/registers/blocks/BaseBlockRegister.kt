/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  01:09
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks

import cn.timaviciix.ebm.util.GlobalData
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.DyeColor
import net.minecraft.util.Identifier
import kotlin.reflect.KProperty
import kotlin.reflect.full.createInstance

interface BaseBlockRegister {

    companion object {
        inline fun <reified T : Block> registrySelf(property: KProperty<*>): T {

            val blockId = property.name.lowercase()
            val targetBlock = T::class.createInstance()

            registryBlockItem(targetBlock,blockId)

            return Registry.register(Registries.BLOCK, Identifier(GlobalData.MOD_ID, blockId), targetBlock)

        }

        //TODO
        fun registryBlockItem(block: Block,itemId:String){
            Registry.register(Registries.ITEM,Identifier(GlobalData.MOD_ID,itemId),BlockItem(block,FabricItemSettings()))
        }


    }


}