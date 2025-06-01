/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.network.news
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-26  21:45
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.network

import cn.timaviciix.ebm.network.channels.BookExternalNbtChannel
import cn.timaviciix.ebm.network.channels.ReadingStateChannel
import cn.timaviciix.ebm.network.interfaces.ChannelModule

object NetworkInit {
    private val modules = listOf(ReadingStateChannel,BookExternalNbtChannel)

    fun registryClient() = modules.forEach { it.registerClient() }
    fun registryServer() = modules.forEach { it.registerServer() }

}