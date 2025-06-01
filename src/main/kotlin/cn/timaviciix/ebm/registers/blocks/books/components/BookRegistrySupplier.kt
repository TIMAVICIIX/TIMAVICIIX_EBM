/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.blocks.books
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  16:05
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks.books.components

import cn.timaviciix.ebm.block.books.BaseBookBlock
import cn.timaviciix.ebm.registers.blocks.BlockRegistryHandler
import kotlin.reflect.KProperty
import kotlin.reflect.full.createInstance

abstract class BookRegistrySupplier {
    abstract val nameColor: Int
    abstract val maxPage: Int
    abstract val typeCode: Int

    inline fun <reified T : BaseBookBlock> registry(property:KProperty<*>): T {
        return BlockRegistryHandler.registrySelf(
            id = property.name.lowercase(),
            block = T::class.createInstance(),
            nameColor = this.nameColor,
            maxPage = this.maxPage,
            typeCode = this.typeCode
        )
    }
}