/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-26  02:18
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm

import cn.timaviciix.ebm.registers.others.AnimationIBsRegister
import cn.timaviciix.ebm.render.BlueCatPendantBlockRender
import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories

object TIMAVICIIXEBMClient:ClientModInitializer {

    override fun onInitializeClient() {

        //client to registry render
        BlockEntityRendererFactories.register(
            AnimationIBsRegister.EntityTypes.BLUE_CAT_PENDANT_ANIME_ENTITY_TYPE,
            ::BlueCatPendantBlockRender
        )
    }

}