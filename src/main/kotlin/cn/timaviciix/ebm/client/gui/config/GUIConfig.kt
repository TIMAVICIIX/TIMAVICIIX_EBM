package cn.timaviciix.ebm.client.gui.config

import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.util.Identifier

object GUIConfig {

    val blackTextColor4: TextColor = TextColor.fromRgb(0x444444)
    val blackTextColorPure: TextColor = TextColor.fromRgb(0x000000)

    data class BtnTextureSets(
        val normalTexture: Identifier

    )

    object Textures {
        val READING_WRITING_GUI_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/gui/reading_gui.png")

        val CLOSE_BTN_WIDGET_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/gui/widget/btn/close_btn_widget.png")
        val CLOSE_BTN_WIDGET_HOVER_TEXTURE =
            Identifier(GlobalData.MOD_ID, "textures/gui/widget/btn/hover/close_btn_widget_hover.png")

        val SETTING_BTN_WIDGET_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/gui/widget/btn/setting_btn_widget.png")
        val SETTING_BTN_WIDGET_HOVER_TEXTURE =
            Identifier(GlobalData.MOD_ID, "textures/gui/widget/btn/hover/setting_btn_widget_hover.png")

        val NETWORK_BTN_WIDGET_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/gui/widget/btn/network_btn_widget.png")
        val NETWORK_BTN_WIDGET_HOVER_TEXTURE =
            Identifier(GlobalData.MOD_ID, "textures/gui/widget/btn/hover/network_btn_widget_hover.png")

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

        val TOP_SLIDE_NOTICE_WIDGET_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/gui/notice/background_notice.png")
        val NOTICE_SAVE_ICON_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/gui/notice/save_notice.png")
        val NOTICE_INFO_ICON_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/gui/notice/info_notice.png")
        val NOTICE_SUCCESS_ICON_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/gui/notice/success_notice.png")
        val NOTICE_fAILED_ICON_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/gui/notice/failed_notice.png")
        val NOTICE_WAIT_ICON_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/gui/notice/wait_notice.png")

        val READ_LEVEL_WIDGET_TEXTURE =
            Identifier(GlobalData.MOD_ID, "textures/gui/widget/component/read_level_widget.png")

        val WRITE_LEVEL_WIDGET_TEXTURE =
            Identifier(GlobalData.MOD_ID, "textures/gui/widget/component/write_level_widget.png")
    }

    object FrameTextures {
        val BOOK_LOADING_FRAME_TEXTURES = listOf(
            Identifier(GlobalData.MOD_ID, "textures/gui/frame/loading/book_data_loading_frame1.png"),
            Identifier(GlobalData.MOD_ID, "textures/gui/frame/loading/book_data_loading_frame2.png"),
            Identifier(GlobalData.MOD_ID, "textures/gui/frame/loading/book_data_loading_frame3.png"),
            Identifier(GlobalData.MOD_ID, "textures/gui/frame/loading/book_data_loading_frame4.png"),
            Identifier(GlobalData.MOD_ID, "textures/gui/frame/loading/book_data_loading_frame5.png"),
            Identifier(GlobalData.MOD_ID, "textures/gui/frame/loading/book_data_loading_frame6.png")
        )
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
//        var listener: ((Int) -> Unit)? = null
//
//        var wrapLineCount by Delegates.observable(1) { _, _, newValue ->
//            LOGGER.info("[Mixin]Lines Changed: $newValue")
//            listener?.invoke(newValue)
//        }

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

    data class ThreePatchTextureConfig(
        val texture: Identifier,
        val u: Int,
        val v: Int,
        val textureWidth: Int,
        val textureHeight: Int,
        val sideWidth: Int,
        val middleOffset: Int
    )

    data class FrameTexturesConfig(
        val textures: List<Identifier>,
        val u: Float,
        val v: Float,
        val textureWidth: Int,
        val textureHeight: Int,
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

    val SETTING_BUTTON_TEXTURE_SET = BtnTextureConfig(
        Textures.SETTING_BTN_WIDGET_TEXTURE,
        0, 0,
        20, 20,
        20, 20,
        hoverTexture = Textures.SETTING_BTN_WIDGET_HOVER_TEXTURE
    )

    val NETWORK_BUTTON_TEXTURE_SET = BtnTextureConfig(
        Textures.NETWORK_BTN_WIDGET_TEXTURE,
        0, 0,
        20, 20,
        20, 20,
        hoverTexture = Textures.NETWORK_BTN_WIDGET_HOVER_TEXTURE
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

    val TITLE_THREE_PATCH_TEXTURE_SET = ThreePatchTextureConfig(
        Textures.TITLE_WIDGET_TEXTURE,
        0, 0,
        91, 20,
        14, 63
    )

    val LOADING_FRAME_TEXTURE_SET = FrameTexturesConfig(
        FrameTextures.BOOK_LOADING_FRAME_TEXTURES,
        0f, 0f,
        32, 32
    )

    val SAVE_NOTICE_ICON_TEXTURE_SET = NormalTextureConfig(
        Textures.NOTICE_SAVE_ICON_TEXTURE,
        0, 0,
        12, 12,
        12, 12
    )
    val INFO_NOTICE_ICON_TEXTURE_SET = NormalTextureConfig(
        Textures.NOTICE_INFO_ICON_TEXTURE,
        0, 0,
        12, 12,
        12, 12
    )
    val SUCCESS_NOTICE_ICON_TEXTURE_SET = NormalTextureConfig(
        Textures.NOTICE_SUCCESS_ICON_TEXTURE,
        0, 0,
        12, 12,
        12, 12
    )
    val FAILED_NOTICE_ICON_TEXTURE_SET = NormalTextureConfig(
        Textures.NOTICE_fAILED_ICON_TEXTURE,
        0, 0,
        12, 12,
        12, 12
    )
    val WAIT_NOTICE_ICON_TEXTURE_SET = NormalTextureConfig(
        Textures.NOTICE_WAIT_ICON_TEXTURE,
        0, 0,
        12, 12,
        12, 12
    )
    val NOTICE_THREE_PATCH_TEXTURE_SET = ThreePatchTextureConfig(
        Textures.TOP_SLIDE_NOTICE_WIDGET_TEXTURE,
        0, 0,
        102, 24,
        6, 90
    )

}