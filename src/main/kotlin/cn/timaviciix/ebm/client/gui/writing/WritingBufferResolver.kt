/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui.writing
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-24  07:34
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui.writing

import cn.timaviciix.ebm.client.gui.widgets.EditTextWidget
import cn.timaviciix.ebm.data.data_configs.DefaultData
import cn.timaviciix.ebm.data.templates.package_templates.WarpedBookData
import cn.timaviciix.ebm.puppets.EditBoxPuppet

class WritingBufferResolver(
    var warpedBookData: WarpedBookData,
    var titleFieldBuffer: String = "",
    var leftFieldBuffer: String = "",
    var rightFieldBuffer: String = "",
) {
    private var nowClipboardFieldBuffer: String = ""

    private lateinit var leftEditWidget: EditTextWidget
    private lateinit var rightEditWidget: EditTextWidget

    init {
        //书本UI存在两个展示页面，也就是说当前页面应该存在两个页码，分别是左半页与右半页
        val currentPage = warpedBookData.lastReadingPage.safetyGetValue(DefaultData.START_PAGE_COUNT)

    }

    fun injectLeftWidget(widget: EditTextWidget) {
        leftEditWidget = widget.apply {
            text = leftFieldBuffer
            setChangeListener { leftFieldBuffer = it }
        }
    }

    fun injectRightWidget(widget: EditTextWidget) {
        rightEditWidget = widget.apply {
            text = rightFieldBuffer
            setChangeListener { rightFieldBuffer = it }
        }
    }

    fun onClipboard(massiveText: String, textLineIndexList: List<EditBoxPuppet.Substring>) {

    }

    fun cleanContentBuffer() {
        leftFieldBuffer = ""
        rightFieldBuffer = ""
    }

    fun cleanClipboardField() {
        nowClipboardFieldBuffer = ""
    }


}