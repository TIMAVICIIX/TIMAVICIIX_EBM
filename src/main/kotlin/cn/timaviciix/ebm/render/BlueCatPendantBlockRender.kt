/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.render
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-26  00:39
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.render

import cn.timaviciix.ebm.block.blockentitys.others.BlueCatPendantBlockEntity
import cn.timaviciix.ebm.render.geo.BlueCatPendantBlockGeoModel
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import software.bernie.geckolib.renderer.GeoBlockRenderer

class BlueCatPendantBlockRender(context: BlockEntityRendererFactory.Context) :
    GeoBlockRenderer<BlueCatPendantBlockEntity>(BlueCatPendantBlockGeoModel()) {
}