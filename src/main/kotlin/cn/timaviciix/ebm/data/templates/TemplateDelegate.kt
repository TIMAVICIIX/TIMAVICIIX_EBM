/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.components
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  01:08
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data.templates

import cn.timaviciix.ebm.data.nbt.NbtResolver
import cn.timaviciix.ebm.data.nbt.NbtStorageType
import cn.timaviciix.ebm.data.templates.original_templates.ElementTemplate
import cn.timaviciix.ebm.data.templates.original_templates.WarpedTemplate
import kotlin.reflect.KProperty

//利用委托自动提供注册上下文的特性，对id进行自动注入，将声明属性的名称直接注入到id中
//XmlResolver提供的空实例能够直接判断数据是否需要Xml存储
class TemplateDelegate<T, E : WarpedTemplate<E>>(
    private val storageType: NbtStorageType,
    private val nbtResolver: NbtResolver<T>,
    private val default: T? = null
) {
    private lateinit var instance: ElementTemplate<T>

    operator fun getValue(thisRef: WarpedTemplate<E>, property: KProperty<*>): ElementTemplate<T> {
        if (!::instance.isInitialized) {
            instance =
                ElementTemplate(property.name, storageType, nbtResolver.also { it.injectId(property.name) }, default)
            thisRef.register(instance)
        }
        return instance
    }
}