package cn.timaviciix.ebm.client.rich_text.rendering.interfaces

import net.fabricmc.fabric.api.renderer.v1.render.RenderContext

/**
 * 渲染层提供内容块接口，桥接分发层负责实现块类逻辑
 * */

interface ContentRenderer<T> {

    val blockWidth: Int
    val blockHeight: Int

    val screenX: Int
    val screenY: Int

    val interpreterResult: T

    fun render(context: RenderContext)

    fun setAttrs()

    fun onClick()

    fun onSelection()

    fun onChangeEvent()
}