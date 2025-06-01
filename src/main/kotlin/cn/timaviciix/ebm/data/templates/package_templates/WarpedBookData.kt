/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.templates.package_templates
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  00:45
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data.templates.package_templates

import cn.timaviciix.ebm.data.data_configs.DefaultData
import cn.timaviciix.ebm.data.nbt.NbtResolver.Companion
import cn.timaviciix.ebm.data.nbt.NbtStorageType
import cn.timaviciix.ebm.data.templates.original_templates.WarpedTemplate
import cn.timaviciix.ebm.util.UuidUtil
import net.minecraft.text.Text
import java.time.LocalDateTime

class WarpedBookData(
    defaultMaxPage: Int,
    defaultTypeCode: Int,
) : WarpedTemplate<WarpedBookData>(
) {
    //BOOK BASE INFO
    val bookId by template(
        NbtStorageType.ALL,
        Companion.UUIDResolver(),
        UuidUtil.generateFullUUID()
    )
    val bookName by template(
        NbtStorageType.ALL,
        Companion.StringResolver(),
        Text.translatable("data.timaviciix_ebm.book_name_unknown").string
    )
    val maxPage by template(NbtStorageType.ALL, Companion.IntResolver(), defaultMaxPage)
    val typeCode by template(NbtStorageType.ALL, Companion.IntResolver(), defaultTypeCode)

    //AUTHOR BASE INFO
    val author by template(
        NbtStorageType.ALL,
        Companion.StringResolver(),
        Text.translatable("data.timaviciix_ebm.author_name_unknown").string
    )
    val editor by template(
        NbtStorageType.ALL,
        Companion.StringResolver(),
        Text.translatable("data.timaviciix_ebm.author_name_unknown").string
    )
    val editorId by template(
        NbtStorageType.ALL,
        Companion.UUIDResolver()
    )


    //BOOK STATE INFO
    val stampingState by template(
        NbtStorageType.ALL,
        Companion.BooleanResolver(), false
    )
    val isCopies by template(
        NbtStorageType.ALL,
        Companion.BooleanResolver(), false
    )
    val copyGrantees by template(
        NbtStorageType.ALL,
        Companion.UUIDListResolver(),
        mutableListOf()
    )

    val createDate by template(NbtStorageType.ALL, Companion.StringResolver(), LocalDateTime.now().toString())

    //READING INFO
    val pageTag by template(NbtStorageType.ALL, Companion.IntListResolver(), listOf())
    val lastReadingPage by template(NbtStorageType.ALL, Companion.IntResolver(), DefaultData.START_PAGE_COUNT)

    val content by template(NbtStorageType.ONLY_EXTERNAL, Companion.StringResolver(), "")

}