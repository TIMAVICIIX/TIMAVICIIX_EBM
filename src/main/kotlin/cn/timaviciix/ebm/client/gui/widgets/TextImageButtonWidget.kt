/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui.widgets
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-14  23:58
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui.widgets

import cn.timaviciix.ebm.client.gui.GUIConfig
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.text.Text

class TextImageButtonWidget(
    private val textRenderer: TextRenderer,
    private val textureConfig: GUIConfig.BtnTextureConfig,
    private val positionX: Int,
    private val positionY: Int,
    private val widgetWidth: Int,
    private val widgetHeight: Int,
    private val text: Text,
    private val onClick: () -> Unit
) : ButtonWidget(
    positionX,
    positionY,
    widgetWidth,
    widgetHeight,
    Text.empty(),
    { onClick() },
    DEFAULT_NARRATION_SUPPLIER
) {

    override fun renderButton(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        val currentTexture = if (isHovered) textureConfig.hoverTexture else textureConfig.normalTexture

        context?.apply {
            drawTexture(
                currentTexture,
                positionX, positionY,
                textureConfig.u.toFloat(), textureConfig.v.toFloat(),
                widgetWidth, widgetHeight,
                textureConfig.textureWidth, textureConfig.textureHeight
            )

            drawCenterText(this)
        }
    }

    private fun drawCenterText(context: DrawContext) {
        val i =
            this.x + Math.round(0.5f * (this.getWidth() - textRenderer.getWidth(text)).toFloat())
        val j = this.y + (this.getHeight() - 9) / 2
        context.drawText(textRenderer, text, i, j, GUIConfig.blackTextColor4.rgb, false)
    }

}