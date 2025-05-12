/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.blocks.books
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  14:55
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks.books

import cn.timaviciix.ebm.block.blockentitys.bookentitys.JournalBookBlockEntity
import cn.timaviciix.ebm.block.books.journalbooks.ClassicJournalBookBlock
import cn.timaviciix.ebm.registers.blocks.BlockRegistryHandler
import cn.timaviciix.ebm.registers.blocks.books.delegates.JournalBookDelegateRegister
import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer
import io.wispforest.owo.registration.reflect.BlockRegistryContainer
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntityType

class JournalBookRegister() : BlockRegistryContainer {


    companion object {
        val CLASSIC_JOURNAL_BOOK by JournalBookDelegateRegister(::ClassicJournalBookBlock)
    }

    class EntityTypes() : BlockEntityRegistryContainer {

        companion object {
            val JOURNAL_CLASSIC_JOURNAL_BOOK_TYPE: BlockEntityType<JournalBookBlockEntity> =
                BlockRegistryHandler.registryEntitySelf(
                    ::CLASSIC_JOURNAL_BOOK,
                    FabricBlockEntityTypeBuilder.create({ pos, state ->
                        JournalBookBlockEntity(
                            ParamEntityTypes.JOURNAL_CLASSIC_JOURNAL_BOOK_TYPE.blockEntityType,
                            pos, state
                        )
                    }, CLASSIC_JOURNAL_BOOK).build()
                )


            enum class ParamEntityTypes(val blockEntityType: BlockEntityType<*>) {
                JOURNAL_CLASSIC_JOURNAL_BOOK_TYPE(EntityTypes.JOURNAL_CLASSIC_JOURNAL_BOOK_TYPE)
            }
        }

    }

}