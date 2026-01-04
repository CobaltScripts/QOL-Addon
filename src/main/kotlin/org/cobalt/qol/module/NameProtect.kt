package org.cobalt.qol.module

import net.minecraft.client.MinecraftClient
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import org.cobalt.api.module.Module
import org.cobalt.api.module.setting.impl.CheckboxSetting
import org.cobalt.api.module.setting.impl.TextSetting

object NameProtect : Module(
  name = "Name Protect"
) {

  private val mc = MinecraftClient.getInstance()

  var enabled by CheckboxSetting(
    name = "Enabled",
    description = "Toggle name protect on or off",
    defaultValue = false
  )

  var nick by TextSetting(
    name = "Nickname",
    description = "Your new nickname",
    defaultValue = "Cobalt User"
  )

  @JvmStatic
  fun getName(): MutableText {
    return Text.literal(nick)
  }

  @JvmStatic
  fun getMcIGN(): String {
    return mc.player?.gameProfile?.name.toString()
  }

}
