package cn.timaviciix.ebm.data.book

import java.util.*


//@Imp: Shouldn't Use Default Construction Method!!!
class CopyPermission private constructor(
    var stampingState: Boolean = false,
    var isCopies:Boolean = false,
    var copyGrantees: MutableList<UUID>
) {

    companion object {
        //Use this!
        fun createCopyPermission(stampingState: Boolean = false,isCopies: Boolean=false): CopyPermission {
            return CopyPermission(stampingState,isCopies, mutableListOf())
        }

        fun duplicateCopyPermission(stampingState: Boolean,isCopies: Boolean=false, copyGrantees: MutableList<UUID>): CopyPermission {
            return CopyPermission(stampingState,isCopies, copyGrantees)
        }

    }

    fun toggleStamping() {
        stampingState = !stampingState
    }


    fun checkPermission(playerUUID: UUID): Boolean = copyGrantees.contains(playerUUID)

    fun addGrantee(playerUUID: UUID) {
        if (!copyGrantees.contains(playerUUID)) copyGrantees.add(playerUUID)
    }

    fun removeGrantee(playerUUID: UUID) = copyGrantees.remove(playerUUID)

    fun flushGrantees() = copyGrantees.clear()

}
