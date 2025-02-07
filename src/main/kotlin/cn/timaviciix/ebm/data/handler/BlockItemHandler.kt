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

interface BlockItemHandler {

    fun playOpenSounds(user: PlayerEntity)

    fun playUsingSounds(user: PlayerEntity)

    fun playCloseSounds(user: PlayerEntity)

    fun setScreen(user: PlayerEntity)

}