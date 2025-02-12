package cn.timaviciix.ebm.mixin;

import net.minecraft.client.gui.widget.EditBoxWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

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
        return 0x444444;
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
        return 0x444444;
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
        return 0x444444;
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
        return 0x444444;
    }
}