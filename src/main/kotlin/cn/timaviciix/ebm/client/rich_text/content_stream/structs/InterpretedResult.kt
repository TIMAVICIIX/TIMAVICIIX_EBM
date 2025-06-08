/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.rich_text.content_stream.structs
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-06-07  23:52
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.rich_text.content_stream.structs

interface InterpretedResult<T> {
    val startIndex: Int
    val endIndex: Int

    val blockContent: T
}