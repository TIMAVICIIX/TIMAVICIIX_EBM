package cn.timaviciix.ebm.util

import cn.timaviciix.ebm.client.gui.config.GUIConfig
import net.minecraft.text.Style
import software.bernie.geckolib.core.`object`.Color

object StyleUtil {

    val GRAY_COLOR_STYLE: Style = Style.EMPTY.withColor(Color.GRAY.color)
    val RED_COLOR_STYLE: Style = Style.EMPTY.withColor(Color.RED.color)
    val DEEP_BLUE_COLOR_STYLE: Style = Style.EMPTY.withColor(Color.BLUE.color)
    val WITHE_COLOR_STYLE: Style = Style.EMPTY.withColor(Color.WHITE.color)

    val BLACK4_BOLD_STYLE:Style = Style.EMPTY.withColor(GUIConfig.blackTextColor4).withBold(true)
    val BLACK4_STYLE:Style = Style.EMPTY.withColor(GUIConfig.blackTextColor4)



}