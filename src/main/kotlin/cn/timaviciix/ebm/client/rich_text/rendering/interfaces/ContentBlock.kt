package cn.timaviciix.ebm.client.rich_text.rendering.interfaces

/**
 * 渲染层提供内容块接口，桥接分发层负责实现块类逻辑
 * */

interface ContentBlock {

    val blockWidth:Int
    val blockHeight:Int

    fun onClick()

    fun onSelection()

}