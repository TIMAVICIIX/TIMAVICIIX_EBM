/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.others
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-01  16:28
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

object TexturesRegister {

    private val READING_UI_TEXTURE = Identifier(GlobalData.MOD_ID, "textures/ui/reading_ui.png")

    fun registry() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(
            object : SimpleSynchronousResourceReloadListener {

                override fun reload(manager: ResourceManager?) {
                    manager?.getAllResources(READING_UI_TEXTURE)
                }

                override fun getFabricId(): Identifier {
                    return Identifier(GlobalData.MOD_ID, "reading_ui_bk")
                }

            }
        )

    }

}