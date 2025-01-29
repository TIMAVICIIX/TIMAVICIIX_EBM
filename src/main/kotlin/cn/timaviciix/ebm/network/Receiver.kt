/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.network
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-30  00:14
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.network

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text

object Receiver {

    fun registryAll() {
        receiveBookReadingState()
    }

    private fun receiveBookReadingState() {
        ClientPlayNetworking.registerGlobalReceiver(Packets.BOOK_READING_PACKET) { client, _, buf, _ ->

            val uuid = buf.readUuid()
            client.execute{
                val originPlayer = MinecraftClient.getInstance().world?.getPlayerByUuid(uuid)

                if (originPlayer!=null){
                    client.player?.sendMessage(Text.literal("Player[${originPlayer.id}] is Reading!!!"))
                }

            }

        }
    }

}