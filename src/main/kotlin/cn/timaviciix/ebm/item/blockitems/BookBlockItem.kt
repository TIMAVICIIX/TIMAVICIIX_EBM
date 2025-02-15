/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.blockitems
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-04  23:27
 *@Description: 书本BlockItem类，是整个Mod的代码核心
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.blockitems

import cn.timaviciix.ebm.client.gui.GUIConfig
import cn.timaviciix.ebm.client.gui.OriginalWritingScreen
import cn.timaviciix.ebm.data.DataFactory
import cn.timaviciix.ebm.data.SealedData
import cn.timaviciix.ebm.data.SealedDataPackage
import cn.timaviciix.ebm.data.book.BookData
import cn.timaviciix.ebm.data.handler.ScreenSetHandler
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
    itemClassify: Companion.BlockItemClassify
) : BaseBlockItem(block, settings, nameColor, itemClassify), ScreenSetHandler {

    /**2024.02.10 01:24
     * 目前需要解决的问题：
     * 1.bookData与Nbt的绑定机制过松，并且与Nbt的绑定过于强硬，对后续其他数据绑定存在阻碍
     * 2.itemStack与Nbt创建、初始化存在交叉，并且与bookData初始化发生交叉，很大部分原因是采用高阶函数引起的，导致函数过于强硬，代码僵硬，可修改性与灵活性差
     * 3.待优化变量 @param bookDataAlready 这个变量我老是觉得能够优化掉！
     * 4.
     */

    private lateinit var dataPackage: SealedDataPackage
    private lateinit var bookData: BookData

    private fun ItemStack.loadCacheDataFromNbt(authorName: String) {
        val nbt = this.orCreateNbt

        dataPackage = SealedDataPackage(
            SealedData.BookDataComponent(DataFactory.getOrCreateBookData(nbt, authorName, true))
        ).apply {
            bookData = get()
        }
    }

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

    override fun setScreen(user: PlayerEntity, stack: ItemStack) {
        stack.loadCacheDataFromNbt(user.name.string)

        val setOpenOperation = {
            GUIConfig.BufferFromMixin.toggleMixin()
            playOpenSounds(user)
            Packets.sendReadingPlayerUUid(user)
        }

        val setChangeOperation = {
            playUsingSounds(user)
            bookData.currentContent
        }

        val setCloseOperation = {
            GUIConfig.BufferFromMixin.toggleMixin()
            playCloseSounds(user)
            Packets.sendReadingPlayerUUid(user)
        }

        if (bookData.copyPermission.getStampingState()) {
            MinecraftClient.getInstance().setScreen(
                OriginalWritingScreen(
                    bookData,
                    openOperation = setOpenOperation,
                    changePageOperation = setChangeOperation,
                    closeOperation = setCloseOperation
                )
            )
        } else {
            MinecraftClient.getInstance().setScreen(
                OriginalWritingScreen(
                    bookData,
                    openOperation = setOpenOperation,
                    changePageOperation = setChangeOperation,
                    closeOperation = setCloseOperation,
                    saveOperation = {
                        bookData.save(stack)
                    }
                ))
        }

    }

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        if (user != null) {
            //@Imp: active reading UI
            if (user.world.isClient) {
                user.swingHand(Hand.MAIN_HAND)
                setScreen(user, user.getStackInHand(hand))
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
                    setScreen(player, context.stack)
                }
                return ActionResult.FAIL
            }
        } else {
            return ActionResult.FAIL
        }
    }
}


