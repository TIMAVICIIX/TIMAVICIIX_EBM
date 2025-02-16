/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-16  12:34
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui

import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text

abstract class BaseScreen(title: Text): Screen(title) {

    abstract fun openOperations()

    abstract fun changeOperations()

    abstract fun doneOperations()

    abstract fun closeOperations()

}
