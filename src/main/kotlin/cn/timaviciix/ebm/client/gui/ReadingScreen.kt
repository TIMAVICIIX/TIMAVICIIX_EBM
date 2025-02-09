/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-31  15:22
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui

import cn.timaviciix.ebm.util.GlobalData
import io.wispforest.owo.ui.base.BaseUIModelScreen
import io.wispforest.owo.ui.component.ButtonComponent
import io.wispforest.owo.ui.component.LabelComponent
import io.wispforest.owo.ui.container.FlowLayout
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.util.Identifier


class ReadingScreen(
    private val openOperation: () -> Unit = {},
    private val changePageOperation: () -> String? = { null },
    private val closeOperation: () -> Unit = {},
) :
    BaseUIModelScreen<FlowLayout>(
        FlowLayout::class.java,
        DataSource.asset(Identifier(GlobalData.MOD_ID, "reading_ui"))
    ) {

    private val textFont = Identifier(GlobalData.MOD_ID, "large_reading")


    override fun build(p0: FlowLayout?) {
        openOperation()
        p0?.let {


            p0.childById(ButtonComponent::class.java, "BackBtn")?.onPress {
                close()
            }
            p0.childById(LabelComponent::class.java, "TextDisplayLabelLeft")?.apply {
                text(
                    Text.translatable("text.timaviciix_ebm.example_display")
                        .setStyle(
                            Style.EMPTY.withColor(TextColor.fromRgb(0x000000))
//                                .withFont(textFont)

                        )
                )
            }
            p0.childById(LabelComponent::class.java, "TextDisplayLabelRight")?.apply {
                text(
                    Text.translatable("text.timaviciix_ebm.example_display")
                        .setStyle(
                            Style.EMPTY.withColor(TextColor.fromRgb(0x000000))
//                                .withFont(textFont)

                        )
                )
            }

            p0.childById(ButtonComponent::class.java, "inspector-button")
                ?.onPress { uiAdapter.toggleInspector() }
        }
    }

    //TODO
    override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        return super.keyPressed(keyCode, scanCode, modifiers)
    }

    override fun close() {
        closeOperation()
        super.close()
    }

}