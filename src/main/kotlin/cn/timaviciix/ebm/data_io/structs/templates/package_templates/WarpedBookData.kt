/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.templates.package_templates
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  00:45
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.templates.package_templates

import cn.timaviciix.ebm.data_io.structs.components.TemplateDelegate
import cn.timaviciix.ebm.data_io.structs.components.XmlResolver
import cn.timaviciix.ebm.data_io.structs.templates.original_templates.ElementTemplate
import cn.timaviciix.ebm.data_io.structs.templates.original_templates.WarpedTemplate

class WarpedBookData : WarpedTemplate(
) {
    val bookId by template<String>()

}