package org.cobalt.qol.module

import net.minecraft.client.Minecraft
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.chat.Component
import org.cobalt.api.module.Module
import org.cobalt.api.module.setting.impl.CheckboxSetting
import org.cobalt.api.module.setting.impl.TextSetting

object NameProtect : Module(
  name = "Name Protect"
) {

  private val mc: Minecraft =
    Minecraft.getInstance()

  var enabled by CheckboxSetting(
    name = "Enabled",
    description = "Toggle name protect on or off",
    defaultValue = false
  )

  @JvmStatic
  var nick by TextSetting(
    name = "Nickname",
    description = "Your new nickname",
    defaultValue = "Cobalt User"
  )

  @JvmStatic
  fun getName(): MutableComponent {
    return Component.literal(nick)
  }

  @JvmStatic
  fun getMcIGN(): String {
    return mc.player?.gameProfile?.name.toString()
  }

}
