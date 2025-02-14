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
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.text.Text
import net.minecraft.util.math.MathHelper

class TextImageButtonWidget(
    private val textureConfig: GUIConfig.BtnTextureConfig,
    private val positionX: Int,
    private val positionY: Int,
    private val widgetWidth: Int,
    private val widgetHeight: Int,
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
        val minecraftClient = MinecraftClient.getInstance()
        context!!.setShaderColor(1.0f, 1.0f, 1.0f, this.alpha)
        RenderSystem.enableBlend()
        RenderSystem.enableDepthTest()
        context.drawNineSlicedTexture(
            textureConfig.normalTexture,
            this.x,
            this.y,
            this.getWidth(),
            this.getHeight(), 20, 4, 200, 20, textureConfig.u, textureConfig.v
        )
        context.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        val i = if (this.active) 16777215 else 10526880
        this.drawMessage(context, minecraftClient.textRenderer, i or (MathHelper.ceil(this.alpha * 255.0f) shl 24))
    }

}