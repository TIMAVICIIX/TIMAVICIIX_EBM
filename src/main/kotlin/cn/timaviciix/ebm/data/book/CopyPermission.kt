package cn.timaviciix.ebm.data.book

import java.util.*


//@Imp: Shouldn't Use Default Construction Method!!!
class CopyPermission private constructor(
    private var stampingState: Boolean = false,
    private var copyGrantees: MutableList<UUID>
) {

    companion object {
        //Use this!
        fun createCopyPermission(stampingState: Boolean = false): CopyPermission {
            return CopyPermission(stampingState, mutableListOf())
        }

        fun duplicateCopyPermission(stampingState: Boolean, copyGrantees: MutableList<UUID>): CopyPermission {
            return CopyPermission(stampingState, copyGrantees)
        }

    }

    fun toggleStamping() {
        stampingState = !stampingState
    }

    fun getStampingState() = stampingState

    fun checkPermission(playerUUID: UUID): Boolean = copyGrantees.contains(playerUUID)

    fun getCopyGrantees():List<UUID> = copyGrantees
    fun addGrantee(playerUUID: UUID) {
        if (!copyGrantees.contains(playerUUID)) copyGrantees.add(playerUUID)
    }

    fun removeGrantee(playerUUID: UUID) = copyGrantees.remove(playerUUID)

    fun flushGrantees() = copyGrantees.clear()

}
