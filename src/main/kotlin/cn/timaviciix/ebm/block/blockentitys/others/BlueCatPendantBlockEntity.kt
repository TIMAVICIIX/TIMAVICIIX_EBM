/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.block.blockentitys.others
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-25  13:00
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.block.blockentitys.others

import cn.timaviciix.ebm.registers.others.AnimationIBsRegister
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos
import software.bernie.geckolib.animatable.GeoBlockEntity
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache
import software.bernie.geckolib.core.animation.*
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.RenderUtils

class BlueCatPendantBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(
    AnimationIBsRegister.EntityTypes.BLUE_CAT_PENDANT_ANIME_ENTITY_TYPE,
    pos, state
), GeoBlockEntity {
    private val cache: AnimatableInstanceCache = SingletonAnimatableInstanceCache(this)
    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return cache
    }

    override fun registerControllers(p0: AnimatableManager.ControllerRegistrar?) {
        p0?.add(AnimationController(this, "blue_cat_pendant", this::predicate))
    }

    //customize play state operation,set animation play
    private fun <T : GeoAnimatable> predicate(state: AnimationState<T>): PlayState {
        state.controller.setAnimation(RawAnimation.begin().then("swing_anime", Animation.LoopType.LOOP))
        state.controller.setSoundKeyframeHandler { }
        return PlayState.CONTINUE
    }

    override fun getTick(blockEntity: Any?): Double {
        return RenderUtils.getCurrentTick()
    }

}