/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.armours
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-28  02:14
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.armors

import cn.timaviciix.ebm.registers.items.registers.ArmorRegister
import cn.timaviciix.ebm.render.PenguinArmorRender
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.decoration.ArmorStandEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.RenderProvider
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.renderer.GeoArmorRenderer
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.function.Consumer
import java.util.function.Supplier

class PenguinArmorItem(material: ArmorMaterial, type: Type, settings: Settings) :
    ArmorItem(material, type, settings), GeoItem {

    private val cache = GeckoLibUtil.createInstanceCache(this)
    private val renderProvider = GeoItem.makeRenderer(this)

    override fun registerControllers(controllers: ControllerRegistrar) {
        controllers.add(
            AnimationController(this, 20) { state ->
                state.controller.setAnimation(DefaultAnimations.IDLE)
                val entity = state.getData(DataTickets.ENTITY) as Entity

                if (entity is ArmorStandEntity) {
                    return@AnimationController PlayState.CONTINUE
                } else {
                    val wornArmor = mutableSetOf<Item>()
                    for (stack in entity.armorItems) {
                        if (stack.isEmpty) {
                            return@AnimationController PlayState.STOP
                        }
                        wornArmor.add(stack.item)
                    }

                    val isFullSet = wornArmor.containsAll(
                        listOf(
                            ArmorRegister.PENGUIN_HELMET,
                            ArmorRegister.PENGUIN_CHESTPLATE,
                            ArmorRegister.PENGUIN_LEGGINGS,
                            ArmorRegister.PENGUIN_BOOTS
                        )
                    )
                    return@AnimationController if (isFullSet) PlayState.CONTINUE else PlayState.STOP
                }
            }
        )
    }


    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return cache
    }


    override fun createRenderer(consumer: Consumer<Any?>) {
        consumer.accept(object : RenderProvider {
            private var renderer: GeoArmorRenderer<*>? = null

            override fun getHumanoidArmorModel(
                livingEntity: LivingEntity,
                itemStack: ItemStack,
                equipmentSlot: EquipmentSlot,
                original: BipedEntityModel<LivingEntity>
            ): BipedEntityModel<LivingEntity> {
                if (this.renderer == null) {
                    this.renderer = PenguinArmorRender()
                }

                this.renderer!!.prepForRender(livingEntity, itemStack, equipmentSlot, original)
                return this.renderer!!
            }
        })
    }


    override fun getRenderProvider(): Supplier<Any> {
        return renderProvider
    }
}