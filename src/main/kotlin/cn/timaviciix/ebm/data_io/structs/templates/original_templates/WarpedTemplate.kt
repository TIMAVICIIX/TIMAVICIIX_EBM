/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.templates
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  00:42
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.templates.original_templates

import cn.timaviciix.ebm.data_io.structs.components.TemplateDelegate
import cn.timaviciix.ebm.data_io.structs.components.nbt.NbtResolver
import cn.timaviciix.ebm.data_io.structs.components.xml.XmlResolveSupplier
import cn.timaviciix.ebm.util.GlobalData

//基类函数template与委托类TemplateDelegate配合，完成子类语法糖方式的声明，算是一个语法糖优化，不要也行
//元数据全部采用基本类型，避免复杂类型转换
abstract class WarpedTemplate {
    private val _elements = mutableListOf<ElementTemplate<*>>()
    val elements: List<ElementTemplate<*>> get() = _elements

    fun <T> register(template: ElementTemplate<T>): ElementTemplate<T> {
        _elements.add(template)
        return template
    }

    fun <T> template(
        nbtResolver: NbtResolver<T>,
        xmlResolverSupplier: XmlResolveSupplier<T>? = null,
        default: T? = null,
        readSupplier: (inputValue: String?, default: T?) -> T? = { inputValue, defaultValue ->
            try {
                @Suppress("UNCHECKED_CAST")
                inputValue as? T
            } catch (e: Exception) {
                GlobalData.LOGGER.error("Can't cast this value!")
                if (defaultValue != null) {
                    defaultValue
                } else {
                    GlobalData.LOGGER.error("Can't find default value!")
                    null
                }
            }
        },
        saveSupplier: (outputValue: T) -> String = {
            try {
                it.toString()
            } catch (e: Exception) {
                GlobalData.LOGGER.error("Can't cast this value!")
                throw TypeCastException()
            }
        }
    ): TemplateDelegate<T> {
        return TemplateDelegate(nbtResolver, readSupplier, saveSupplier, xmlResolverSupplier, default)
    }


}
