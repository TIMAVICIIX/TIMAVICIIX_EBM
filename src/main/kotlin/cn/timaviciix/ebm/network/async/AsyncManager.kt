/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.network.buffer_manager
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-27  15:51
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.network.async

import cn.timaviciix.ebm.util.GlobalData
import kotlinx.coroutines.*

object AsyncManager {
    private val QUEUE: MutableMap<String, AsyncSubscriber<*>> = mutableMapOf()
    private val scope = CoroutineScope(Dispatchers.IO)

    fun <E> findAsyncTask(id: String): AsyncSubscriber<E>? {
        @Suppress("UNCHECKED_CAST")
        return QUEUE[id] as? AsyncSubscriber<E>
    }

    fun <E> publish(
        id: String,
        task: AsyncSubscriber<E>,
        coolDownMs: Long = 20000L,
        timeoutMs: Long = 10000L
    ): Boolean {
        if (QUEUE.containsKey(id)) return false

        QUEUE[id] = task
        scope.launch {
            try {
                task.onUploading()
                withTimeoutOrNull(timeoutMs) {
                    task.startUpload()
                }
                if (task.response == null) {
                    task.onTimeout()
                }
            } catch (e: Exception) {
                task.onFailed(task.response)
                GlobalData.LOGGER.error("Upload Error: ${e.message}")
            } finally {
                delay(coolDownMs)
                QUEUE.remove(id)
            }
        }

        return true
    }
}