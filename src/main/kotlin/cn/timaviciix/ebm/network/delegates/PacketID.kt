/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.network.news.delegates
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-26  21:34
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.network.delegates

import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.util.Identifier
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class PacketID : ReadOnlyProperty<Any, Identifier> {
    override fun getValue(thisRef: Any, property: KProperty<*>): Identifier {
        return Identifier(GlobalData.MOD_ID, GlobalData.MOD_ID + property.name.lowercase())
    }
}