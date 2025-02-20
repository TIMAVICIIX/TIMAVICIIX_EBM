/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui.widgets
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-12  21:15
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui.widgets

import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.client.MinecraftClient
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.EditBoxWidget
import net.minecraft.text.Style
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

    private val lines: MutableList<Pair<Int, Int>> = mutableListOf()
    private var cutBufferString = StringBuilder().toString()

    override fun charTyped(chr: Char, modifiers: Int): Boolean {
        GlobalData.LOGGER.info("CharTyped!")
        if (lines.size > maxLines) {
            //GlobalData.LOGGER.info("[charTyped]Can't Typed More Chars!!!")
            return false
        }
        //GlobalData.LOGGER.info("[charTyped]Typed Char!!!")
        return super.charTyped(chr, modifiers)
    }

    override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        //GlobalData.LOGGER.info("KeyPressed")
        return if (lines.size > maxLines && isFocused) {
            //GlobalData.LOGGER.info("[keyPressed]Can't Type More Chars!!!")
            //GlobalData.LOGGER.info("Now Text:$text")
            false
        } else if (lines.size == maxLines && (keyCode == 257 || keyCode == 335)) {
            //GlobalData.LOGGER.info("[keyPressed]Can't Type More Chars!!!")
            //GlobalData.LOGGER.info("Now Text:$text")
            false
        } else if (Screen.isPaste(keyCode)) {
            reWrapLines(MinecraftClient.getInstance().keyboard.clipboard)
            false
        } else {
            super.keyPressed(keyCode, scanCode, modifiers)
        }
    }

    fun reWrapLines(additionalText:String=""): Int {
        this.lines.clear()
        val consistText = text + additionalText
        if (consistText.isEmpty()) {
            this.lines.add(Pair(0, 0))
        } else {
            this.textRenderer
                .textHandler
                .wrapLines(// this.width - doublePadding
                    consistText, this.widgetWidth - 8, Style.EMPTY, false
                ) { _: Style?, start: Int, end: Int ->
                    this.lines.add(
                        Pair(start, end)
                    )
                }
            if (consistText[consistText.length - 1] == '\n') {
                this.lines.add(Pair(consistText.length, consistText.length))
            }
        }

        if (lines.size > maxLines) {
            cutBufferString = consistText.substring(lines[maxLines - 1].first, lines[lines.size - 1].second)
            text = consistText.substring(0, lines[maxLines - 1].second)
        }
        GlobalData.LOGGER.info("[EditTextWidget]Line Count:${lines.size}")
        return lines.size
    }

}