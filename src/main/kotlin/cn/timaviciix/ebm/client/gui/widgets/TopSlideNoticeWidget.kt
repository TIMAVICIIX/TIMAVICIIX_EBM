/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui.widgets
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-05-31  17:27
 *@Description:
 * 顶部滑动提示框，背景为三宫格分割纹理，配合一个icon与自定义文本，展示时配置滑动速度与保持时间
 * 首先根据icon与文本宽度渲染三宫格背景纹理，其次渲染icon与文本
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui.widgets

import cn.timaviciix.ebm.client.gui.config.GUIConfig
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder
import net.minecraft.client.gui.widget.ClickableWidget
import net.minecraft.text.Text
import java.util.*

class TopSlideNoticeWidget(
    private val screenX: Int,
    private val targetY: Int = 10,
    private val textRenderer: TextRenderer,
    private val backgroundTexture: GUIConfig.ThreePatchTextureConfig
) : ClickableWidget(0, targetY, 0, 0, Text.empty()) {

    // 消息队列
    private val noticeQueue: Queue<Pair<Text, GUIConfig.NormalTextureConfig>> = LinkedList()
    private lateinit var currentNotice: Pair<Text, GUIConfig.NormalTextureConfig>

    // 动画参数
    private var defaultDuringTime = 500
    private var countTime = 0
    private var defaultSlideSpeed = 1
    private var padding = 3

    // 位置参数
    private var startY = -backgroundTexture.textureHeight
    private var yPos = startY

    // 布局参数
    private var bgPart1StartX = 0
    private var bgPart2StartX = 0
    private var bgPart2EndX = 0
    private var bgPart3StartX = 0
    private var iconStartX = 0
    private var textStartX = 0
    private var iconStartY = 0
    private var textStartY = 0

    // 动画状态
    private enum class Phase {
        START_SLIDE, SLIDE_IN, SLIDE_OUT, SHOW, HIDE, PROCESSING_QUEUE
    }

    private var widgetState = Phase.HIDE
    private var isProcessingQueue = false

    /**
     * 添加通知到队列并尝试显示
     * @param noticeContent 通知内容（文本和图标）
     * @param duringTime 显示持续时间（毫秒）
     * @param slideSpeed 滑动速度（像素/帧）
     */
    fun show(
        noticeContent: Pair<Text, GUIConfig.NormalTextureConfig>,
        duringTime: Int = defaultDuringTime,
        slideSpeed: Int = defaultSlideSpeed
    ) {
        // 添加到队列
        noticeQueue.offer(noticeContent)

        // 如果当前没有显示通知，开始处理队列
        if (widgetState == Phase.HIDE && !isProcessingQueue) {
            processNextNotice(duringTime, slideSpeed)
        }
    }

    /**
     * 处理队列中的下一个通知
     */
    private fun processNextNotice(duringTime: Int, slideSpeed: Int) {
        if (noticeQueue.isEmpty()) {
            isProcessingQueue = false
            return
        }

        isProcessingQueue = true
        currentNotice = noticeQueue.poll()
        this.defaultDuringTime = duringTime
        this.defaultSlideSpeed = slideSpeed

        visible = true
        widgetState = Phase.START_SLIDE
    }

    override fun appendClickableNarrations(builder: NarrationMessageBuilder?) {}

    override fun renderButton(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        if (visible) {
            when (widgetState) {
                Phase.START_SLIDE -> {
                    calculatedX()
                    widgetState = Phase.SLIDE_IN
                }

                Phase.SLIDE_IN -> {
                    if (yPos < targetY) {
                        yPos += defaultSlideSpeed
                        calculatedY()
                        renderWidget(context)
                    } else {
                        yPos = targetY
                        widgetState = Phase.SHOW
                    }
                }

                Phase.SHOW -> {
                    if (countTime < defaultDuringTime) {
                        countTime++
                        renderWidget(context)
                    } else {
                        countTime = 0
                        widgetState = Phase.SLIDE_OUT
                    }
                }

                Phase.SLIDE_OUT -> {
                    if (yPos > startY) {
                        yPos -= defaultSlideSpeed
                        calculatedY()
                        renderWidget(context)
                    } else {
                        yPos = startY
                        widgetState = Phase.PROCESSING_QUEUE
                    }
                }

                Phase.PROCESSING_QUEUE -> {
                    // 处理队列中的下一个通知
                    if (noticeQueue.isNotEmpty()) {
                        processNextNotice(defaultDuringTime, defaultSlideSpeed)
                    } else {
                        widgetState = Phase.HIDE
                        visible = false
                        isProcessingQueue = false
                    }
                }

                Phase.HIDE -> {
                    // 无操作，等待新通知
                }
            }
        }
    }

    private fun renderWidget(context: DrawContext?) {
        context?.apply {
            //Render Background
            backgroundTexture.let {
                //render Bg Part1
                drawTexture(
                    it.texture,
                    bgPart1StartX,
                    yPos,
                    it.sideWidth,
                    it.textureHeight,
                    it.u.toFloat(),
                    it.v.toFloat(),
                    it.sideWidth,
                    it.textureHeight,
                    it.textureWidth,
                    it.textureHeight
                )

                //render Bg Part2
                val widthOffset = if (bgPart2EndX > it.middleOffset) {
                    it.middleOffset
                } else {
                    bgPart2EndX
                }
                drawTexture(
                    it.texture,
                    bgPart2StartX,
                    yPos,
                    bgPart2EndX,
                    it.textureHeight,
                    (it.sideWidth + it.u).toFloat(),
                    it.v.toFloat(),
                    widthOffset,
                    it.textureHeight,
                    it.textureWidth,
                    it.textureHeight
                )

                //render Bg Part3
                drawTexture(
                    it.texture,
                    bgPart3StartX,
                    yPos,
                    it.sideWidth,
                    (it.u + it.sideWidth + it.middleOffset).toFloat(),
                    it.v.toFloat(),
                    it.sideWidth,
                    it.textureHeight,
                    it.textureWidth,
                    it.textureHeight
                )
            }

            //Render Icon And Text
            drawTexture(
                currentNotice.second.texture,
                iconStartX,
                iconStartY,
                0f, 0f,
                currentNotice.second.sizeWidth,
                currentNotice.second.sizeHeight,
                currentNotice.second.sizeWidth,
                currentNotice.second.sizeHeight
            )

            drawText(
                textRenderer,
                currentNotice.first,
                textStartX,
                textStartY,
                GUIConfig.blackTextColor4.rgb,
                false
            )
        }
    }

    private fun calculatedX() {
        val textWidth = textRenderer.getWidth(currentNotice.first)
        val iconWidth = currentNotice.second.sizeWidth
        val middleWidth = textWidth + iconWidth + padding

        bgPart2StartX = (screenX - middleWidth) / 2
        bgPart2EndX = middleWidth
        bgPart1StartX = bgPart2StartX - backgroundTexture.sideWidth
        bgPart3StartX = (screenX + middleWidth) / 2

        iconStartX = bgPart2StartX
        textStartX = iconStartX + currentNotice.second.sizeWidth + padding
    }

    private fun calculatedY() {
        iconStartY = yPos + (backgroundTexture.textureHeight - currentNotice.second.sizeHeight) / 2
        textStartY = yPos + (backgroundTexture.textureHeight - textRenderer.fontHeight) / 2
    }

    private fun resetDefault() {
        defaultDuringTime = 500
        defaultSlideSpeed = 1
        yPos = startY
    }

    /**
     * 清空所有待显示的通知
     */
    fun clearQueue() {
        noticeQueue.clear()
        if (widgetState == Phase.PROCESSING_QUEUE) {
            widgetState = Phase.HIDE
            visible = false
            isProcessingQueue = false
        }
    }

    /**
     * 获取队列中待显示的通知数量
     */
    fun getQueueSize(): Int = noticeQueue.size
}