/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.network
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-30  00:07
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.network

import cn.timaviciix.ebm.item.blockitems.BaseBlockItem
import net.fabricmc.fabric.api.event.player.UseItemCallback
import net.minecraft.util.TypedActionResult

object Events {

    fun registryAll() {

        registryBookReadingState()

    }

    private fun registryBookReadingState() {

        UseItemCallback.EVENT.register(UseItemCallback { playerEntity,_, hand ->


            val itemStack = playerEntity.getStackInHand(hand)
            val item = itemStack.item

            if (item is BaseBlockItem) {
                if (item.itemClassify == BaseBlockItem.Companion.BlockItemClassify.Books) {
                    Packets.sendReadingPlayerUUid(playerEntity)
                    return@UseItemCallback TypedActionResult.success(itemStack)
                }
                return@UseItemCallback TypedActionResult.pass(itemStack)
            }
            return@UseItemCallback TypedActionResult.pass(itemStack)

        })

    }

}