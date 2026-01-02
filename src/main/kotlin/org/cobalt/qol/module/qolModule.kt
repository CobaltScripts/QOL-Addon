package org.cobalt.qol.module

import org.cobalt.Cobalt.mc
import org.cobalt.api.event.annotation.SubscribeEvent
import org.cobalt.api.event.impl.client.TickEvent
import org.cobalt.api.module.Module
import org.cobalt.api.module.setting.impl.*

object AutoSprint : Module(
  name = "Auto Sprint",
) {
  val autoSprintToggle by CheckboxSetting(
    name = "Enabled",
    "is Auto Sprint enabled?",
    true
  )

  @SubscribeEvent
  fun onTick(e: TickEvent.Start) {
    mc.options.sprintKey.isPressed = autoSprintToggle
  }
}
