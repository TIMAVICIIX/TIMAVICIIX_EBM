/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.templates.package_templates
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  00:45
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.templates.package_templates

import cn.timaviciix.ebm.data_io.structs.components.nbt.NbtResolver.Companion
import cn.timaviciix.ebm.data_io.structs.components.xml.ListStringXmlResolver
import cn.timaviciix.ebm.data_io.structs.templates.original_templates.WarpedTemplate
import cn.timaviciix.ebm.util.GeneralUtil
import net.minecraft.text.Text
import java.time.LocalDateTime

class WarpedBookData(
    private val defaultMaxPage: Int,
    private val defaultTypeCode: Int,
) : WarpedTemplate(
) {
    val bookId by template(
        nbtResolver = Companion.StringResolver(),
        default = GeneralUtil.Uuid.generateShortUUID()
    )
    val bookName by template(
        nbtResolver = Companion.StringResolver(),
        default = Text.translatable("data.timaviciix_ebm.book_name_unknown").string
    )

    val editor by template(
        nbtResolver = Companion.StringResolver(),
        default = Text.translatable("data.timaviciix_ebm.author_name_unknown").string
    )

    val editorId by template(nbtResolver = Companion.UUIDResolver())

    val author by template(
        nbtResolver = Companion.StringResolver(),
        default = Text.translatable("data.timaviciix_ebm.author_name_unknown").string
    )

    val stampingState by template(nbtResolver = Companion.BooleanResolver(), default = false)
    val isCopies by template(nbtResolver = Companion.BooleanResolver(), default = false)
    val copyGrantees by template(nbtResolver = Companion.UUIDListResolver(), default = mutableListOf())

    val createDate by template(
        nbtResolver = Companion.StringResolver(),
        default = LocalDateTime.now().toString()
    )

    val maxPage by template(nbtResolver = Companion.IntResolver(), default = defaultMaxPage)
    val typeCode by template(nbtResolver = Companion.IntResolver(), default = defaultTypeCode)
    val pageTag by template(nbtResolver = Companion.IntListResolver())
    val lastReadingPage by template(nbtResolver = Companion.IntResolver())

    val content by template(
        xmlResolverSupplier = ListStringXmlResolver(
            "content",
            "page",
            mutableMapOf(),
            "page" to Pair(1, true)
        ),
        nbtResolver = Companion.NullResolver()
    )
}