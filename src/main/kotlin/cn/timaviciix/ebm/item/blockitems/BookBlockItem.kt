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
    itemClassify: BaseBlockItem.Companion.BlockItemClassify
) : BaseBlockItem(block, settings, nameColor, itemClassify), BlockItemHandler {

    /**
     * needSneaking:Does the block need to be placed crouching?
     */


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

    override fun playUsingSounds(user: PlayerEntity) {
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

    override fun setScreen(user: PlayerEntity) {
        MinecraftClient.getInstance().setScreen(ReadingScreen(
            closeOperation = {
                playCloseSounds(user)
                Packets.sendReadingPlayerUUid(user)
            },
            openOperation = {
                playOpenSounds(user)
                Packets.sendReadingPlayerUUid(user)
            }
        ))
    }

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        if (user != null) {
            //@Imp: active reading UI
            if (user.world.isClient) {
                user.swingHand(Hand.MAIN_HAND)
                setScreen(user)
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
                return super.place(context)
            } else {
                if (player.world.isClient) {
                    player.swingHand(Hand.MAIN_HAND)
                    setScreen(player)
                }
                return ActionResult.FAIL
            }
        } else {
            return ActionResult.FAIL
        }
    }
}


