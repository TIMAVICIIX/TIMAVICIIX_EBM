/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-11  01:04
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui

import cn.timaviciix.ebm.client.gui.widgets.EditTextWidget
import cn.timaviciix.ebm.client.gui.widgets.ImageButtonWidget
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ScrollableTextWidget
import net.minecraft.text.Style
import net.minecraft.text.Text

class TestScreen(
    private val openOperation: () -> Unit = {},
    private val changePageOperation: () -> String? = { null },
    private val saveOperation: () -> Unit = {},
    private val closeOperation: () -> Unit = {}
) : Screen(Text.literal("原生UI示例")) {

    private lateinit var closeBtn: ImageButtonWidget
    private lateinit var nextPageBtn: ImageButtonWidget
    private lateinit var previewPageBtn: ImageButtonWidget

    private lateinit var textDisplay: ScrollableTextWidget
    private lateinit var textField: EditTextWidget
    private var bufferFieldString = ""

    private val pendingWidgetUpdates = mutableListOf<() -> Unit>()

    init {
        openOperation()
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
                    (width - sizeWidth) / 2, (height - sizeHeight) / 2,
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
        textField.render(context,mouseX,mouseY,delta)
        textDisplay.render(context,mouseX,mouseY,delta)
    }

    override fun init() {
        //@Imp: Make sure to do the following after render()
        pendingWidgetUpdates.add {
            closeBtn = addDrawableChild(
                ImageButtonWidget(GUIConfig.CLOSE_BUTTON_TEXTURE_SET, width - 25, 10, 20, 20) {
                    saveOperation()
                    close()
                }
            )
            nextPageBtn = addDrawableChild(
                ImageButtonWidget(GUIConfig.NEXT_BUTTON_TEXTURE_SET, width - 125, height - 30, 20, 20) {
                    changePageOperation()
                }
            )
            previewPageBtn = addDrawableChild(
                ImageButtonWidget(GUIConfig.PREVIEW_BUTTON_TEXTURE_SET, width - 155, height - 30, 20, 20) {
                    changePageOperation()
                }
            )
        }

        pendingWidgetUpdates.add {
            val displayTextPosition = Pair(
                (width - GUIConfig.READING_GUI_TEXTURE_SET.sizeWidth) / 2 + 22,
                (height - GUIConfig.READING_GUI_TEXTURE_SET.sizeHeight) / 2 + 16
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
                ((height - GUIConfig.READING_GUI_TEXTURE_SET.sizeHeight) / 2) + 12
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
    }

    private fun textInterpreter(bufferString: String): Text {
        return if (bufferString.isEmpty()) {
            Text.empty().setStyle(Style.EMPTY.withColor(GUIConfig.blackTextColor4))
        } else {
            Text.literal(bufferString).setStyle(Style.EMPTY.withColor(GUIConfig.blackTextColor4))
        }

    }

    private fun saveData() {
        saveOperation()
    }

    override fun close() {
        closeOperation()
        super.close()
        GUIConfig.BufferFromMixin.wrapLineCount = 1
    }
}