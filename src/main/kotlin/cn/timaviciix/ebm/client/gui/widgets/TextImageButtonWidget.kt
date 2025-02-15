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
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.text.Text

class TextImageButtonWidget(
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
        val currentTexture = if (isHovered) textureConfig.normalTexture else textureConfig.hoverTexture

        context?.apply {
            drawTexture(
                currentTexture,
                positionX, positionY,
                textureConfig.u.toFloat(), textureConfig.v.toFloat(),
                widgetWidth, widgetHeight,
                textureConfig.textureWidth, textureConfig.textureHeight
            )

            drawText(
                MinecraftClient.getInstance().textRenderer,
                text,positionX,positionY,GUIConfig.blackTextColor4.rgb,false)
        }
    }

}