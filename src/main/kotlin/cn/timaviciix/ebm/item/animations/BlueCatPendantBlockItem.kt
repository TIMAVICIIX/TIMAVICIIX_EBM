/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.animations
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-26  00:50
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.animations

import cn.timaviciix.ebm.render.BlueCatPendantItemRender
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.block.Block
import net.minecraft.client.render.item.BuiltinModelItemRenderer
import net.minecraft.item.BlockItem
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.SingletonGeoAnimatable
import software.bernie.geckolib.animatable.client.RenderProvider
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache
import software.bernie.geckolib.core.animation.*
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.RenderUtils
import java.util.function.Consumer
import java.util.function.Supplier

class BlueCatPendantBlockItem(block: Block) : BlockItem(block, GlobalData.OWO_ITEM_SIGNAL_SETTING),
    GeoItem {

    init {
        SingletonGeoAnimatable.registerSyncedAnimatable(this)
    }

    private val cache: AnimatableInstanceCache = SingletonAnimatableInstanceCache(this)
    private val renderProvider = GeoItem.makeRenderer(this)

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return cache
    }

    override fun createRenderer(p0: Consumer<Any>?) {
        p0?.accept(object : RenderProvider {
            override fun getCustomRenderer(): BuiltinModelItemRenderer {
                return BlueCatPendantItemRender()
            }
        })
    }

    override fun getRenderProvider(): Supplier<Any> {
        return renderProvider
    }

    override fun getTick(itemStack: Any?): Double {
        return RenderUtils.getCurrentTick()
    }

    override fun registerControllers(p0: AnimatableManager.ControllerRegistrar?) {
        p0?.add(AnimationController(this, "blue_cat_pendant", this::predicate))
    }

    //customize play state operation,set animation play
    private fun <T : GeoAnimatable> predicate(state: AnimationState<T>): PlayState {
        state.controller.setAnimation(RawAnimation.begin().then("swing_anime", Animation.LoopType.LOOP))
        return PlayState.CONTINUE
    }
}