/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.util
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-05  23:17
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.util

import kotlinx.io.IOException
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

object CompressUtil {

    @Throws(IOException::class)
    fun compressString(data: String): ByteArray {
        return ByteArrayOutputStream().use { bos ->
            GZIPOutputStream(bos).bufferedWriter(StandardCharsets.UTF_8).use { writer ->
                writer.write(data)
            }
            bos.toByteArray()
        }
    }

    // 解压字符串
    @Throws(IOException::class)
    fun decompressString(compressed: ByteArray): String {
        return ByteArrayInputStream(compressed).use { bis ->
            GZIPInputStream(bis).bufferedReader(StandardCharsets.UTF_8).use { reader ->
                reader.readText()
            }
        }
    }

}