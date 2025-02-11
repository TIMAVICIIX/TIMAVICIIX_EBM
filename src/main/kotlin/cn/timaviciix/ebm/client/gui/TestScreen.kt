/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-11  01:04
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui

import cn.timaviciix.ebm.client.gui.widgets.ImageButtonWidget
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ScrollableTextWidget
import net.minecraft.client.gui.widget.TextFieldWidget
import net.minecraft.text.Style
import net.minecraft.text.Text

class TestScreen(
    private val openOperation: () -> Unit = {},
    private val changePageOperation: () -> String? = { null },
    private val saveOperation: () -> Unit = {},
    private val closeOperation: () -> Unit = {}
) : Screen(Text.literal("原生UI示例")) {

    private lateinit var textDisplay: ScrollableTextWidget
    private lateinit var textField: TextFieldWidget

    override fun shouldPause(): Boolean {
        return false
    }

    override fun renderBackground(context: DrawContext?) {
        super.renderBackground(context)
    }

    override fun render(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(context)
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
    }

    override fun init() {
        openOperation()

        addDrawableChild(
            ImageButtonWidget(GUIConfig.CLOSE_BUTTON_TEXTURE_SET, width - 25, 10, 20, 20) {
                saveOperation()
                close()
            }
        )

        val displayTextPosition = Pair(
            (width - GUIConfig.READING_GUI_TEXTURE_SET.sizeWidth) / 2 + 24,
            (height - GUIConfig.READING_GUI_TEXTURE_SET.sizeHeight) / 2 + 14
        )

        textDisplay = addDrawableChild(
           ScrollableTextWidget(
                displayTextPosition.first,
                displayTextPosition.second,
                116, 176,
                Text.literal(
                    "黑暗中的我們都沒有說話 你只想回家 不想你回家\n" +
                            "寂寞深的像海太讓人害怕\n" +
                            "溫柔你的手 輕輕揉著我的髮\n" +
                            "你的眉眼說 你好渴望我擁抱\n" +
                            "你身體卻在拼命逃 當慾望在燃燒你愛我還是他\n" +
                            "是不是真的他有比我好 你為誰在掙扎\n" +
                            "愛我還是他\n" +
                            "就說出你想說的真心話\n" +
                            "你到底要跟我還是他\n" +
                            "愛 愛 愛 他\n" +
                            "這是不是命運對我的懲罰\n" +
                            "愛你也沒辦法 恨你也沒辦法\n"
                ).setStyle(Style.EMPTY.withColor(GUIConfig.blackTextColor4)),
                textRenderer
            )
        )


    }

    private fun saveData() {
        saveOperation()
    }

    override fun close() {
        closeOperation()
        super.close()
    }
}