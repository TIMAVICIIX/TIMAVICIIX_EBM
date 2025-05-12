/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.blocks.books.delegates
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  23:46
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks.books.delegates

import cn.timaviciix.ebm.block.books.BaseBookBlock

class LightBookDelegateRegister<T : BaseBookBlock>(blockSupplier: () -> T) :
    BaseBookRegistryDelegate<T>(MAX_PAGE, 0x10ddc2, TYPE_CODE, blockSupplier) {
    companion object : BookTypeHolder {
        override val TYPE_CODE: Int
            get() = 2

        override val MAX_PAGE: Int
            get() = 500
    }
}