package cn.timaviciix.ebm.client.rich_text.content_stream.interpreters

import cn.timaviciix.ebm.client.rich_text.content_stream.interfaces.BlockInterpreter
import cn.timaviciix.ebm.client.rich_text.content_stream.interfaces.FieldBinder
import cn.timaviciix.ebm.client.rich_text.content_stream.interfaces.FieldBinder.Companion.applyFieldBindings
import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.util.Identifier

object ImageInterpreter : BlockInterpreter<String, ImageInterpreter.ImageData>("image") {

    data class ImageData(
        var id: Identifier = Identifier(GlobalData.MOD_ID, "noting"),
        var imageWidth: Int = 0,
        var imageHeight: Int = 0
    )

    override fun resolveContent(segment: String): ImageData {
        val data = ImageData()
        applyFieldBindings(segment, data, listOf(
            FieldBinder(ImageData::id) { Identifier(GlobalData.MOD_ID, it) },
            FieldBinder(ImageData::imageWidth) { it.toIntOrNull() ?: 0 },
            FieldBinder(ImageData::imageHeight) { it.toIntOrNull() ?: 0 }
        ))
        return data
    }
}
