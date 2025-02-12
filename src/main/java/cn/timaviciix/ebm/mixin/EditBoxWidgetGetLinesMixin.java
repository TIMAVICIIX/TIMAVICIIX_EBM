package cn.timaviciix.ebm.mixin;


import cn.timaviciix.ebm.client.gui.GUIConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.EditBox;
import net.minecraft.client.gui.widget.EditBoxWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EditBoxWidget.class)
public abstract class EditBoxWidgetGetLinesMixin {

    @Shadow
    @Final
    private EditBox editBox;

    @Inject(method = "renderContents", at = @At("HEAD"))
    private void countTextLines(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        String string = this.editBox.getText();
        if (!string.isEmpty()) {
            int linesCount = getSize(this.editBox.getLines());
            if (linesCount != GUIConfig.BufferFromMixin.INSTANCE.getWrapLineCount()) {
                GUIConfig.BufferFromMixin.INSTANCE.setWrapLineCount(linesCount);
            }
        }
    }

    @Unique
    public int getSize(Iterable<?> iterable) {
        int size = 0;
        for (Object ignored : iterable) {
            size++;
        }
        return size;
    }
}


