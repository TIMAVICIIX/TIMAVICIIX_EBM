/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui.widgets
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-16  22:13
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui.widgets

import cn.timaviciix.ebm.client.gui.GUIConfig
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.widget.TextWidget
import net.minecraft.text.Text

class RandomTipsWidget(
    private val textureConfig: GUIConfig.NormalTextureConfig,
    private val textRenderer: TextRenderer,
    private val positionX: Int,
    private val positionY: Int,
    private val widgetWidth: Int,
    private val widgetHeight: Int,
    private val textSetList: List<Text>
) : TextWidget(
    positionX, positionY, widgetWidth, widgetHeight, Text.empty(), textRenderer
) {

    private var checkTick = 0
    private var currentText = textSetList.random()

    override fun renderButton(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        if (checkTick >= 2000) {
            checkTick = 0
            currentText = textSetList.random()
        } else {
            checkTick++
        }
        val i =
            this.x + Math.round(0.0f * (this.getWidth() - textRenderer.getWidth(currentText)).toFloat())
        val j = this.y + (this.getHeight() - 9) / 2
        context?.drawTextWithShadow(textRenderer, currentText, i, j, this.textColor)
        textureConfig.apply {
            context?.drawTexture(
                texture,
                x - 15,
                y,
                u.toFloat(),
                v.toFloat(),
                sizeWidth,
                sizeHeight,
                textureWidth,
                textureHeight
            )
        }
    }

}