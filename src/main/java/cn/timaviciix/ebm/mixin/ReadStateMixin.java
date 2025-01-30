/**
 * @desc : 通过Mixin添加玩家阅读状态,下述代码由DeepSeek生成
 */

package cn.timaviciix.ebm.mixin;

import cn.timaviciix.ebm.network.BufferBus;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class ReadStateMixin extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    public ReadStateMixin(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(
            method = "renderLabelIfPresent*",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onRenderLabelIfPresent(
            AbstractClientPlayerEntity entity,
            Text text,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            CallbackInfo ci
    ) {
        // 检查玩家是否在阅读状态
        if (BufferBus.ReadingOperationBus.INSTANCE.getReadingStatePlayers().contains(entity.getName().getString())) {
            // 创建自定义文本
            Text customText = Text.literal(text.getString()).append(Text.literal(" ")).append(Text.literal("Reading..."));

            // 强制转换为父类并调用原方法
            super.renderLabelIfPresent(
                    entity,
                    customText,
                    matrices,
                    vertexConsumers,
                    light
            );

            // 取消原方法的执行
            ci.cancel();
        }
    }
}