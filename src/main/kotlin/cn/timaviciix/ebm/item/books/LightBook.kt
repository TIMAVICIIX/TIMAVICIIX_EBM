/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.item.books
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-15  23:24
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.item.books

import cn.timaviciix.ebm.EBMItemGroup
import io.wispforest.owo.itemgroup.OwoItemSettings

class LightBook : BookItem(0x55e9bc,OwoItemSettings().group(EBMItemGroup.EBM_ITEM_GROUP).maxCount(64)) {
}