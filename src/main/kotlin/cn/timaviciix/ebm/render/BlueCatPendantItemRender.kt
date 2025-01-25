/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.render
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-26  01:05
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.render

import cn.timaviciix.ebm.item.animations.BlueCatPendantBlockItem
import cn.timaviciix.ebm.render.geo.BlueCatPendantItemGeoModel
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import software.bernie.geckolib.renderer.GeoItemRenderer

class BlueCatPendantItemRender :
    GeoItemRenderer<BlueCatPendantBlockItem>(BlueCatPendantItemGeoModel()) {
}