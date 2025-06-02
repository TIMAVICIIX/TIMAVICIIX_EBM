package cn.timaviciix.ebm.mixin.gui;

import cn.timaviciix.ebm.client.gui.config.GUIConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ScrollableWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScrollableWidget.class)
public abstract class ScrollableWidgetMixin {

    @Inject(method = "drawBox(Lnet/minecraft/client/gui/DrawContext;)V", at = @At("HEAD"), cancellable = true)
    private void drawBoxInject1(DrawContext context, CallbackInfo ci) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) ci.cancel();
    }

    @Inject(method = "drawBox(Lnet/minecraft/client/gui/DrawContext;IIII)V",at=@At("HEAD"),cancellable = true)
    private void drawBoxInject2(DrawContext context, int x, int y, int width, int height, CallbackInfo ci) {
        if (GUIConfig.BufferFromMixin.INSTANCE.getScreenMixinLaunch()) ci.cancel();
    }

}
