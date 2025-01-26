/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.blocks
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-27  01:48
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks

import cn.timaviciix.ebm.block.blockentitys.worktables.LibrarianDeskBlockEntity
import cn.timaviciix.ebm.block.worktables.LibrarianDeskBlock
import cn.timaviciix.ebm.registers.blocks.BaseBlockRegister.Companion.registryEntitySelf
import cn.timaviciix.ebm.registers.blocks.BaseBlockRegister.Companion.registrySelf
import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer
import io.wispforest.owo.registration.reflect.BlockRegistryContainer
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.BlockItem

class WorkTableRegister() : BlockRegistryContainer {

    companion object {
        val blockItemsConsist: MutableList<Pair<BlockItem, String>> = mutableListOf()

        val LIBRARIAN_DESK: LibrarianDeskBlock = registrySelf(::LIBRARIAN_DESK, {
            blockItemsConsist.add(it)
        }, 0xd4a5a5)


        class EntityTypes() : BlockEntityRegistryContainer {
            companion object {
                val LIBRARIAN_DESK_TYPE: BlockEntityType<LibrarianDeskBlockEntity> = registryEntitySelf(
                    ::LIBRARIAN_DESK,
                    FabricBlockEntityTypeBuilder.create(::LibrarianDeskBlockEntity, LIBRARIAN_DESK).build()
                )
            }
        }
    }

}