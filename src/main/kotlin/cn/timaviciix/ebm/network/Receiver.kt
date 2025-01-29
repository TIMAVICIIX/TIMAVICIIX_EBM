/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.network
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-30  00:14
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.network

import io.netty.buffer.Unpooled
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.client.MinecraftClient
import net.minecraft.network.PacketByteBuf
import net.minecraft.text.Text

object Receiver {

    fun registryClientReceiver() {
        receiveAnnotationBookReadingState()
    }

    fun registryServerReceiver() {
        clientReceiveBookReadingState()
    }

    private fun receiveAnnotationBookReadingState() {
        ServerPlayNetworking.registerGlobalReceiver(Packets.BOOK_READING_PACKET_FROM_CLIENT) { server, player, handler, buf, responseSender ->
            val uuid = buf.readUuid()

            server.playerManager.playerList.forEach { targetPlayer ->
                val responseBuf = PacketByteBuf(Unpooled.buffer())
                responseBuf.writeUuid(uuid)
                ServerPlayNetworking.send(targetPlayer, Packets.BOOK_READING_PACKET_FROM_SERVER, responseBuf)
            }

        }
    }

    private fun clientReceiveBookReadingState() {
        ClientPlayNetworking.registerGlobalReceiver(Packets.BOOK_READING_PACKET_FROM_SERVER) { client, _, buf, _ ->
            val uuid = buf.readUuid()
            client.execute {
                val originPlayer = MinecraftClient.getInstance().world?.getPlayerByUuid(uuid)

                if (originPlayer != null) {
                    client.player?.sendMessage(Text.literal("Player[${originPlayer.id}] is Reading!!!"))
                }

            }

        }

    }

}