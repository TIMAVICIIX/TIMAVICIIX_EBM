/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-11  01:04
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui

import cn.timaviciix.ebm.client.gui.widgets.*
import cn.timaviciix.ebm.data.book.BookData
import cn.timaviciix.ebm.data.book.BookData.Companion.save
import cn.timaviciix.ebm.network.Packets
import cn.timaviciix.ebm.registers.others.SoundRegister
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.widget.ScrollableTextWidget
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.text.Style
import net.minecraft.text.Text

class OriginalWritingScreen(
    private val bookData: BookData,
    private val user: PlayerEntity,
    private val stack: ItemStack
) : BaseScreen(Text.literal("TIMAVICIIX_EBM:WRITING_GUI")) {

    private lateinit var closeBtn: ImageButtonWidget
    private lateinit var nextPageBtn: ImageButtonWidget
    private lateinit var previousPageBtn: ImageButtonWidget

    private lateinit var titleTextWidget: ThreePatchHorizontalFieldWidget
    private lateinit var tipsWidget: RandomTipsWidget

    private lateinit var savePageBtn: TextImageButtonWidget
    private lateinit var previewBtn: TextImageButtonWidget

    private lateinit var textDisplay: ScrollableTextWidget
    private lateinit var textField: EditTextWidget

    private var bufferTitleFieldString = ""
    private var bufferFieldString = ""
    private var currentPage = 1

    private val pendingWidgetUpdates = mutableListOf<() -> Unit>()

    init {
        bufferTitleFieldString = bookData.bookName
        val lastContent = bookData.getLastReadingContent()
        bufferFieldString = lastContent.second
        currentPage = lastContent.first
        openOperations()
    }

    override fun shouldPause(): Boolean {
        return false
    }

    override fun renderBackground(context: DrawContext?) {
        super.renderBackground(context)
    }

    override fun render(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        super.renderBackground(context)
        super.render(context, mouseX, mouseY, 0.4f)
        context?.let {
            GUIConfig.READING_GUI_TEXTURE_SET.apply {
                it.drawTexture(
                    texture,
                    (width - sizeWidth) / 2, (height - sizeHeight) / 2 + 5,
                    u.toFloat(), v.toFloat(),
                    sizeWidth, sizeHeight,
                    textureWidth, textureHeight
                )
            }
        }

        //@Imp: Execute
        for (update in pendingWidgetUpdates) {
            update()
        }
        pendingWidgetUpdates.clear()

        //Render Components Again!
        tipsWidget.render(context,mouseX,mouseY,delta)
        textField.render(context, mouseX, mouseY, delta)
        textDisplay.render(context, mouseX, mouseY, delta)
    }

    override fun init() {
        //@Imp: Make sure to do the following after render()
        pendingWidgetUpdates.add {
            closeBtn = addDrawableChild(
                ImageButtonWidget(GUIConfig.CLOSE_BUTTON_TEXTURE_SET, width - 25, 5, 20, 20) {
                    doneOperations()
                    close()
                }
            )
            nextPageBtn = addDrawableChild(
                ImageButtonWidget(GUIConfig.NEXT_BUTTON_TEXTURE_SET, width - 125, height - 25, 20, 20) {
                    changeOperations()
                }
            )
            previousPageBtn = addDrawableChild(
                ImageButtonWidget(GUIConfig.PREVIEW_BUTTON_TEXTURE_SET, width - 155, height - 25, 20, 20) {
                    changeOperations()
                }
            )
        }

        pendingWidgetUpdates.add {
            titleTextWidget = addDrawableChild(
                ThreePatchHorizontalFieldWidget(
                    GUIConfig.TITLE_THREE_PATCH_TEXTURE_SET,
                    textRenderer,
                    10, 5, width - 100, 20,
                    true,
                    textInterpreter(bufferTitleFieldString)
                )
            )

            tipsWidget = addDrawableChild(
                RandomTipsWidget(
                    GUIConfig.TIPS_WIDGET_TEXTURE_SET,
                    textRenderer,
                    25,
                    height - 25,
                    100,
                    20,
                    GUIConfig.TIPS_TEXT_SET.toList()
                )
            )

        }

        pendingWidgetUpdates.add {
            val displayTextPosition = Pair(
                (width - GUIConfig.READING_GUI_TEXTURE_SET.sizeWidth) / 2 + 22,
                (height - GUIConfig.READING_GUI_TEXTURE_SET.sizeHeight) / 2 + 21
            )
            textDisplay = addDrawableChild(
                ScrollableTextWidget(
                    displayTextPosition.first,
                    displayTextPosition.second,
                    116, 170,
                    textInterpreter(bufferFieldString),
                    textRenderer
                )
            )


            val textFieldPosition = Pair(
                ((width - GUIConfig.READING_GUI_TEXTURE_SET.sizeWidth) / 2) + 136,
                ((height - GUIConfig.READING_GUI_TEXTURE_SET.sizeHeight) / 2) + 17
            )

            textField = addDrawableChild(
                EditTextWidget(
                    textRenderer,
                    textFieldPosition.first, textFieldPosition.second,
                    116, 170
                ).apply {
                    text = bufferFieldString
                    setChangeListener {
                        bufferFieldString = it



                        remove(textDisplay)
                        textDisplay = addDrawableChild(
                            ScrollableTextWidget(
                                displayTextPosition.first,
                                displayTextPosition.second,
                                116, 170,
                                textInterpreter(bufferFieldString),
                                textRenderer
                            )
                        )
                    }


                }
            ).apply {
                GUIConfig.BufferFromMixin.listener = { newValue ->
                    if (newValue > maxLines) {
                        val newText = text.substring(0, text.length - 1)
                        MinecraftClient.getInstance().execute {
                            text = newText
                        }
                        bufferFieldString = newText
                    }

                }
            }
        }

        pendingWidgetUpdates.add {

            val operationsBtnPosition = Pair(
                width - 41, height - 25
            )

            savePageBtn = addDrawableChild(
                TextImageButtonWidget(
                    textRenderer,
                    GUIConfig.SHORT_TEXT_BTN_TEXTURE_SET,
                    operationsBtnPosition.first,
                    operationsBtnPosition.second - 30,
                    36, 20,
                    textTranslatableInterpreter(
                        "gui.timaviciix_ebm.save_btn",
                        Style.EMPTY.withBold(true).withColor(GUIConfig.blackTextColor4)
                    )
                ) {
                    doneOperations()
                }
            )

            previewBtn = addDrawableChild(
                TextImageButtonWidget(
                    textRenderer,
                    GUIConfig.SHORT_TEXT_BTN_TEXTURE_SET,
                    operationsBtnPosition.first,
                    operationsBtnPosition.second,
                    36, 20,
                    textTranslatableInterpreter(
                        "gui.timaviciix_ebm.previous_btn",
                        Style.EMPTY.withBold(true).withColor(GUIConfig.blackTextColor4)
                    )
                ) {
                    //
                }
            )

        }
    }

    private fun textInterpreter(bufferString: String): Text {
        return if (bufferString.isEmpty()) {
            Text.empty().setStyle(Style.EMPTY.withColor(GUIConfig.blackTextColor4))
        } else {
            Text.literal(bufferString).setStyle(Style.EMPTY.withColor(GUIConfig.blackTextColor4))
        }

    }

    private fun textTranslatableInterpreter(
        bufferString: String,
        style: Style = Style.EMPTY.withColor(GUIConfig.blackTextColor4)
    ): Text {
        return if (bufferString.isEmpty()) {
            Text.empty().setStyle(style)
        } else {
            Text.translatable(bufferString).setStyle(style)
        }

    }


    //@Imp: Change Title Widget State
    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        titleTextWidget.onScreenClick(mouseX, mouseY)
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun openOperations() {
        GUIConfig.BufferFromMixin.toggleMixin()
        SoundRegister.BookSounds.playOpenSounds(user)
        Packets.sendReadingPlayerUUid(user)
    }

    override fun changeOperations() {
        SoundRegister.BookSounds.playUsingSounds(user)
    }

    override fun doneOperations() {
        bookData.save(stack)
    }

    override fun closeOperations() {
        GUIConfig.BufferFromMixin.toggleMixin()
        SoundRegister.BookSounds.playCloseSounds(user)
        Packets.sendReadingPlayerUUid(user)
    }

    override fun close() {
        closeOperations()
        GUIConfig.BufferFromMixin.wrapLineCount = 1
        super.close()
    }
}