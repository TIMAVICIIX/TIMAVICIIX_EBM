/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.books
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-15  23:23
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.books

import cn.timaviciix.ebm.EBMItemGroup
import io.wispforest.owo.itemgroup.OwoItemSettings
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item

class GeneralBook : BookItem, Item(OwoItemSettings().group(EBMItemGroup.EBM_ITEM_GROUP).maxCount(64))