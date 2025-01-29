/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.network
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-30  00:07
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.network

import cn.timaviciix.ebm.util.GlobalData
import io.netty.buffer.Unpooled
import kotlinx.serialization.internal.InlinePrimitiveDescriptor
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier

object Packets {

    val BOOK_READING_PACKET = Identifier(GlobalData.MOD_ID,GlobalData.PacketsID.BOOK_READING_STATE)

    fun sendReadingPlayerUUid(playerEntity: PlayerEntity){
        val buf = PacketByteBuf(Unpooled.buffer())
        buf.writeUuid(playerEntity.uuid)
        if (playerEntity is ServerPlayerEntity){
            ServerPlayNetworking.send(playerEntity, BOOK_READING_PACKET,buf)
        }else{
            ClientPlayNetworking.send(BOOK_READING_PACKET,buf)
        }

    }

}