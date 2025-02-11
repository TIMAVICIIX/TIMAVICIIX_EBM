/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui.widgets
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-11  17:45
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui.widgets

import cn.timaviciix.ebm.client.gui.GUIConfig
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.text.Text

class ImageButtonWidget(
    private val textureConfig: GUIConfig.BtnTextureConfig,
    private val positionX: Int,
    private val positionY: Int,
    private val widgetWidth: Int,
    private val widgetHeight: Int,
    private val onClick: () -> Unit
) : ButtonWidget(
    positionX, positionY, widgetWidth, widgetHeight, Text.empty(), { onClick() }, DEFAULT_NARRATION_SUPPLIER
) {
    override fun renderButton(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        val readyTexture = if (isHovered) {
            textureConfig.hoverTexture
        } else {
            textureConfig.normalTexture
        }

        drawTexture(
            context,
            readyTexture,
            positionX, positionY,
            textureConfig.u, textureConfig.v, 0,
            textureConfig.sizeWidth, textureConfig.sizeHeight,
            textureConfig.textureWidth, textureConfig.textureHeight
        )
    }

}