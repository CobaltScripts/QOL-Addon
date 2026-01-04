package org.cobalt.qol

import org.cobalt.api.addon.Addon
import org.cobalt.api.event.EventBus
import org.cobalt.api.module.Module
import org.cobalt.qol.module.AutoSprint
import org.cobalt.qol.module.DiscordRPC

object QOL : Addon() {

  override fun onLoad() {
    println("QOL Addon loaded!")

    listOf(
      DiscordRPC,
      AutoSprint
    ).forEach(EventBus::register)
  }

  override fun onUnload() {
    println("QOL Addon unloaded!")
  }

  override fun getModules(): List<Module> {
    return listOf(AutoSprint, DiscordRPC)
  }

}
