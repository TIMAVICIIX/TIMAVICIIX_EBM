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
    private val textRenderer: TextRenderer,
    private val positionX: Int,
    private val positionY: Int,
    private val widgetWidth: Int,
    private val widgetHeight: Int,
    val maxLines: Int = 18,
    private val placeholder: Text = Text.empty(),
    private val message: Text = Text.empty()
) : EditBoxWidget(textRenderer, positionX, positionY, widgetWidth, widgetHeight, placeholder, message) {

    override fun charTyped(chr: Char, modifiers: Int): Boolean {
        GlobalData.LOGGER.info("CharTyped!")
        if (GUIConfig.BufferFromMixin.wrapLineCount > maxLines) {
            //GlobalData.LOGGER.info("[charTyped]Can't Typed More Chars!!!")
            return false
        }
        //GlobalData.LOGGER.info("[charTyped]Typed Char!!!")
        return super.charTyped(chr, modifiers)
    }

    override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        //GlobalData.LOGGER.info("KeyPressed")
        return if (GUIConfig.BufferFromMixin.wrapLineCount > maxLines && isFocused) {
            //GlobalData.LOGGER.info("[keyPressed]Can't Type More Chars!!!")
            //GlobalData.LOGGER.info("Now Text:$text")
            false
        } else if (GUIConfig.BufferFromMixin.wrapLineCount == maxLines && (keyCode == 257 || keyCode == 335) &&isFocused) {
            //GlobalData.LOGGER.info("[keyPressed]Can't Type More Chars!!!")
            //GlobalData.LOGGER.info("Now Text:$text")
            false
        } else {
            super.keyPressed(keyCode, scanCode, modifiers)
        }
    }
}