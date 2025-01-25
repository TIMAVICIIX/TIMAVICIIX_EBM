/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.others
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-20  13:03
 *@Description: 鸡毛掸子
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.others

import cn.timaviciix.ebm.item.BaseSwordItem
import cn.timaviciix.ebm.registers.others.SoundRegister
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class InvertedFeatherDuster :
    BaseSwordItem(0xf73859, 0, GlobalData.OWO_ITEM_SIGNAL_SETTING, toolMaterial = FeatherDusterMaterial()) {

    val logger: Logger = LoggerFactory.getLogger(GlobalData.MOD_ID)

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {

        user?.swingHand(hand, true)
        if (world?.isClient == true) {
            world.playSound(
                null, user?.blockPos, SoundRegister.WHIP_SWHOO, SoundCategory.PLAYERS, 3.0f, 1.0f
            )
        }

        return TypedActionResult.success(user?.getStackInHand(hand))
    }

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {

        target?.let {

            if (it.isPlayer) {
                if (!it.isSneaking) {
                    if (it.health < 10) {
                        it.health = 0f
                    }
                    it.health /= 2
                    return super.postHit(stack, it, attacker)
                }
                it.health = it.health
                it.world.playSound(
                    null, it.blockPos, SoundRegister.WHIP_CRACK, SoundCategory.PLAYERS, 4.0f, 1.0f
                )
            } else {
                it.health = it.health
                attacker?.sendMessage(Text.translatable("message.timaviciix_ebm.feather_duster"))
                it.world.playSound(
                    null, it.blockPos, SoundRegister.WHIP_SWHOO, SoundCategory.PLAYERS, 3.0f, 1.0f
                )
            }

        }

        return false

    }
}