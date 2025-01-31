/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.network
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-30  00:14
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.network

import cn.timaviciix.ebm.util.GlobalData
import io.netty.buffer.Unpooled
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.client.MinecraftClient
import net.minecraft.network.PacketByteBuf
import org.slf4j.LoggerFactory
import java.util.*

object Receiver {

    private val logger = LoggerFactory.getLogger(GlobalData.MOD_ID)

    fun registryClientReceiver() {
        clientReceiveBookReadingState()
        clientInitPlayersReadingState()
    }

    fun registryServerReceiver() {
        receiveAnnotationBookReadingState()
        initPlayerSyncReadingState()
    }

    private fun receiveAnnotationBookReadingState() {
        ServerPlayNetworking.registerGlobalReceiver(Packets.BOOK_READING_PACKET_FROM_CLIENT) { server, player, _, buf, _ ->
            val uuid = buf.readUuid()
            server.playerManager.playerList.forEach { targetPlayer ->
                val responseBuf = PacketByteBuf(Unpooled.buffer())
                responseBuf.writeUuid(uuid)
                //logger.info("You are Server Player send infos from Server")
                ServerPlayNetworking.send(targetPlayer, Packets.BOOK_READING_PACKET_FROM_SERVER, responseBuf)

            }

        }
    }

    private fun initPlayerSyncReadingState() {
        ServerPlayConnectionEvents.JOIN.register { handler, _, _ ->

            val initReadStateBuf = PacketByteBuf(Unpooled.buffer()).apply {
                writeInt(BufferBus.ReadingOperationBus.readingStatePlayers.size)

                for (uuid in BufferBus.ReadingOperationBus.readingStatePlayers) {
                    writeUuid(uuid)
                }
            }

            ServerPlayNetworking.send(handler.player, Packets.BOOK_READING_INIT_PACKET, initReadStateBuf)

        }
    }

    private fun clientReceiveBookReadingState() {
        ClientPlayNetworking.registerGlobalReceiver(Packets.BOOK_READING_PACKET_FROM_SERVER) { client, _, buf, _ ->
            val uuid = buf.readUuid()
            logger.info("Reading Players:${BufferBus.ReadingOperationBus.readingStatePlayers}")
            client.execute {
                //logger.info("You are Client Player, Receive Infos From Server")
                if (!BufferBus.ReadingOperationBus.readingStatePlayers.contains(uuid)) {
                    //@Imp:Render&UI
                    //client.player?.sendMessage(Text.literal("Player[${originPlayer.name.string}] is Reading!!!"))
                    BufferBus.ReadingOperationBus.addReadingPlayer(uuid)

                    //@Imp: WARING!!! TEST CODE
                } else {
                    BufferBus.ReadingOperationBus.removeReadingPlayer(uuid)
                }


            }

        }

    }

    private fun clientInitPlayersReadingState() {

        ClientPlayNetworking.registerGlobalReceiver(Packets.BOOK_READING_INIT_PACKET) { client, _, buf, _ ->

            val size = buf.readInt()
            val initReadingPlayerUUIDList: MutableList<UUID> = mutableListOf()

            repeat(size) {
                initReadingPlayerUUIDList.add(buf.readUuid())
            }

            client.execute {
                BufferBus.ReadingOperationBus.readingStatePlayers += initReadingPlayerUUIDList
            }

        }

    }

}