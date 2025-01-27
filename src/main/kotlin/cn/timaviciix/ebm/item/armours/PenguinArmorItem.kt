/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.armours
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-28  02:14
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.armours

import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import java.util.function.Consumer
import java.util.function.Supplier

class PenguinArmorItem(material: ArmorMaterial, type: ArmorItem.Type, settings: Settings) :
    ArmorItem(material, type, settings), GeoItem {

    override fun registerControllers(p0: AnimatableManager.ControllerRegistrar?) {
        TODO("Not yet implemented")
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        TODO("Not yet implemented")
    }

    override fun createRenderer(p0: Consumer<Any>?) {
        TODO("Not yet implemented")
    }

    override fun getRenderProvider(): Supplier<Any> {
        TODO("Not yet implemented")
    }
}