/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-13  22:06
 *@Description: 接口Item类，定义抽象操作
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item

import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.util.Identifier
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

open class BaseItem(nameColor: Int, settings: Settings) : Item(settings) {

//    RegistryHandler
//    @Imp: Deprecated

//    fun registryMe(name: String, self:Item): Item {
//        return Registry.register(Registries.ITEM, Identifier(GlobalData.MOD_ID, name),self )
//    }

    private var nameStyle: Style = Style.EMPTY.withColor(TextColor.fromRgb(nameColor))

    override fun getName(): Text {
        return super.getName().copy().setStyle(nameStyle)
    }

    override fun getName(stack: ItemStack?): Text {
        return super.getName(stack).copy().setStyle(nameStyle)
    }

}