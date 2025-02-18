package cn.timaviciix.ebm.mixin.gui;

import cn.timaviciix.ebm.client.gui.GUIConfig;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.EditBoxWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EditBoxWidget.class)
public abstract class EditBoxWidgetTextColorMixin {
    /**
     * 修改普通文本颜色（明确指定参数索引）
     */
    @ModifyArg(
            method = "renderContents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)I",
                    ordinal = 0
            ),
            index = 4
    )
    private int modifyTextColor0(int color) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) {
            return 0x444444;
        } else {
            return color;
        }
    }

    @ModifyArg(
            method = "renderContents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)I",
                    ordinal = 1
            ),
            index = 4
    )
    private int modifyTextColor1(int color) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) {
            return 0x444444;
        } else {
            return color;
        }
    }

    @ModifyArg(
            method = "renderContents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)I",
                    ordinal = 2
            ),
            index = 4
    )
    private int modifyTextColor2(int color) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) {
            return 0x444444;
        } else {
            return color;
        }
    }

    @ModifyArg(
            method = "renderContents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)I",
                    ordinal = 3
            ),
            index = 4
    )
    private int modifyTextColor3(int color) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) {
            return 0x444444;
        } else {
            return color;
        }
    }

    @Redirect(
            method = "renderContents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)I",
                    ordinal = 0
            )
    )
    private int redirectDrawTextWithShadow0(DrawContext instance, TextRenderer textRenderer, String text, int x, int y, int color) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) {
            return instance.drawText(textRenderer, text, x, y, 0x444444, false) - 1;
        }else{
            return instance.drawTextWithShadow(textRenderer, text, x, y, color) - 1;
        }
    }

    @Redirect(
            method = "renderContents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)I",
                    ordinal = 1
            )
    )
    private int redirectDrawTextWithShadow1(DrawContext instance, TextRenderer textRenderer, String text, int x, int y, int color) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) {
            return instance.drawText(textRenderer, text, x, y, 0x444444, false);
        }else{
            return instance.drawTextWithShadow(textRenderer, text, x, y, color);
        }
    }

    @Redirect(
            method = "renderContents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)I",
                    ordinal = 2
            )
    )
    private int redirectDrawTextWithShadow2(DrawContext instance, TextRenderer textRenderer, String text, int x, int y, int color) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) {
            return instance.drawText(textRenderer, text, x, y, 0x444444, false) - 1;
        }else{
            return instance.drawTextWithShadow(textRenderer, text, x, y, color) - 1;
        }
    }

    @Redirect(
            method = "renderContents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)I",
                    ordinal = 3
            )
    )
    private int redirectDrawTextWithShadow3(DrawContext instance, TextRenderer textRenderer, String text, int x, int y, int color) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) {
            return instance.drawText(textRenderer, text, x, y, 0x444444, false);
        }else{
            return instance.drawTextWithShadow(textRenderer, text, x, y, color);
        }
    }
}