/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.network
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-30  18:09
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.network

import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.entity.player.PlayerEntity
import org.slf4j.LoggerFactory

object BufferBus {

    private val logger = LoggerFactory.getLogger(GlobalData.MOD_ID)

    object ReadingOperationBus {

        val readingStatePlayers: MutableList<String> = mutableListOf()

        fun addReadingPlayer(playerId:String) {
            if (!readingStatePlayers.contains(playerId)) {
                logger.info("SET NEW READING PLAYER:${playerId}")
                readingStatePlayers.add(playerId)
            }
        }

        fun removeReadingPlayer(playerId:String) {
            readingStatePlayers.remove(playerId)
        }

    }
}