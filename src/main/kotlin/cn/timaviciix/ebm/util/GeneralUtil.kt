/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.util
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-13  23:32
 *@Description: 全局方法包
 *@Version: 1.0
 */

package cn.timaviciix.ebm.util

import io.wispforest.owo.nbt.NbtKey
import net.minecraft.client.font.FontStorage
import net.minecraft.client.font.TextRenderer
import net.minecraft.item.ItemStack
import net.minecraft.text.Style
import java.util.*
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible


object GeneralUtil {


    //UUID class
    object Uuid {
        fun generateTicket(): String {
            val ticket = UUID.randomUUID().toString()
            return ticket.replace("-".toRegex(), "")
        }

        fun generateFullUUID():UUID = UUID.randomUUID()

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

    //Nbt Operations final class
    //@Imp: extendsOperations It can be used to accompany the operation empty, and the overall situation of the code is evaluated later to consider deletion and modification
    object Nbt {

        fun <T : Any> setNbtValue(
            stack: ItemStack,
            key: NbtKey<T>,
            value: T,
            extendsOperation: ((value: ItemStack) -> Unit)?
        ): T {
            extendsOperation?.let { extendsOperation(stack) }
            stack.put(key, value)
            return value
        }

        fun <T : Any> getNbtValue(
            stack: ItemStack,
            key: NbtKey<T>,
            default: T,
            extendsOperation: ((value: ItemStack) -> Unit)?
        ): T {
            extendsOperation?.let { extendsOperation(stack) }
            return if (stack.has(key)) stack.get(key) else default
        }

    }

    //Kotlin Reflect
    fun <T> getPropertyName(property: KProperty<T>): String {
        return property.name.lowercase(Locale.getDefault())
    }

}