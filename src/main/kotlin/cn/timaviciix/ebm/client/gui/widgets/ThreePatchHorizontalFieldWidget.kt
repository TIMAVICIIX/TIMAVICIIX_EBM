/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui.widgets
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-17  14:14
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui.widgets

import cn.timaviciix.ebm.client.gui.GUIConfig
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.widget.TextFieldWidget
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class ThreePatchHorizontalFieldWidget(
    private val textureConfig: GUIConfig.ThreePatchTextureConfig,
    private val textRenderer: TextRenderer,
    private val positionX: Int,
    private val positionY: Int,
    private val widgetWidth: Int,
    private val widgetHeight: Int,
    private val widgetEditable: Boolean = false,
    private val text: Text = Text.empty()
) : TextFieldWidget(textRenderer, positionX, positionY, widgetWidth, widgetHeight, text) {

    init {
        setEditableColor(GUIConfig.blackTextColor4.rgb)
        setUneditableColor(GUIConfig.blackTextColorPure.rgb)
        setEditable(widgetEditable)
    }

    override fun drawTexture(
        context: DrawContext?,
        texture: Identifier?,
        x: Int,
        y: Int,
        u: Int,
        v: Int,
        hoveredVOffset: Int,
        width: Int,
        height: Int,
        textureWidth: Int,
        textureHeight: Int
    ) {
        val textWidth = if (textRenderer.getWidth(getText()) < textureConfig.middleOffset) {
            textureConfig.middleOffset
        } else {
            textRenderer.getWidth(getText())
        }
        RenderSystem.enableDepthTest()
        //Background Part1 Renderer
        context!!.drawTexture(
            texture,
            x,
            y,
            width,
            height,
            u.toFloat(),
            v.toFloat(),
            textureConfig.sideWidth,
            textureConfig.textureHeight,
            textureWidth,
            textureHeight
        )
        //Background Part2 Renderer
        context.drawTexture(
            texture,
            x + textureConfig.sideWidth,
            y,
            textWidth,
            height,
            (textureConfig.sideWidth + u).toFloat(),
            v.toFloat(),
            textureConfig.textureWidth - (textureConfig.sideWidth * 2),
            textureConfig.textureHeight,
            textureWidth,
            textureHeight
        )

        //Background Part3 Render
        context.drawTexture(
            texture,
            x + textWidth + textureConfig.sideWidth,
            y,
            width,
            height,
            (textureConfig.sideWidth + textureConfig.middleOffset).toFloat(),
            v.toFloat(),
            textureConfig.sideWidth,
            textureConfig.textureHeight,
            textureWidth,
            textureHeight
        )
    }

    override fun setDrawsBackground(drawsBackground: Boolean) {
        super.setDrawsBackground(false)
    }

    fun onScreenClick(mouseX: Double, mouseY: Double) {
        if (widgetEditable) {
            if (mouseX < x || mouseY < y || mouseX > widgetWidth + x || mouseY > widgetHeight + y) {
                this.isFocused = false
            }
        }
    }

    override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        if (keyCode == 257 || keyCode == 335) {
            this.isFocused = false
        }
        return super.keyPressed(keyCode, scanCode, modifiers)
    }

    override fun renderButton(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        context?.let {
            drawTexture(
                it,
                textureConfig.texture,
                positionX,
                positionY,
                textureConfig.u,
                textureConfig.v,
                0,
                textureConfig.sideWidth,
                widgetHeight,
                textureConfig.textureWidth,
                textureConfig.textureHeight
            )
        }
        super.renderButton(context, mouseX, mouseY, delta)
    }


}