package cn.timaviciix.ebm.mixin.gui;


import cn.timaviciix.ebm.client.gui.config.GUIConfig;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.MultilineTextWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MultilineTextWidget.class)
public class DisplayBoxMixin {

    @Redirect(method = "renderButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/MultilineText;drawWithShadow(Lnet/minecraft/client/gui/DrawContext;IIII)I", ordinal = 0))
    private int redirectDraw(MultilineText instance, DrawContext drawContext, int i, int j, int k, int l) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) {
            return instance.draw(drawContext, i, j, k, l);
        } else {
            return instance.drawWithShadow(drawContext, i, j, k, l);
        }
    }
}
 