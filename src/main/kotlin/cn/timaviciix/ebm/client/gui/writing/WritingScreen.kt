/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-02-11  01:04
 *@Description:
 * 阅读GUI作为组件与数据的交换枢纽，通过一个内容缓存处理器连接内容UI渲染与内容数据存储
 * 也可为后续增加富文本解释器提供空间
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui.writing

import cn.timaviciix.ebm.client.gui.BaseScreen
import cn.timaviciix.ebm.client.gui.config.GUIConfig
import cn.timaviciix.ebm.client.gui.config.NoticeTexts
import cn.timaviciix.ebm.client.gui.widgets.*
import cn.timaviciix.ebm.client.gui.widgets.screenmusk.FramePlayWidget
import cn.timaviciix.ebm.client.gui.widgets.screenmusk.ScreenMuskWidget
import cn.timaviciix.ebm.data.templates.package_templates.WarpedBookData
import cn.timaviciix.ebm.network.async.AsyncID
import cn.timaviciix.ebm.network.async.AsyncManager
import cn.timaviciix.ebm.network.async.AsyncSubscriber
import cn.timaviciix.ebm.network.channels.BookExternalNbtChannel
import cn.timaviciix.ebm.network.channels.ReadingStateChannel
import cn.timaviciix.ebm.registers.others.SoundRegister
import cn.timaviciix.ebm.util.StyleUtil
import net.minecraft.client.gui.DrawContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.text.Text

