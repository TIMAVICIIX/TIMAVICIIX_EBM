/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.util.annotations
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-18  13:06
 *@Description: Item注册注解
 *@Version: 1.0
 */

package cn.timaviciix.ebm.util.annotations

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.SOURCE)
annotation class RegistryItem (val desc:String="Convert Item name to lowercase")
