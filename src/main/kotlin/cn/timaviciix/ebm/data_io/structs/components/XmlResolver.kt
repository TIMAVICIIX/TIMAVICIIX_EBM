/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.data_io.structs.components
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-07  00:25
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.data_io.structs.components

//XmlResolver是元数据类组件，判断元素是否需要XML存储，提供一个EMPTY预制类，不需要XML存储时直接入参EMPTY，需要时则入参xpath
class XmlResolver(val xpath:MutableList<String>) {

    companion object{
        val EMPTY = XmlResolver(mutableListOf())
    }

}