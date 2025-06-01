/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui.widgets.musk
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-30  20:18
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui.widgets.screenmusk

import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder
import net.minecraft.client.gui.widget.ClickableWidget
import net.minecraft.text.Text
import java.awt.Color

class ScreenMuskWidget(
    private val x: Int,
    private val y: Int,
    private val width: Int,
    private val height: Int,
    private val framePlayer: FramePlayWidget,
    private val frameText: Text,
    private val textRenderer: TextRenderer
) : ClickableWidget(x, y, width, height, Text.empty()) {

    override fun appendClickableNarrations(builder: NarrationMessageBuilder?) {
    }

    override fun renderButton(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        context?.let {
            it.fillGradient(0, 0, width, height, -1072689136, -804253680)
            framePlayer.render(context, width / 2, height / 2)

            val i =
                this.x + Math.round(0.5f * (this.getWidth() - textRenderer.getWidth(frameText)).toFloat())
            val j = this.y + (this.getHeight() - 9) / 2
            it.drawText(textRenderer, frameText, i, j + 25, Color.WHITE.rgb, false)
        }
    }


}