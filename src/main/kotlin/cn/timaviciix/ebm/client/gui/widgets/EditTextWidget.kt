/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui.widgets
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-12  21:15
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui.widgets

import cn.timaviciix.ebm.client.gui.GUIConfig
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.widget.EditBoxWidget
import net.minecraft.text.Text

class EditTextWidget(
    private val textRender: TextRenderer,
    private val positionX: Int,
    private val positionY: Int,
    private val widgetWidth: Int,
    private val widgetHeight: Int,
    private val maxLines: Int = 16,
    private val placeholder: Text = Text.empty(),
    private val message: Text = Text.empty()
) : EditBoxWidget(textRender, positionX, positionY, widgetWidth, widgetHeight, placeholder, message) {

    override fun render(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, 0f)
    }

    override fun renderContents(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        super.renderContents(context, mouseX, mouseY, delta)
    }

    override fun charTyped(chr: Char, modifiers: Int): Boolean {
        GlobalData.LOGGER.info("typed a char")
        return if (GUIConfig.BufferFromMixin.wrapLineCount == maxLines) {
            GlobalData.LOGGER.info("Can't Typed More Chars!!!")
            false
        } else {
            super.charTyped(chr, modifiers)
        }
    }

}