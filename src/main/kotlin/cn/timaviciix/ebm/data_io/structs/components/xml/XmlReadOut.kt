/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.components.xml
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-17  19:44
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.components.xml

class XmlReadOut<T>(
    val value:T,
    val stringAttributes:Map<String,String>,
    val digitAttributes:Map<String,Int>
) {
}