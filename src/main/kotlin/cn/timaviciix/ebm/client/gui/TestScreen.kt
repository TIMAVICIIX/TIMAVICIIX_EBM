/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-11  01:04
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui

import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.EditBoxWidget
import net.minecraft.client.gui.widget.TextFieldWidget
import net.minecraft.text.Text

class TestScreen(
    private val openOperation: () -> Unit = {},
    private val changePageOperation: () -> String? = { null },
    private val saveOperation: () -> Unit = {},
    private val closeOperation: () -> Unit = {}
) : Screen(Text.literal("原生UI示例")) {

    private lateinit var textField: TextFieldWidget
    private var scrollOffset = 0f

    override fun shouldPause(): Boolean {
        return false
    }

    override fun init() {
        openOperation()

        textField = addDrawableChild(
            TextFieldWidget(
                textRenderer,
                width / 2 - 100, 50, 200, 20,
                Text.literal("输入框")
            ).apply {
                setChangedListener {
                    GlobalData.LOGGER.info(it)
                }
            }
        )

        addDrawableChild(
            EditBoxWidget(
                textRenderer, width / 2 - 100, 100, 200, 200, Text.literal(""), Text.literal("")
            )
        )

        // 添加按钮
        addDrawableChild(
            ButtonWidget.builder(Text.literal("保存")) {
                saveData()
                close()
            }.position(width / 2 - 50, height - 30)
                .size(100, 20)
                .build()
        )
    }

    private fun saveData() {
        val text = textField.text
        // 保存逻辑...
    }

    override fun close() {
        closeOperation()
        super.close()
    }
}