/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-10  00:09
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui

import cn.timaviciix.ebm.util.GlobalData
import io.wispforest.owo.ui.base.BaseUIModelScreen
import io.wispforest.owo.ui.component.ButtonComponent
import io.wispforest.owo.ui.component.LabelComponent
import io.wispforest.owo.ui.component.TextAreaComponent
import io.wispforest.owo.ui.container.FlowLayout
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class WritingScreen(
    private val openOperation: () -> Unit = {},
    private val changePageOperation: () -> String? = { null },
    private val saveOperation: () -> Unit = {},
    private val closeOperation: () -> Unit = {}
) :
    BaseUIModelScreen<FlowLayout>(
        FlowLayout::class.java,
        DataSource.asset(Identifier(GlobalData.MOD_ID, "writing_ui"))
    ) {

    private lateinit var displayLabel: LabelComponent
    private lateinit var textWriteLabel: TextAreaComponent

    override fun shouldPause(): Boolean {
        return false
    }

    override fun build(p0: FlowLayout?) {
        openOperation()
        p0?.let {

            p0.childById(ButtonComponent::class.java, "BackBtn")?.onPress {
                close()
            }

            p0.childById(ButtonComponent::class.java, "inspector-button")
                ?.onPress { uiAdapter.toggleInspector() }

            displayLabel = p0.childById(LabelComponent::class.java, "TextDisplayLabelLeft")!!

            textWriteLabel = p0.childById(TextAreaComponent::class.java, "TextWriteLabelRight")!!

            textWriteLabel.apply {
                onChanged().subscribe(TextAreaComponent.OnChanged {
                    if (it.isNotEmpty() && !it.isNullOrBlank() && it.isNotBlank()) {
                        displayLabel.text(
                            Text.literal(it)
                                .setStyle(
                                    Style.EMPTY.withColor(GUIConfig.blackTextColor4)
//                                .withFont(textFont)
                                )
                        )
                    } else {
                        displayLabel.text(Text.literal(" "))
                    }
                })

            }
        }

    }

    fun printUnicodeCodes(input: String) {
        input.forEach { char ->
            // 输出 Unicode 编码
            GlobalData.LOGGER.info("\\u${char.code.toString(16).padStart(4, '0')}")
        }
    }

    private fun save() {
        saveOperation()
    }

    private fun changePage() {
        changePageOperation()
    }


    override fun close() {
        closeOperation()
        super.close()
    }
}