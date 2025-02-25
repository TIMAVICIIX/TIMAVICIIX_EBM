/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-10  18:09
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data



class SealedDataPackage(vararg sealedData: SealedData<*>) {
    val dataMap: Map<Class<*>, SealedData<*>> = sealedData.associateBy {
        it.data!!::class.java
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T> get():T{
        return (dataMap[T::class.java] as? SealedData<T>)!!.data ?: error("Can't Find Data in Package!!! Data Struct Error!!!")
    }

}