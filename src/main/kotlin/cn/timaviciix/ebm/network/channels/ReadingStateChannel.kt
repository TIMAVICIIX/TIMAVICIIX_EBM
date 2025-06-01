/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.network.news.channels
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-26  21:35
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.network.channels

import cn.timaviciix.ebm.network.delegates.PacketID
import cn.timaviciix.ebm.network.interfaces.ChannelModule
import cn.timaviciix.ebm.util.GlobalData
import io.netty.buffer.Unpooled
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.network.ServerPlayerEntity
import java.util.*

object ReadingStateChannel : ChannelModule {

    private val BOOK_READING_STATE_FROM_SERVER by PacketID()
    private val BOOK_READING_STATE_FROM_CLIENT by PacketID()
    private val BOOK_READING_INIT_PACKET by PacketID()

    val readingStatePlayers: MutableList<UUID> = mutableListOf()


    fun sendReadingPlayerUUid(playerEntity: PlayerEntity) {
        val buf = PacketByteBuf(Unpooled.buffer()).apply {
            writeUuid(playerEntity.uuid)
        }
        if (playerEntity is ServerPlayerEntity) {
            //logger.info("Player is ServerPlayer send ServerPlayerNetworking")
            ServerPlayNetworking.send(playerEntity, BOOK_READING_STATE_FROM_SERVER, buf)
        } else {
            //logger.info("Player is ClientPlayer send ClientPlayerNetworking")
            ClientPlayNetworking.send(BOOK_READING_STATE_FROM_CLIENT, buf)
        }

    }

    override fun registerClient() {
        clientReceiveBookReadingState()
        clientInitPlayersReadingState()
    }

    override fun registerServer() {
        receiveAnnotationBookReadingState()
        initPlayerSyncReadingState()
    }

    private fun receiveAnnotationBookReadingState() {
        ServerPlayNetworking.registerGlobalReceiver(BOOK_READING_STATE_FROM_CLIENT) { server, player, _, buf, _ ->
            val uuid = buf.readUuid()
            server.playerManager.playerList.forEach { targetPlayer ->
                val responseBuf = PacketByteBuf(Unpooled.buffer())
                responseBuf.writeUuid(uuid)
                ServerPlayNetworking.send(targetPlayer, BOOK_READING_STATE_FROM_SERVER, responseBuf)

            }

        }
    }

    private fun initPlayerSyncReadingState() {
        ServerPlayConnectionEvents.JOIN.register { handler, _, _ ->
            val initReadStateBuf = PacketByteBuf(Unpooled.buffer()).apply {
                writeInt(readingStatePlayers.size)
                for (uuid in readingStatePlayers) {
                    writeUuid(uuid)
                }
            }
            ServerPlayNetworking.send(handler.player, BOOK_READING_INIT_PACKET, initReadStateBuf)
        }
    }

    private fun clientReceiveBookReadingState() {
        ClientPlayNetworking.registerGlobalReceiver(BOOK_READING_STATE_FROM_SERVER) { client, _, buf, _ ->
            val uuid = buf.readUuid()
            GlobalData.LOGGER.info("Reading Players:$readingStatePlayers")
            client.execute {
                if (!readingStatePlayers.contains(uuid)) {
                    addReadingPlayer(uuid)
                } else {
                    removeReadingPlayer(uuid)
                }
            }
        }
    }

    private fun clientInitPlayersReadingState() {
        ClientPlayNetworking.registerGlobalReceiver(BOOK_READING_INIT_PACKET) { client, _, buf, _ ->
            val size = buf.readInt()
            val initReadingPlayerUUIDList: MutableList<UUID> = mutableListOf()
            repeat(size) {
                initReadingPlayerUUIDList.add(buf.readUuid())
            }
            client.execute {
                readingStatePlayers += initReadingPlayerUUIDList
            }
        }
    }

    private fun addReadingPlayer(playerId: UUID) {
        if (!readingStatePlayers.contains(playerId)) {
            readingStatePlayers.add(playerId)
        }
    }

    private fun removeReadingPlayer(playerId: UUID) {
        readingStatePlayers.remove(playerId)
    }

}