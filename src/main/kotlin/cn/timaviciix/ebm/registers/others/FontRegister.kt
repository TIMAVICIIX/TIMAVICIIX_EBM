/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.others
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-01  01:02
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.others

import cn.timaviciix.ebm.util.GlobalData
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier

object FontRegister {

    private val FONT_RESOURCE = ResourceType.CLIENT_RESOURCES

    fun registry() {
        ResourceManagerHelper.get(FONT_RESOURCE).registerReloadListener(
            object:SimpleSynchronousResourceReloadListener {

                override fun reload(manager: ResourceManager?) {
                    manager?.getAllResources(Identifier(GlobalData.MOD_ID,"font/fonts.json"))
                }

                override fun getFabricId(): Identifier {
                    return Identifier(GlobalData.MOD_ID, "custom_font_loader")
                }

            }
        )

    }

}