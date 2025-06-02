/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.storage.naming
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-06-02  17:14
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.storage.naming

import cn.timaviciix.ebm.storage.NamingStrategy

object UUIDNamingStrategy : NamingStrategy {
    override fun fileNameFor(id: String): String = "$id.nbt"
}