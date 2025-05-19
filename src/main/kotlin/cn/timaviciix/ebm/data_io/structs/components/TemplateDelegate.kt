/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.components
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  01:08
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.components

import cn.timaviciix.ebm.data_io.structs.components.nbt.NbtResolver
import cn.timaviciix.ebm.data_io.structs.components.xml.XmlResolveSupplier
import cn.timaviciix.ebm.data_io.structs.components.xml.XmlResolverDelegate
import cn.timaviciix.ebm.data_io.structs.templates.original_templates.ElementTemplate
import cn.timaviciix.ebm.data_io.structs.templates.original_templates.WarpedTemplate
import kotlin.reflect.KProperty

//利用委托自动提供注册上下文的特性，对id进行自动注入，将声明属性的名称直接注入到id中
//XmlResolver提供的空实例能够直接判断数据是否需要Xml存储
class TemplateDelegate<T>(
    private val nbtResolver: NbtResolver<T>,
    private val readSupplier: (inputValue: String?, default: T?) -> T?,
    private val saveSupplier: (outputValue: T) -> String,
    private var xmlResolverSupplier: XmlResolveSupplier<T>? = null,
    private val default: T? = null
) {
    private lateinit var instance: ElementTemplate<T>

    operator fun getValue(thisRef: WarpedTemplate, property: KProperty<*>): ElementTemplate<T> {
        if (!::instance.isInitialized) {
            val id = property.name
            //默认字符串类型XML解析委托，返回String类型的XML解析器
            xmlResolverSupplier = XmlResolverDelegate(mapOf(), readSupplier, saveSupplier).getValue(null, property)
            instance =
                ElementTemplate(id, xmlResolverSupplier!!, nbtResolver.also { it.injectId(property.name) }, default)
            thisRef.register(instance)
        }
        return instance
    }
}