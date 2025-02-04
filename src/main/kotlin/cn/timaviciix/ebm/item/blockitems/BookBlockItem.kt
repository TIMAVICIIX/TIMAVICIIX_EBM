/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.blockitems
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-04  23:27
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.blockitems

import cn.timaviciix.ebm.client.gui.ReadingScreen
import cn.timaviciix.ebm.network.Packets
import cn.timaviciix.ebm.registers.others.SoundRegister
import net.minecraft.block.Block
import net.minecraft.client.MinecraftClient
import net.minecraft.client.sound.PositionedSoundInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.random.Random
import net.minecraft.world.World

class BookBlockItem(
    block: Block,
    settings: Settings,
    nameColor: Int,
    private val needSneakingPlace: Boolean = true,
    itemClassify: Companion.BlockItemClassify
) : BaseBlockItem(block, settings, nameColor, needSneakingPlace, itemClassify), BookItemOverrides {

    override fun playOpenSounds(user: PlayerEntity) {
        val volume = 5.0f
        val pitch = 1.0f
        MinecraftClient.getInstance().soundManager.play(
            PositionedSoundInstance(
                SoundRegister.TURNING_PAGE,
                SoundCategory.BLOCKS,
                volume,
                pitch,
                Random.create(),
                user.blockPos
            )
        )
    }

    override fun playUsingSounds() {
        TODO("Not yet implemented")
    }

    override fun playCloseSounds(user: PlayerEntity) {
        val volume = 5.0f
        val pitch = 1.0f
        MinecraftClient.getInstance().soundManager.play(
            PositionedSoundInstance(
                SoundRegister.BOOK_CLOSING,
                SoundCategory.BLOCKS,
                volume,
                pitch,
                Random.create(),
                user.blockPos
            )
        )
    }

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        if (user != null) {
            //Use Mixin
            Packets.sendReadingPlayerUUid(user)

            //@Imp: active reading UI
            if (user.world.isClient) {
                user.swingHand(Hand.MAIN_HAND)

                playOpenSounds(user)

                MinecraftClient.getInstance().setScreen(ReadingScreen(user))
            }
        }

        user?.swingHand(hand)
        return TypedActionResult.fail(user?.getStackInHand(hand))
    }

    override fun place(context: ItemPlacementContext?): ActionResult {
        //logger.info("Sneaking state:${needSneakingPlace}")
        val player = context?.player
        if (player != null) {
            if (player.isSneaking) {
                return ActionResult.SUCCESS
            } else {

                player.swingHand(Hand.MAIN_HAND)
                //@Imp: Fixed the other place state to read
                //@Imp: active Book UI

                Packets.sendReadingPlayerUUid(player)

                //@Imp: active reading UI
                if (player.world.isClient) {
                    player.swingHand(Hand.MAIN_HAND)
                    MinecraftClient.getInstance().setScreen(ReadingScreen(player))
                }
                return ActionResult.FAIL
            }
        } else {
            return ActionResult.FAIL
        }
    }
}


