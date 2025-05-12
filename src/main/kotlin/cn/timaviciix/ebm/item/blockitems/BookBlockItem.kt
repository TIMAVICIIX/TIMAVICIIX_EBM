/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.blockitems
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-04  23:27
 *@Description: 书本BlockItem类，是整个Mod的代码核心
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.blockitems

import cn.timaviciix.ebm.client.gui.OriginalWritingScreen
import cn.timaviciix.ebm.client.gui.ScreenSetHandler
import cn.timaviciix.ebm.data.DataFactory
import cn.timaviciix.ebm.data.SealedData
import cn.timaviciix.ebm.data.SealedDataPackage
import cn.timaviciix.ebm.data.book.BookData
import cn.timaviciix.ebm.data_io.data_configs.BookTooltipConfig
import cn.timaviciix.ebm.util.StyleUtil
import net.minecraft.block.Block
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import java.util.*

class BookBlockItem(
    block: Block,
    settings: Settings,
    nameColor: Int,
    val typeCode: Int,
    val maxPage: Int
) : BaseBlockItem(block, settings, nameColor, Companion.BlockItemClassify.Books), ScreenSetHandler {

    /**2024.02.10 01:24
     * 目前需要解决的问题：
     * 1.bookData与Nbt的绑定机制过松，并且与Nbt的绑定过于强硬，对后续其他数据绑定存在阻碍
     * 2.itemStack与Nbt创建、初始化存在交叉，并且与bookData初始化发生交叉，很大部分原因是采用高阶函数引起的，导致函数过于强硬，代码僵硬，可修改性与灵活性差
     * 3.待优化变量 @param bookDataAlready 这个变量我老是觉得能够优化掉！
     */

    private lateinit var dataPackage: SealedDataPackage
    private lateinit var bookData: BookData

    private fun ItemStack.loadCacheDataFromNbt(authorName: String, playerUUID: UUID) {
        dataPackage = SealedDataPackage(
            SealedData.BookDataComponent(DataFactory.getOrCreateBookData(this, authorName, playerUUID, true))
        ).apply {
            bookData = get()
        }

    }


    //Stamp（签章权不应在此入参，应绑定在BooData内，再从其中抽取判定，并形成UI）
    override fun setScreen(user: PlayerEntity, stack: ItemStack) {
        stack.loadCacheDataFromNbt(user.name.string, user.uuid)
        if (!bookData.copyPermission.stampingState) {
            MinecraftClient.getInstance().setScreen(OriginalWritingScreen(bookData, user, stack))
        } else {
            user.sendMessage(Text.literal("TODO"))
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

    //书本Item的tooltip展示
    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        require(stack != null && tooltip != null)
        stack.let {
            val bookData = DataFactory.getOrCreateBookData(it, playerUUID = null, loadBaseData = true)
            val bookEditor = bookData.editor
            val textEditor = Text.translatable(BookTooltipConfig.BOOK_EDITOR).string

            tooltip.add(Text.literal("$textEditor:$bookEditor").setStyle(StyleUtil.WITHE_COLOR_STYLE))

            if (Screen.hasShiftDown()) {
                val bookAuthor = bookData.author
                val textAuthor = Text.translatable(BookTooltipConfig.BOOK_AUTHOR).string

                val bookTag = bookData.pageTag
                val textTag = Text.translatable(BookTooltipConfig.BOOK_TAG).string

                val isCopies = if (bookData.copyPermission.isCopies) {
                    Text.translatable(BookTooltipConfig.BOOK_IS_COPIES)
                } else {
                    Text.translatable(BookTooltipConfig.BOOK_IS_MANUSCRIPT)
                }
                val stampState = if (bookData.copyPermission.stampingState) {
                    Text.translatable(BookTooltipConfig.BOOK_STAMPED)
                } else {
                    Text.translatable(BookTooltipConfig.BOOK_DRAFT)
                }

                tooltip.apply {
                    add(Text.literal("$textAuthor:$bookAuthor").setStyle(StyleUtil.WITHE_COLOR_STYLE))
                    add(Text.literal("$textTag:$bookTag").setStyle(StyleUtil.WITHE_COLOR_STYLE))
                    add(isCopies.setStyle(StyleUtil.WITHE_COLOR_STYLE))
                    add(stampState.setStyle(StyleUtil.WITHE_COLOR_STYLE))
                }
            } else {
                val holdShift = Text.translatable(BookTooltipConfig.HOLD_SHIFT).setStyle(StyleUtil.GRAY_COLOR_STYLE)
                tooltip.add(holdShift)
            }
        }

    }
}


