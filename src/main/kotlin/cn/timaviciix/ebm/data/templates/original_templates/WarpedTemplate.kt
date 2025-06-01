/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.templates
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  00:42
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data.templates.original_templates

import cn.timaviciix.ebm.data.nbt.NbtResolver
import cn.timaviciix.ebm.data.nbt.NbtStorageType
import cn.timaviciix.ebm.data.templates.TemplateDelegate
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound

//基类函数template与委托类TemplateDelegate配合，完成子类语法糖方式的声明，算是一个语法糖优化，不要也行
//元数据全部采用基本类型，避免复杂类型转换
abstract class WarpedTemplate<E : WarpedTemplate<E>> {
    private val _elements = mutableListOf<ElementTemplate<*>>()
    val elements: List<ElementTemplate<*>> get() = _elements

    fun <T> register(template: ElementTemplate<T>): ElementTemplate<T> {
        _elements.add(template)
        return template
    }

    protected fun <T> template(
        storageType: NbtStorageType,
        nbtResolver: NbtResolver<T>,
        default: T? = null,
    ): TemplateDelegate<T, E> {
        return TemplateDelegate(storageType, nbtResolver, default)
    }

    fun updateInline(stack: ItemStack, operation: E.() -> Unit) {
        @Suppress("UNCHECKED_CAST")
        (this as E).operation()
        saveInline(stack)
        readInline(stack, true)
    }

    fun saveInline(stack: ItemStack, needPrint: Boolean = false) {
        val nbt = NbtCompound()
        elements.forEach {
            if (it.storageType == NbtStorageType.ONLY_INLINE || it.storageType == NbtStorageType.ALL)
                it.saveToNbt(nbt)
        }
        stack.nbt = nbt
        if (needPrint) printElements()
    }

    fun readInline(stack: ItemStack, needPrint: Boolean = false) {
        val nbt = stack.orCreateNbt
        elements.forEach {
            it.readFromNbt(nbt)
        }
        if (needPrint) printElements()
    }

    fun readExternal(): NbtCompound {
        val nbt = NbtCompound()
        elements.forEach {
            if (it.storageType == NbtStorageType.ONLY_EXTERNAL || it.storageType == NbtStorageType.ALL)
                it.saveToNbt(nbt)
        }
        return nbt
    }

    fun asyncExternalAndInline(externalNbt: NbtCompound, inlineNbt: NbtCompound) {
        elements.forEach { it.readFromNbt(externalNbt) }
        elements.forEach { it.readFromNbt(inlineNbt) }
    }

    fun printElements() {
        elements.forEach {
            GlobalData.LOGGER.info("Element ID:${it.id} Element Value:${it.valueToString()}")
        }
    }

}
