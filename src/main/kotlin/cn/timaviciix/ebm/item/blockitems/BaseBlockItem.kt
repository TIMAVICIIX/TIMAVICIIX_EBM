/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-27  17:30
 *@Description: 基本BlockItem类
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.blockitems

import cn.timaviciix.ebm.client.gui.ReadingScreen
import cn.timaviciix.ebm.network.Packets
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.block.Block
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import org.slf4j.LoggerFactory

open class BaseBlockItem(
    block: Block,
    settings: Settings,
    nameColor: Int = 0xeeeeee,
    private val needSneakingPlace: Boolean = false,
    val itemClassify: BlockItemClassify = BlockItemClassify.Unknown
) :
    BlockItem(block, settings) {

    private val logger = LoggerFactory.getLogger(GlobalData.MOD_ID)
    var alreadyReading = false

    companion object {

        //
        enum class BlockItemClassify {
            Unknown,
            Books,
            Worktables,
            Others,
            Copiers
        }
    }

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        //logger.info("Active use")
        //logger.info("${user?.id} is Using")
        if (user != null) {
            //Use Mixin
            if (alreadyReading) {
                return TypedActionResult.fail(user.getStackInHand(hand))
            } else {
                //logger.info("send Use!!!")

                alreadyReading = true
                Packets.sendReadingPlayerUUid(user)

                //@Imp: active reading UI
                if (user.world.isClient) {
                    user.swingHand(Hand.MAIN_HAND)
                    MinecraftClient.getInstance().setScreen(ReadingScreen(this, user))
                }
            }
        }
        user?.swingHand(hand)
        return TypedActionResult.fail(user?.getStackInHand(hand))
    }

    private val nameStyle: Style = Style.EMPTY.withColor(TextColor.fromRgb(nameColor))
    override fun getName(): Text {
        return super.getName().copy().setStyle(nameStyle)
    }

    override fun getName(stack: ItemStack?): Text {
        return super.getName(stack).copy().setStyle(nameStyle)
    }

    /**
     * needSneaking:Does the block need to be placed crouching?
     */
    override fun place(context: ItemPlacementContext?): ActionResult {
        logger.info("Active place")
        //logger.info("Sneaking state:${needSneakingPlace}")
        return if (needSneakingPlace) {
            val player = context?.player
            if (player != null && !player.isSneaking) {
                if (itemClassify == BlockItemClassify.Books && !alreadyReading) {
                    player.swingHand(Hand.MAIN_HAND)
                    //@Imp: Fixed the other place state to read

                    //@Imp: active Book UI
                    alreadyReading = true
                    Packets.sendReadingPlayerUUid(player)

                    //@Imp: active reading UI
                    if (player.world.isClient) {
                        player.swingHand(Hand.MAIN_HAND)
                        MinecraftClient.getInstance().setScreen(ReadingScreen(this, player))
                    }
                    ActionResult.FAIL
                } else {
                    //logger.info("Player doesn't Sneaking")
                    ActionResult.FAIL
                }
            } else {
                //logger.info("Player is Sneaking")
                super.place(context)
            }
        } else {
            //logger.info("needing state fail!")
            super.place(context)
        }
    }

}