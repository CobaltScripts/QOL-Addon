package org.cobalt.qol.module

import org.cobalt.Cobalt.mc
import org.cobalt.api.event.EventBus
import org.cobalt.api.event.annotation.SubscribeEvent
import org.cobalt.api.event.impl.client.TickEvent
import org.cobalt.api.module.Module
import org.cobalt.api.module.setting.impl.*

object AutoSprint : Module(
  name = "Auto Sprint",
) {

  var enabled by CheckboxSetting(
    name = "Enabled",
    description = "Toggle auto sprint on or off",
    defaultValue = false
  )

  @SubscribeEvent
  fun onTick(event: TickEvent.Start) {
    if (!enabled) {
      return
    }

    mc.options.sprintKey.isPressed = true
  }

}
