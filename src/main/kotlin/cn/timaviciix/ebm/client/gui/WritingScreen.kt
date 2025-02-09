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
import io.wispforest.owo.ui.container.FlowLayout
import net.minecraft.util.Identifier

class WritingScreen(
    private val openOperation: () -> Unit = {},
    private val changePageOperation: () -> Unit = {},
    private val saveOperation: () -> Unit = {},
    private val closeOperation: () -> Unit = {}
) :
    BaseUIModelScreen<FlowLayout>(
        FlowLayout::class.java,
        DataSource.asset(Identifier(GlobalData.MOD_ID, "writing_ui"))
    ) {

    override fun build(p0: FlowLayout?) {
        openOperation()
        p0?.let {



        }

    }

    private fun save(){
        saveOperation()
    }

    private fun changePage(){
        changePageOperation()
    }



    override fun close() {
        closeOperation()
        super.close()
    }
}