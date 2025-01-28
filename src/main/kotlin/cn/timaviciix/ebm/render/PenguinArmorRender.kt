/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.render
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-29  01:27
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.render

import cn.timaviciix.ebm.item.armors.PenguinArmorItem
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.DefaultedItemGeoModel
import software.bernie.geckolib.renderer.GeoArmorRenderer

class PenguinArmorRender:GeoArmorRenderer<PenguinArmorItem>(DefaultedItemGeoModel(Identifier(GlobalData.MOD_ID,"armor/penguin_armor"))) {


}