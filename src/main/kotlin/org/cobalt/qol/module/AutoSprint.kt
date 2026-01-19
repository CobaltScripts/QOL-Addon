package org.cobalt.qol.module

import net.minecraft.client.Minecraft
import org.cobalt.api.event.EventBus
import org.cobalt.api.event.annotation.SubscribeEvent
import org.cobalt.api.event.impl.client.TickEvent
import org.cobalt.api.module.Module
import org.cobalt.api.module.setting.impl.*

object AutoSprint : Module(
  name = "Auto Sprint",
) {

  private val mc: Minecraft =
    Minecraft.getInstance()

  var enabled by CheckboxSetting(
    name = "Enabled",
    description = "Toggle auto sprint on or off",
    defaultValue = false
  )

  init {
    EventBus.register(this)
  }

  @SubscribeEvent
  fun onTick(event: TickEvent.Start) {
    if (!enabled) {
      return
    }

    mc.options.keySprint.isDown = true
  }

}
