/**
 *@BelongsProject: TIMAVICIIX_EBM
 *@BelongsPackage: cn.timaviciix.ebm.registers.others
 *@Author: TIMAVICIIX
 *@CreateTime: 2025-01-25  00:32
 *@Description: TODO
 *@Version: 1.0
 */

package cn.timaviciix.ebm.registers.others

import cn.timaviciix.ebm.util.GlobalData
import net.minecraft.client.MinecraftClient
import net.minecraft.client.sound.PositionedSoundInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import net.minecraft.util.math.random.Random
import kotlin.reflect.KProperty

object SoundRegister {

    fun soundRegistryInterface() {

    }


    val WHIP_CRACK: SoundEvent = registrySelf(::WHIP_CRACK)
    val WHIP_SWHOO: SoundEvent = registrySelf(::WHIP_SWHOO)

    val BELL_LING: SoundEvent = registrySelf(::BELL_LING)
    val BELL_WIND_SWING: SoundEvent = registrySelf(::BELL_WIND_SWING)

    val BOOK_CLOSING: SoundEvent = registrySelf(::BOOK_CLOSING)
    val TURNING_PAGE: SoundEvent = registrySelf(::TURNING_PAGE)

    fun registrySelf(property: KProperty<*>): SoundEvent {

        val soundId = property.name.lowercase()
        val targetSoundEvent = SoundEvent.of(Identifier(GlobalData.MOD_ID, soundId))

        return Registry.register(Registries.SOUND_EVENT, Identifier(GlobalData.MOD_ID, soundId), targetSoundEvent)

    }

    object BookSounds{

        fun playOpenSounds(user: PlayerEntity) {
            val volume = 5.0f
            val pitch = 1.0f
            MinecraftClient.getInstance().soundManager.play(
                PositionedSoundInstance(
                    TURNING_PAGE,
                    SoundCategory.BLOCKS,
                    volume,
                    pitch,
                    Random.create(),
                    user.blockPos
                )
            )
        }

        fun playUsingSounds(user: PlayerEntity) {
            val volume = 5.0f
            val pitch = 1.0f
            MinecraftClient.getInstance().soundManager.play(
                PositionedSoundInstance(
                    TURNING_PAGE,
                    SoundCategory.BLOCKS,
                    volume,
                    pitch,
                    Random.create(),
                    user.blockPos
                )
            )
        }

        fun playCloseSounds(user: PlayerEntity) {
            val volume = 5.0f
            val pitch = 1.0f
            MinecraftClient.getInstance().soundManager.play(
                PositionedSoundInstance(
                    BOOK_CLOSING,
                    SoundCategory.BLOCKS,
                    volume,
                    pitch,
                    Random.create(),
                    user.blockPos
                )
            )
        }

    }

}