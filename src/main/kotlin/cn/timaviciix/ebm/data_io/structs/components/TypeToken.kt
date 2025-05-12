/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.components
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-12  22:41
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.components

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class TypeToken<T> {
    val type: Type = (this::class.java.genericSuperclass as ParameterizedType)
        .actualTypeArguments[0]
}