/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.util
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-21  15:59
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object LogUtil {
    inline fun <reified T : Any> T.logger(): Logger {
        return LoggerFactory.getLogger(T::class.java)
    }

    inline fun <reified T : Any> T.logError(e: Throwable) {
        logger<T>().error("[${T::class.java.simpleName}]", e)
    }

    inline fun <reified T : Any> T.logError(msg: String) {
        logger<T>().error("[${T::class.java.simpleName}] $msg")
    }

    inline fun <reified T : Any> T.logInfo(msg: String) {
        logger<T>().info("[${T::class.java.simpleName}] $msg")
    }

    inline fun <reified T : Any> T.logDebug(msg: String) {
        logger<T>().debug("[${T::class.java.simpleName}] $msg")
    }

    // 静态/伴生对象专用日志工具
    inline fun <reified T> staticLogger(): Logger {
        return LoggerFactory.getLogger(T::class.java)
    }

    inline fun <reified T> logStaticError(e: Throwable) {
        staticLogger<T>().error("[${T::class.java.simpleName}]", e)
    }

    inline fun <reified T> logStaticError(msg: String) {
        staticLogger<T>().error("[${T::class.java.simpleName}] $msg")
    }

    inline fun <reified T> logStaticInfo(msg: String) {
        staticLogger<T>().info("[${T::class.java.simpleName}] $msg")
    }

    inline fun <reified T> logStaticDebug(msg: String) {
        staticLogger<T>().debug("[${T::class.java.simpleName}] $msg")
    }
}