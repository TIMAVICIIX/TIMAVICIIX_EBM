/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.client.gui
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-31  15:22
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.client.gui

import cn.timaviciix.ebm.item.blockitems.BaseBlockItem
import cn.timaviciix.ebm.network.Packets
import cn.timaviciix.ebm.util.GlobalData
import io.wispforest.owo.ui.base.BaseUIModelScreen
import io.wispforest.owo.ui.component.ButtonComponent
import io.wispforest.owo.ui.component.LabelComponent
import io.wispforest.owo.ui.container.FlowLayout
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.util.Identifier


class ReadingScreen(private val awakeMaster: BaseBlockItem, private val awakeUser: PlayerEntity) :
    BaseUIModelScreen<FlowLayout>(
        FlowLayout::class.java,
        DataSource.asset(Identifier(GlobalData.MOD_ID, "reading_ui"))
    ) {

    private val textFont = Identifier(GlobalData.MOD_ID, "font/alima")

    override fun build(p0: FlowLayout?) {
        p0?.let {


            p0.childById(ButtonComponent::class.java, "BackBtn")?.onPress {
                close()
            }
            p0.childById(LabelComponent::class.java, "TextDisplayLabelLeft")?.apply {
                text(
                    Text.translatable("text.timaviciix_ebm.example_display")
                        .setStyle(
                            Style.EMPTY.withColor(TextColor.fromRgb(0x000000))
                                .withFont(textFont)

                        )
                )
            }

            p0.childById(ButtonComponent::class.java, "inspector-button")
                ?.onPress { uiAdapter.toggleInspector() }
        }
    }

    override fun close() {
        awakeMaster.alreadyReading = false
        Packets.sendReadingPlayerUUid(awakeUser)
        super.close()
    }

}