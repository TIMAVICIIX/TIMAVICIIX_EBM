/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.blockitems
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-27  17:48
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data.handler

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack

interface ScreenSetHandler {
    fun setScreen(user: PlayerEntity, stack: ItemStack)

}