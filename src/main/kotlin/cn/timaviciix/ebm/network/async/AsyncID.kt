/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.network.async
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-27  16:14
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.network.async

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


object AsyncID {

    val UPLOAD_EXTERNAL_BOOK_REQUEST by RegistryAsyncId()
    val DOWNLOAD_EXTERNAL_BOOK_REQUEST by RegistryAsyncId()


    private class RegistryAsyncId() : ReadOnlyProperty<Any, String> {
        override fun getValue(thisRef: Any, property: KProperty<*>): String {
            return property.name.lowercase()
        }
    }
}