package cn.timaviciix.ebm.client.gui

import cn.timaviciix.ebm.util.GlobalData
import cn.timaviciix.ebm.util.GlobalData.LOGGER
import com.google.common.math.IntMath
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.util.Identifier
import kotlin.properties.Delegates

object GUIConfig {

    val blackTextColor4: TextColor = TextColor.fromRgb(0x444444)

    data class BtnTextureSets(
        val normalTexture: Identifier

    )

    object Textures {
        val READING_WRITING_GUI_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/gui/reading_gui.png")

        val CLOSE_BTN_WIDGET_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/gui/widget/btn/close_btn_widget.png")
        val CLOSE_BTN_WIDGET_HOVER_TEXTURE =
            Identifier(GlobalData.MOD_ID, "textures/gui/widget/btn/hover/close_btn_widget_hover.png")

        val NEXT_BTN_WIDGET_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/gui/widget/btn/next_btn_widget.png")
        val NEXT_BTN_WIDGET_HOVER_TEXTURE =
            Identifier(GlobalData.MOD_ID, "textures/gui/widget/btn/hover/next_btn_widget_hover.png")

        val PREVIEW_BTN_WIDGET_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/gui/widget/btn/preview_btn_widget.png")
        val PREVIEW_BTN_WIDGET_HOVER_TEXTURE =
            Identifier(GlobalData.MOD_ID, "textures/gui/widget/btn/hover/preview_btn_widget_hover.png")

        val SHORT_TEXT_BTN_WIDGET_TEXTURE =
            Identifier(GlobalData.MOD_ID, "textures/gui/widget/btn/short_text_btn_widget.png")
        val SHORT_TEXT_BTN_WIDGET_HOVER_TEXTURE =
            Identifier(GlobalData.MOD_ID, "textures/gui/widget/btn/hover/short_text_btn_widget_hover.png")

        val TIPS_WIDGET_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/gui/widget/component/tips_widget.png")

        val TITLE_WIDGET_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/gui/widget/component/title_widget.png")

        val READ_LEVEL_WIDGET_TEXTURE =
            Identifier(GlobalData.MOD_ID, "textures/gui/widget/component/read_level_widget.png")

        val WRITE_LEVEL_WIDGET_TEXTURE =
            Identifier(GlobalData.MOD_ID, "texture/gui/widget/component/write_level_widget.png")
    }

    object Fonts {
        val BOOK_FONT = Identifier(GlobalData.MOD_ID, "large_reading")
    }

    val TIPS_TEXT_SET = mutableSetOf<Text>().apply {
        for (i in 1..7) {
            add(Text.translatable("tips.timaviciix_ebm.tips_desc_$i"))
        }
    }

    object BufferFromMixin {
        var listener: ((Int) -> Unit)? = null

        var wrapLineCount by Delegates.observable(1) { _, _, newValue ->
            LOGGER.info("Lines Changed: $newValue")
            listener?.invoke(newValue)
        }

        var screenMixinLaunch = false
        fun toggleMixin() {
            screenMixinLaunch = screenMixinLaunch != true
        }
    }

    data class NormalTextureConfig(
        val texture: Identifier,
        val u: Int,
        val v: Int,
        val sizeWidth: Int,
        val sizeHeight: Int,
        val textureWidth: Int,
        val textureHeight: Int,
    )

    data class BtnTextureConfig(
        val normalTexture: Identifier,
        val u: Int,
        val v: Int,
        val sizeWidth: Int,
        val sizeHeight: Int,
        val textureWidth: Int,
        val textureHeight: Int,
        val hoverTexture: Identifier = normalTexture,
        val pressTexture: Identifier = normalTexture,
    )

    data class NineBlockTextureConfig(
        val texture: Identifier,
        val u: Int,
        val v: Int
    )

    val READING_GUI_TEXTURE_SET = NormalTextureConfig(
        Textures.READING_WRITING_GUI_TEXTURE,
        0, 0,
        270, 201,
        270, 201
    )

    val CLOSE_BUTTON_TEXTURE_SET = BtnTextureConfig(
        Textures.CLOSE_BTN_WIDGET_TEXTURE,
        0, 0,
        20, 20,
        20, 20,
        hoverTexture = Textures.CLOSE_BTN_WIDGET_HOVER_TEXTURE
    )

    val NEXT_BUTTON_TEXTURE_SET = BtnTextureConfig(
        Textures.NEXT_BTN_WIDGET_TEXTURE,
        0, 0, 20, 20,
        20, 20,
        hoverTexture = Textures.NEXT_BTN_WIDGET_HOVER_TEXTURE
    )

    val PREVIEW_BUTTON_TEXTURE_SET = BtnTextureConfig(
        Textures.PREVIEW_BTN_WIDGET_TEXTURE,
        0, 0, 20, 20,
        20, 20,
        hoverTexture = Textures.PREVIEW_BTN_WIDGET_HOVER_TEXTURE
    )

    val SHORT_TEXT_BTN_TEXTURE_SET = BtnTextureConfig(
        Textures.SHORT_TEXT_BTN_WIDGET_TEXTURE,
        0, 0, 36, 20,
        36, 20,
        hoverTexture = Textures.SHORT_TEXT_BTN_WIDGET_HOVER_TEXTURE
    )

    val TIPS_WIDGET_TEXTURE_SET = NormalTextureConfig(
        Textures.TIPS_WIDGET_TEXTURE,
        0, 0,
        12, 16,
        12, 16
    )

}