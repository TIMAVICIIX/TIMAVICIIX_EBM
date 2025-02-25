/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui.widgets
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-17  14:14
 *@Description: 左中右三宫格背景渲染TextField
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui.widgets

import cn.timaviciix.ebm.client.gui.GUIConfig
import cn.timaviciix.ebm.deploy.TextFieldWidgetDeploy
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class ThreePatchHorizontalFieldWidget(
    private val textureConfig: GUIConfig.ThreePatchTextureConfig,
    private val textRenderer: TextRenderer,
    private val positionX: Int,
    private val positionY: Int,
    private val widgetWidth: Int,
    private val widgetHeight: Int,
    private val checkSaveOperation: () -> Unit,
    private val bufferStringChecker: (String) -> Unit = {},
    private val widgetEditable: Boolean = false,
    private val text: Text = Text.empty()
) : TextFieldWidgetDeploy(
    textRenderer,
    positionX + textureConfig.sideWidth,
    positionY,
    widgetWidth,
    widgetHeight,
    text
) {

    private val editableColor = GUIConfig.blackTextColor4.rgb
    private val uneditableColor = GUIConfig.blackTextColorPure.rgb
    private var outerEditable = false
    private fun toggleOuterEditable() {
        outerEditable = !outerEditable
        setEditable(outerEditable)
    }

    private fun setOuterEditable() {
        outerEditable = true
        setEditable(true)
    }

    init {
        setEditableColor(editableColor)
        setUneditableColor(uneditableColor)
        setEditable(false)
        super.setDrawsBackground(false)
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

    override fun onClick(mouseX: Double, mouseY: Double) {
        if (widgetEditable) {
            setOuterEditable()
            super.onClick(mouseX, mouseY)
        }
    }

    override fun setFocused(focused: Boolean) {
        if (!focused && getText().isEmpty()) {
            bufferStringChecker(Text.translatable("data.timaviciix_ebm.book_name_unknown").string)
            setText(Text.translatable("data.timaviciix_ebm.book_name_unknown").string)
        }
        super.setFocused(focused)
    }


    fun onScreenClick(mouseX: Double, mouseY: Double) {
        if (this.isFocused) {
            if (mouseX < x || mouseY < y || mouseX > widgetWidth + x || mouseY > widgetHeight + y) {
                saveBus()
            }
        }
    }

    override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        if (keyCode == 257 || keyCode == 335) {
            saveBus()
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

    private fun saveBus() {
        this.isFocused = false
        setEditable(false)
        checkSaveOperation()
    }

}