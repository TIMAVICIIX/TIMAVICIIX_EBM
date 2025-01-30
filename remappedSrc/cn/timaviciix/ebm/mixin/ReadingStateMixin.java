package cn.timaviciix.ebm.mixin;

import cn.timaviciix.ebm.network.BufferBus;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class ReadingStateMixin<T extends LivingEntity, M extends EntityModel<T>> {


    @Shadow
    public abstract void renderLabelIfPresent(
            T entity,
            Text text,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light
    );

    // 注入到 renderLabelIfPresent 方法中
    @Inject(
            method = "renderLabelIfPresent",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onRenderLabelIfPresent(
            T entity,
            Text text,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            CallbackInfo ci
    ) {
        if (entity instanceof PlayerEntity) {
            Text customText = Text.of(entity.getName().getString() + "\nReading...");
            // 直接调用 shadow 方法
            this.renderLabelIfPresent(entity, customText, matrices, vertexConsumers, light);
            ci.cancel();
        }
    }
}