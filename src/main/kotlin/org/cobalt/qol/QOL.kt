package org.cobalt.qol

import org.cobalt.api.addon.Addon
import org.cobalt.api.event.EventBus
import org.cobalt.api.module.Module
import org.cobalt.qol.module.AutoSprint

object qol : Addon() {

  override fun onLoad() {
    println("qol loaded!")
    EventBus.register(AutoSprint)
  }

  override fun onUnload() {
    println("qol unloaded!")
  }

  override fun getModules(): List<Module> {
    return listOf(AutoSprint)
  }

}
