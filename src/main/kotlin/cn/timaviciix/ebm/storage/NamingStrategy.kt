/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.storage
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-06-02  17:08
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.storage

interface NamingStrategy {
    fun fileNameFor(id: String): String
}
