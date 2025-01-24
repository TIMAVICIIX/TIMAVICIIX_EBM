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
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FeatherDuster :
    BaseSwordItem(0xf08a5d, 0, GlobalData.OWO_ITEM_SIGNAL_SETTING, toolMaterial = FeatherDusterMaterial()) {

    val logger: Logger = LoggerFactory.getLogger(GlobalData.MOD_ID)

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {

        user?.swingHand(hand, true)
        if (world?.isClient == true) {
            world.playSound(
                null, user?.blockPos, SoundRegister.WHIP_SWHOO, SoundCategory.PLAYERS, 2.0f, 1.0f
            )
        }

        return TypedActionResult.success(user?.getStackInHand(hand))
    }

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {

        target?.let {

            if (it.isPlayer) {
                if (!it.isSneaking) {
                    if (target.health < 10) {
                        target.health = 0f
                    }
                    target.health /= 2
                    return super.postHit(stack, target, attacker)
                }
                target.world.playSound(
                    null, target.blockPos, SoundRegister.WHIP_CRACK, SoundCategory.PLAYERS, 3.0f, 1.0f
                )
            } else {
                attacker?.sendMessage(Text.translatable("message.timaviciix_ebm.feather_duster"))
                target.world.playSound(
                    null, target.blockPos, SoundRegister.WHIP_SWHOO, SoundCategory.PLAYERS, 2.0f, 1.0f
                )
            }

        }

        return false

    }
}