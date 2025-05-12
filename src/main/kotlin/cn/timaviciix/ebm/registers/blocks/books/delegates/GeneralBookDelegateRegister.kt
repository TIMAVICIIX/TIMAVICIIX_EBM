/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.blocks.books.delegates
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  23:25
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.blocks.books.delegates

import cn.timaviciix.ebm.block.books.BaseBookBlock

class GeneralBookDelegateRegister<T : BaseBookBlock>(blockSupplier: () -> T) :
    BaseBookRegistryDelegate<T>(MAX_PAGE, 0xff8264, TYPE_CODE, blockSupplier) {

    companion object : BookTypeHolder {
        override val MAX_PAGE: Int
            get() = 800

        override val TYPE_CODE: Int
            get() = 1
    }
}