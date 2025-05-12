/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.blocks.books
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  16:05
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks.books.delegates

import cn.timaviciix.ebm.block.books.BaseBookBlock
import cn.timaviciix.ebm.registers.blocks.BlockRegistryHandler
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty



open class BaseBookRegistryDelegate<T : BaseBookBlock>(
    private val nameColor: Int,
    val maxPage: Int,
    val typeCode: Int,
    private val blockSupplier: () -> T
) : ReadOnlyProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val id = property.name.lowercase()
        return BlockRegistryHandler.registrySelf(
            id = id,
            blockSupplier = blockSupplier,
            nameColor = this.nameColor,
            maxPage = this.maxPage,
            typeCode = this.typeCode
        )
    }
}