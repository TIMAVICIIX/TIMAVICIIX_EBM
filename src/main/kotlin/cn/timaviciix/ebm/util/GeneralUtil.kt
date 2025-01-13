/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.util
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-13  23:32
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.util

import java.util.*


object GeneralUtil {


    fun generateTicket(): String {
        val ticket = UUID.randomUUID().toString()
        return ticket.replace("-".toRegex(), "")
    }

    fun generateShortUUID(): String {
        val chars: Array<String> = arrayOf(
            "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"
        )

        //Call the Java-provided object that generates a random string: 32-bit, hexadecimal, containing -
        val shortBuffer = StringBuffer()
        val uuid = UUID.randomUUID().toString().replace("-", "")

        for (i in 0..7) {
            val str = uuid.substring(i * 4, i * 4 + 4)
            val x = str.toInt(16)

            shortBuffer.append(chars[x % 0x3E])
        }
        return shortBuffer.toString()
    }
}