class WritingScreen(
    private val warpedBookData: WarpedBookData,
    private val user: PlayerEntity,
    private val stack: ItemStack
) : BaseScreen(Text.literal("TIMAVICIIX_EBM:WRITE_AND_READ_GUI")) {

    //内容缓存处理器
    private val bufferResolver = WritingBufferResolver(warpedBookData)

    /*组件们*/
    // 关闭，设置，网络，上一页，下一页
    private lateinit var closeBtn: ImageButtonWidget
    private lateinit var settingBtn: ImageButtonWidget
    private lateinit var networkBtn: ImageButtonWidget

    private lateinit var nextPageBtn: ImageButtonWidget
    private lateinit var previousPageBtn: ImageButtonWidget

    // 书名
    private lateinit var bookNameTextWidget: ThreePatchHorizontalFieldWidget

    // 保存
    private lateinit var saveBtn: TextImageButtonWidget

    //左半，右半编辑页
    private lateinit var textLeftContentFieldWidget: EditTextWidget
    private lateinit var textRightContentFieldWidget: EditTextWidget

    //遮罩
    private lateinit var screenMuskWidget: ScreenMuskWidget

    //顶端滑动提示框
    private lateinit var topSlideNoticeWidget: TopSlideNoticeWidget

    // 书名缓存，左右内容页缓存，剪贴板缓存
    //@Imp: 渲染层架构更改后进行内容缓存改造
    private var bufferTitleFieldString = StringBuilder().toString()
    private var bufferLeftFieldString = StringBuilder().toString()
    private var bufferRightFieldString = StringBuilder().toString()
    private var cutBufferFieldString = StringBuilder().toString()
    private var currentPage = 1


    init {
        warpedBookData.readInline(stack)
        //@Imp: 实验调用，后期更改为文章解析器
        bufferTitleFieldString = warpedBookData.bookName.riskyGetValue() ?: ""
        openOperations()
    }

    override fun shouldPause(): Boolean {
        return false
    }

    // 渲染UI背景
    override fun render(context: DrawContext?, mouseX: Int, mouseY: Int, delta: Float) {
        this.renderBackground(context)
        context?.let {
            GUIConfig.READING_GUI_TEXTURE_SET.apply {
                val x1 = (width - sizeWidth) / 2
                val y1 = (height - sizeHeight) / 2 + 5
                it.drawTexture(
                    texture,
                    x1, y1,
                    u.toFloat(), v.toFloat(),
                    sizeWidth, sizeHeight,
                    textureWidth, textureHeight
                )
            }
        }

        super.render(context, mouseX, mouseY, delta)
    }

    override fun init() {
        GUIConfig.CLOSE_BUTTON_TEXTURE_SET.let {
            closeBtn = addDrawableChild(
                ImageButtonWidget(it, width - 25, 5, it.sizeWidth, it.sizeHeight) {
                    close()
                }
            )
        }

        GUIConfig.SETTING_BUTTON_TEXTURE_SET.let {
            settingBtn = addDrawableChild(
                ImageButtonWidget(it, width - 50, 5, it.sizeWidth, it.sizeHeight) {
                    //打开设置界面
                }
            )
        }

        GUIConfig.NETWORK_BUTTON_TEXTURE_SET.let {
            networkBtn = addDrawableChild(
                ImageButtonWidget(it, width - 75, 5, it.sizeWidth, it.sizeHeight) {
                    topSlideNoticeWidget.show(NoticeTexts.INFO_UN_OPEN to GUIConfig.INFO_NOTICE_ICON_TEXTURE_SET)
                }
            )
        }

        GUIConfig.NEXT_BUTTON_TEXTURE_SET.let {
            nextPageBtn = addDrawableChild(
                ImageButtonWidget(it, width - 125, height - 25, it.sizeWidth, it.sizeHeight) {
                    changeOperations()
                }
            )
        }
        GUIConfig.PREVIEW_BUTTON_TEXTURE_SET.let {
            previousPageBtn = addDrawableChild(
                ImageButtonWidget(it, width - 155, height - 25, it.sizeWidth, it.sizeHeight) {
                    changeOperations()
                }
            )
        }

        bookNameTextWidget = addDrawableChild(
            ThreePatchHorizontalFieldWidget(
                GUIConfig.TITLE_THREE_PATCH_TEXTURE_SET,
                textRenderer,
                10, 5, width - 100, 20,
                checkSaveOperation = {
                    warpedBookData.bookName.injectValue(bufferTitleFieldString)
                    doneOperations()
                },
                bufferStringChecker = {
                    bufferTitleFieldString = it
                },
                true,
                textInterpreter(bufferTitleFieldString, true)
            ).apply {
                setChangedListener {
                    bufferTitleFieldString = it
                }
            }.also {
                it.text = bufferTitleFieldString
            }
        )

        val textLeftFieldPosition = Pair(
            (width - GUIConfig.READING_GUI_TEXTURE_SET.sizeWidth) / 2 + 22,
            (height - GUIConfig.READING_GUI_TEXTURE_SET.sizeHeight) / 2 + 17
        )
        textLeftContentFieldWidget = addDrawableChild(
            EditTextWidget(
                textRenderer,
                textLeftFieldPosition.first, textLeftFieldPosition.second,
                116, 170, { s, l -> bufferResolver.onClipboard(s, l) }
            ))
        bufferResolver.injectLeftWidget(textLeftContentFieldWidget)


        val textRightFieldPosition = Pair(
            ((width - GUIConfig.READING_GUI_TEXTURE_SET.sizeWidth) / 2) + 136,
            ((height - GUIConfig.READING_GUI_TEXTURE_SET.sizeHeight) / 2) + 17
        )
        textRightContentFieldWidget = addDrawableChild(
            EditTextWidget(
                textRenderer,
                textRightFieldPosition.first, textRightFieldPosition.second,
                116, 170, { s, l -> bufferResolver.onClipboard(s, l) }
            ))
        bufferResolver.injectRightWidget(textRightContentFieldWidget)

        val operationsBtnPosition = Pair(
            width - 45, height - 25
        )
        saveBtn = addDrawableChild(
            TextImageButtonWidget(
                textRenderer,
                GUIConfig.SHORT_TEXT_BTN_TEXTURE_SET,
                operationsBtnPosition.first,
                operationsBtnPosition.second,
                36, 20,
                textInterpreter("gui.timaviciix_ebm.save_btn", true)
            ) {
                doneOperations()
                saveBook()
            }
        )

        GUIConfig.LOADING_FRAME_TEXTURE_SET.let {
            screenMuskWidget = addDrawableChild(
                ScreenMuskWidget(
                    0, 0, this.width, this.height,
                    FramePlayWidget(it, 10),
                    Text.translatable("gui.timaviciix_ebm.screen_musk_loading"), textRenderer
                )
            ).apply { visible = false }
        }

        GUIConfig.NOTICE_THREE_PATCH_TEXTURE_SET.let {
            topSlideNoticeWidget = addDrawableChild(
                TopSlideNoticeWidget(
                    this.width,
                    10,
                    textRenderer,
                    it
                )
            )
        }
    }

    private fun textInterpreter(inputString: String, withBold: Boolean = false): Text {
        return if (inputString.isEmpty()) {
            if (withBold) Text.empty()
                .setStyle(StyleUtil.BLACK4_BOLD_STYLE)
            else Text.empty()
                .setStyle(StyleUtil.BLACK4_STYLE)
        } else {
            if (Text.translatable(inputString).string != inputString) {
                if (withBold) Text.translatable(inputString)
                    .setStyle(StyleUtil.BLACK4_BOLD_STYLE)
                else Text.translatable(inputString)
                    .setStyle(StyleUtil.BLACK4_STYLE)
            } else {
                if (withBold) Text.literal(inputString)
                    .setStyle(StyleUtil.BLACK4_BOLD_STYLE)
                else Text.literal(inputString)
                    .setStyle(StyleUtil.BLACK4_STYLE)
            }
        }
    }

    private fun saveBook(otherOperation: (d: WarpedBookData) -> Unit = {}) {
        warpedBookData.apply {
            otherOperation(this)
            if (warpedBookData.bookId.getValue() != null) {
                writeInline(stack.orCreateNbt)

                val subscriber = AsyncSubscriber<Boolean>(
                    startUpload = {
                        BookExternalNbtChannel.sendUploadBookExternalNbtRequest(
                            user,
                            this.bookId.valueToString(),
                            writeExternal()
                        )
                    },
                    onUploading = {},
                    onSuccess = {
                        if (it) {
                            topSlideNoticeWidget.show(NoticeTexts.SAVE_SUCCESS to GUIConfig.SUCCESS_NOTICE_ICON_TEXTURE_SET)
                        } else {
                            topSlideNoticeWidget.show(NoticeTexts.SAVE_FAILED to GUIConfig.FAILED_NOTICE_ICON_TEXTURE_SET)
                        }
                    },
                    onFailed = { topSlideNoticeWidget.show(NoticeTexts.SAVE_FAILED to GUIConfig.FAILED_NOTICE_ICON_TEXTURE_SET) },
                    onTimeout = { topSlideNoticeWidget.show(NoticeTexts.SAVE_TIMEOUT to GUIConfig.WAIT_NOTICE_ICON_TEXTURE_SET) },
                )
                //@Imp: 变量测试
                val publish = AsyncManager.publish(AsyncID.UPLOAD_EXTERNAL_BOOK_REQUEST, subscriber)
                if (!publish) {
                    topSlideNoticeWidget.show(NoticeTexts.SAVE_WAIT to GUIConfig.WAIT_NOTICE_ICON_TEXTURE_SET)
                }
            }
        }
    }


    //@Imp: Change Title Widget State
    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        bookNameTextWidget.onScreenClick(mouseX, mouseY)
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun openOperations() {
        GUIConfig.BufferFromMixin.toggleMixin()
        SoundRegister.BookSounds.playOpenSounds(user)
        ReadingStateChannel.sendReadingPlayerUUid(user)
    }

    override fun changeOperations() {
        SoundRegister.BookSounds.playUsingSounds(user)
    }

    override fun doneOperations() {
        // 初次编辑注入数据,不编辑书本就不会认主
        if (warpedBookData.editorId.riskyGetValue() == null) {
            warpedBookData.updateInline(stack) {
                editorId.injectValue(user.uuid)
                editor.injectValue(user.name.string)
                author.injectValue(user.name.string)
            }
        }
    }

    override fun closeOperations() {
        saveBook()
        GUIConfig.BufferFromMixin.toggleMixin()
        SoundRegister.BookSounds.playCloseSounds(user)
        ReadingStateChannel.sendReadingPlayerUUid(user)
    }

    override fun close() {
        saveBook()
        closeOperations()
        super.close()
    }
}