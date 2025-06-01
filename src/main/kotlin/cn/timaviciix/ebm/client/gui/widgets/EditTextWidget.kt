/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui.widgets
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-12  21:15
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui.widgets

import cn.timaviciix.ebm.puppets.EditBoxPuppet
import cn.timaviciix.ebm.puppets.EditBoxWidgetPuppet
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.client.MinecraftClient
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text


class EditTextWidget(
    private val textRenderer: TextRenderer,
    private val positionX: Int,
    private val positionY: Int,
    private val widgetWidth: Int,
    private val widgetHeight: Int,
    private val onClipboard: (String,List<EditBoxPuppet.Substring>) -> Unit,
    private val maxLines: Int = 18,
    private val placeholder: Text = Text.empty(),
    private val message: Text = Text.empty(),
) : EditBoxWidgetPuppet(textRenderer, positionX, positionY, widgetWidth, widgetHeight, placeholder, message) {

    override fun charTyped(chr: Char, modifiers: Int): Boolean {
        GlobalData.LOGGER.info("CharTyped!")
        if (this.editBox.linesCount > maxLines) {
            //GlobalData.LOGGER.info("[charTyped]Can't Typed More Chars!!!")
            return false
        }
        //GlobalData.LOGGER.info("[charTyped]Typed Char!!!")
        return super.charTyped(chr, modifiers)
    }

    override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        //GlobalData.LOGGER.info("KeyPressed")
        return if (this.editBox.linesCount > maxLines && isFocused) {
            //GlobalData.LOGGER.info("[keyPressed]Can't Type More Chars!!!")
            //GlobalData.LOGGER.info("Now Text:$text")
            false
        } else if (this.editBox.linesCount == maxLines && (keyCode == 257 || keyCode == 335)) {
            //GlobalData.LOGGER.info("[keyPressed]Can't Type More Chars!!!")
            //GlobalData.LOGGER.info("Now Text:$text")
            false
        } else if (Screen.isPaste(keyCode)) {
            //@Imp: 应返回组件信息与文本粘贴项到UI界面进行文本粘贴预演处理
            //reWrapLines(MinecraftClient.getInstance().keyboard.clipboard)
            val contentString = text + MinecraftClient.getInstance().keyboard.clipboard
            onClipboard(contentString,this.editBox.reWrapBeforeLines(contentString))
            false
        } else {
            super.keyPressed(keyCode, scanCode, modifiers)
        }
    }

    // 通过textRenderer渲染器渲染目标行数，将当前满足行数的内容截取呈现在text中，剩余行数内容缓存在cutBufferString
    /**
     * 该方法目前存在以下问题：
     * 1.cutBufferString在每次编辑触发时都会被IO，目标是让它仅需在使用粘贴时被IO
     * 2.该方法在父组件中已经存在（EditBox.reWarp()方法），但尚未被优化，可尝试通过构建傀儡组件实现
     * 3.该方法通过获取text中的所有文本进而剪切部分多余文本到缓存区中，操作级别太低，数据已经被注入text，又将其拿出来修改，效率与性能都会降低
     * 4.无法保证text中是否已经是父组件剪切过后的文本
     *
     * 1.不应直接在rewarp中修改文本渲染逻辑，应在keypress中唤起相应文本处理
     * 2.在UI界面中建立相应缓存区，剪切文本并缓存多余文本
     * 3.遇到海量文本时仍然通过缓存区处理，左半与右半文本编辑区应建立文本注入逻辑
     * 4.海量文本通过UI进行分发预演，只展现最后两页，其余页数纳入数据缓存
     * */
    // 注释掉，没用了
    /*fun reWrapLines(additionalText:String=""): Int {
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
    }*/

}