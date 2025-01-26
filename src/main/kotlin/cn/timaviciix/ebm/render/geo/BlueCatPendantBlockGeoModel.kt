/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.render.geo
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-25  23:21
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.render.geo

import cn.timaviciix.ebm.block.blockentitys.others.BlueCatPendantBlockEntity
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.util.Identifier
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.model.GeoModel

class BlueCatPendantBlockGeoModel : GeoModel<BlueCatPendantBlockEntity>() {

    override fun getModelResource(p0: BlueCatPendantBlockEntity?): Identifier {
        return Identifier(GlobalData.MOD_ID, "geo/blue_cat_pendant.geo.json")
    }

    override fun getTextureResource(p0: BlueCatPendantBlockEntity?): Identifier {
        return Identifier(GlobalData.MOD_ID, "textures/block/others/blue_cat_pendant.png")
    }

    override fun getAnimationResource(p0: BlueCatPendantBlockEntity?): Identifier {
        return Identifier(GlobalData.MOD_ID, "animations/blue_cat_pendant.animation.json")
    }
}