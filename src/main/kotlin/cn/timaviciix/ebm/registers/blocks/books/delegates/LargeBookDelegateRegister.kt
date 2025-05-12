/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.blocks.books.delegates
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  23:42
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks.books.delegates

import cn.timaviciix.ebm.block.books.BaseBookBlock

class LargeBookDelegateRegister<T : BaseBookBlock>(blockSupplier: () -> T) :
    BaseBookRegistryDelegate<T>(MAX_PAGE, 0xe84545, TYPE_CODE, blockSupplier) {
    companion object : BookTypeHolder {
        override val MAX_PAGE: Int
            get() = 2000

        override val TYPE_CODE: Int
            get() = 3
    }

}