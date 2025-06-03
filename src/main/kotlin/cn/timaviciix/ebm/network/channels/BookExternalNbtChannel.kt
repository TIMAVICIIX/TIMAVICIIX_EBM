/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.network.channels
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-27  00:51
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.network.channels

import cn.timaviciix.ebm.data.templates.package_templates.WarpedBookData
import cn.timaviciix.ebm.network.async.AsyncID
import cn.timaviciix.ebm.network.async.AsyncManager
import cn.timaviciix.ebm.network.delegates.PacketID
import cn.timaviciix.ebm.network.interfaces.ChannelModule
import cn.timaviciix.ebm.storage.StorageCategory
import cn.timaviciix.ebm.storage.StorageManager
import cn.timaviciix.ebm.util.GlobalData
import io.netty.buffer.Unpooled
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.network.ServerPlayerEntity

object BookExternalNbtChannel : ChannelModule {

    private val NBT_UPLOAD_REQUEST_FROM_CLIENT by PacketID()
    private val NBT_UPLOAD_RESPONSE_FROM_SERVER by PacketID()

    private val NBT_DOWNLOAD_REQUEST_FROM_CLIENT by PacketID()
    private val NBT_DOWNLOAD_RESPONSE_FROM_SERVER by PacketID()

    fun sendUploadBookExternalNbtRequest(playerEntity: PlayerEntity, bookId: String, bookExternalNbt: NbtCompound) {
        val buf = PacketByteBuf(Unpooled.buffer()).apply {
            writeUuid(playerEntity.uuid)
            writeString(bookId)
            writeNbt(bookExternalNbt)
        }
        if (playerEntity is ServerPlayerEntity) {
            ServerPlayNetworking.send(playerEntity, NBT_UPLOAD_REQUEST_FROM_CLIENT, buf)
        } else {
            ClientPlayNetworking.send(NBT_UPLOAD_REQUEST_FROM_CLIENT, buf)
        }
    }

    fun sendGetBookExternalNbtRequest(playerEntity: PlayerEntity, bookId: String) {
        val buf = PacketByteBuf(Unpooled.buffer()).apply {
            writeUuid(playerEntity.uuid)
            writeString(bookId)
        }
        if (playerEntity is ServerPlayerEntity) {
            ServerPlayNetworking.send(playerEntity, NBT_DOWNLOAD_REQUEST_FROM_CLIENT, buf)
        } else {
            ClientPlayNetworking.send(NBT_DOWNLOAD_REQUEST_FROM_CLIENT, buf)
        }
    }

    override fun registerClient() {
        receiveGetResponseFromServer()
        receiveUploadResponseFromServer()
    }

    override fun registerServer() {
        receiveUploadRequestFromClient()
        receiveGetRequestFromClient()
    }

    private fun receiveUploadRequestFromClient() {
        ServerPlayNetworking.registerGlobalReceiver(NBT_UPLOAD_REQUEST_FROM_CLIENT) { server, _, _, buf, _ ->
            val playerUUID = buf.readUuid()
            val bookId = buf.readString()
            val bookNbt = buf.readNbt()
            bookNbt?.let {
                server.playerManager.getPlayer(playerUUID)?.let { player ->
                    val saveResult = StorageManager.writeNbtData(playerUUID, StorageCategory.BOOK_DATA, bookId, it)
                    //进行存储完整性验证
                    StorageManager.readNbtData(playerUUID, StorageCategory.BOOK_DATA, bookId)?.let {
                        val maxPage = it.getInt("maxPage")
                        val typeCode = it.getInt("typeCode")
                        WarpedBookData(maxPage, typeCode).apply {
                            readAll(it)
                            printElements()
                            GlobalData.LOGGER.info("Book ID:${this.bookId.riskyGetValue()}")
                        }

                    }
                    val responseBuf = PacketByteBuf(Unpooled.buffer()).apply { writeBoolean(saveResult) }
                    ServerPlayNetworking.send(player, NBT_UPLOAD_RESPONSE_FROM_SERVER, responseBuf)
                }
            }
        }
    }

    private fun receiveGetRequestFromClient() {
        ServerPlayNetworking.registerGlobalReceiver(NBT_DOWNLOAD_REQUEST_FROM_CLIENT) { server, player, _, buf, _ ->
            val playerUUID = buf.readUuid()
            val bookId = buf.readString()
            server.playerManager.getPlayer(playerUUID)?.let {
                val bookNbt = StorageManager.readNbtData(playerUUID, StorageCategory.BOOK_DATA, bookId)
                val responseBuf = PacketByteBuf(Unpooled.buffer()).apply { writeNbt(bookNbt) }
                ServerPlayNetworking.send(player, NBT_DOWNLOAD_RESPONSE_FROM_SERVER, responseBuf)
            }
        }
    }

    private fun receiveUploadResponseFromServer() {
        ClientPlayNetworking.registerGlobalReceiver(NBT_UPLOAD_RESPONSE_FROM_SERVER) { client, _, buf, _ ->
            val result = buf.readBoolean()
            client.execute {
                AsyncManager.findAsyncTask<Boolean>(AsyncID.UPLOAD_EXTERNAL_BOOK_REQUEST)?.complete(result)
            }
        }
    }

    private fun receiveGetResponseFromServer(): NbtCompound? {
        ClientPlayNetworking.registerGlobalReceiver(NBT_DOWNLOAD_RESPONSE_FROM_SERVER) { client, _, _, _ ->
            client.execute {
                //
            }
        }
        return null
    }
}

