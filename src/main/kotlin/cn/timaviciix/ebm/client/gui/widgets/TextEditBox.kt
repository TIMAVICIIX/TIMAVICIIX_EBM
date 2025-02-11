/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui.widgets
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-12  00:39
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui.widgets

import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.widget.EditBoxWidget
import net.minecraft.text.Text

class TextEditBox(
    private val positionX: Int,
    private val positionY: Int,
    private val widgetWidth: Int,
    private val widgetHeight: Int,
    private val textRenderer: TextRenderer,
    private val placeholder: Text = Text.empty(),
    private val initMessage: Text = Text.empty()
) : EditBoxWidget(textRenderer, positionX, positionY, widgetWidth, widgetHeight, placeholder, initMessage) {

    override fun mouseScrolled(mouseX: Double, mouseY: Double, amount: Double): Boolean = false

    override fun setText(text: String?) {
        super.setText(text)
    }

}