package cn.timaviciix.ebm.mixin.gui;


import cn.timaviciix.ebm.client.gui.GUIConfig;
import cn.timaviciix.ebm.util.GlobalData;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BiFunction;

@Mixin(value = TextFieldWidget.class,priority = 1000000000)

public abstract class TitleTextFieldWidgetMixin {

    @Shadow
    private BiFunction<String, Integer, OrderedText> renderTextProvider;
    @Shadow
    private boolean editable;


    @Inject(method = "drawsBackground", at = @At("HEAD"), cancellable = true)
    private void toggleBackground(CallbackInfoReturnable<Boolean> cir) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }

    @Redirect(method = "renderButton", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/OrderedText;III)I", ordinal = 0))
    private int drawWithoutShadowText0(DrawContext instance, TextRenderer textRenderer, OrderedText text, int x, int y, int color) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) {
            checkEditable();
            return instance.drawText(textRenderer, text, x, y, color, false);
        } else {
            return instance.drawTextWithShadow(textRenderer, text, x, y, color);
        }
    }

    @Redirect(method = "renderButton", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/OrderedText;III)I", ordinal = 1))
    private int drawWithoutShadowText1(DrawContext instance, TextRenderer textRenderer, OrderedText text, int x, int y, int color) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) {
            checkEditable();
            return instance.drawText(textRenderer, text, x, y, color, false);
        } else {
            return instance.drawTextWithShadow(textRenderer, text, x, y, color);
        }
    }

    @Redirect(method = "renderButton", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)I", ordinal = 0))
    private int drawTextWithoutShadow2(DrawContext instance, TextRenderer textRenderer, Text text, int x, int y, int color) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) {
            GlobalData.INSTANCE.getLOGGER().info("Text Class text start render!");
            return instance.drawText(textRenderer, text, x, y, color, false);
        } else {
            return instance.drawTextWithShadow(textRenderer, text, x, y, color);
        }
    }

    @Redirect(method = "renderButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)I", ordinal = 0))
    private int drawTextWithoutShadow3(DrawContext instance, TextRenderer textRenderer, String text, int x, int y, int color) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) {
            return instance.drawText(textRenderer, text, x, y, color, false);
        } else {
            return instance.drawTextWithShadow(textRenderer, text, x, y, color);
        }
    }

    @Redirect(method = "renderButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)I", ordinal = 1))
    private int drawTextWithoutShadow4(DrawContext instance, TextRenderer textRenderer, String text, int x, int y, int color) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) {
            return instance.drawText(textRenderer, text, x, y, color, false);
        } else {
            return instance.drawTextWithShadow(textRenderer, text, x, y, color);
        }
    }

    @Unique
    private void checkEditable(){
        if (editable) {
            this.renderTextProvider = (string, firstCharacterIndex) -> OrderedText.styledForwardsVisitedString(
                    string, Style.EMPTY
            );
        }else{
            this.renderTextProvider = (string, firstCharacterIndex) -> OrderedText.styledForwardsVisitedString(
                    string, Style.EMPTY.withItalic(true)
            );
        }
    }

}
