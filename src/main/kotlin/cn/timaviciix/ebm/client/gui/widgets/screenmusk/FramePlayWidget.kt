/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui.widgets.screenmusk
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-30  20:39
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui.widgets.screenmusk

import cn.timaviciix.ebm.client.gui.config.GUIConfig
import net.minecraft.client.gui.DrawContext

class FramePlayWidget(
    private val framesImageConfig: GUIConfig.FrameTexturesConfig,
    private val frameTime: Int = 10
) {

    private var checkTick = 0
    private var currentFrameIndex = 0

    fun render(context: DrawContext?, x: Int, y: Int) {
        framesImageConfig.apply {
            if (checkTick >= frameTime) {
                checkTick = 0
                currentFrameIndex = (currentFrameIndex + 1) % textures.size
            } else {
                checkTick++
            }

            if (textures.isEmpty()) return
            context?.drawTexture(
                textures[currentFrameIndex],
                x - textureWidth / 2,
                y - textureHeight / 2,
                u,
                v,
                textureWidth,
                textureHeight,
                textureWidth,
                textureHeight
            )
        }
    }
}