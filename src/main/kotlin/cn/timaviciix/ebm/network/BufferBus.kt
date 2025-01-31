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
import org.slf4j.LoggerFactory
import java.util.*

object BufferBus {

    private val logger = LoggerFactory.getLogger(GlobalData.MOD_ID)

    object ReadingOperationBus {

        val readingStatePlayers: MutableList<UUID> = mutableListOf()

        fun addReadingPlayer(playerId: UUID) {
            if (!readingStatePlayers.contains(playerId)) {
                logger.info("SET NEW READING PLAYER:${playerId}")
                readingStatePlayers.add(playerId)
            }
        }

        fun removeReadingPlayer(playerId: UUID) {
            logger.info("EXIT READING PLAYER:${playerId}")
            readingStatePlayers.remove(playerId)
        }

    }
}