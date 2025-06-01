/**
 * @desc : 通过Mixin添加玩家阅读状态,下述代码由DeepSeek生成
 */

package cn.timaviciix.ebm.mixin.network;

import cn.timaviciix.ebm.network.channels.ReadingStateChannel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.joml.Matrix4f;
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
        if (ReadingStateChannel.INSTANCE.getReadingStatePlayers().contains(entity.getUuid())) {
            Text readingStateString = Text.translatable("state.timaviciix_ebm.reading_state");
            double d = this.dispatcher.getSquaredDistanceToCamera(entity);
            if (!(d > 4096.0)) {
                boolean bl = !entity.isSneaky();
                float f = entity.getNameLabelHeight();
                int i = "deadmau5".equals(readingStateString.getString()) ? -10 : 0;
                matrices.push();
                matrices.translate(0.0F, f + 0.3, 0.0F);
                matrices.multiply(this.dispatcher.getRotation());
                matrices.scale(-0.025F, -0.025F, 0.025F);
                Matrix4f matrix4f = matrices.peek().getPositionMatrix();
                float g = MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25F);
                int j = (int) (g * 255.0F) << 24;
                TextRenderer textRenderer = this.getTextRenderer();
                float h = (float) (-textRenderer.getWidth(readingStateString) / 2);
                textRenderer.draw(
                        readingStateString, h, (float) i, 553648127, false, matrix4f, vertexConsumers, bl ? TextRenderer.TextLayerType.SEE_THROUGH : TextRenderer.TextLayerType.NORMAL, j, light
                );
                if (bl) {
                    textRenderer.draw(readingStateString, h, (float) i, -1, false, matrix4f, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, light);
                }

                matrices.pop();
            }


            super.renderLabelIfPresent(
                    entity,
                    text,
                    matrices,
                    vertexConsumers,
                    light
            );

            // 取消原方法的执行
            ci.cancel();
        }
    }
}