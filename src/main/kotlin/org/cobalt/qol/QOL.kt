package org.cobalt.qol

import org.cobalt.api.addon.Addon
import org.cobalt.api.event.EventBus
import org.cobalt.api.module.Module
import org.cobalt.qol.module.AutoSprint
import org.cobalt.qol.module.DiscordRPC
import org.cobalt.qol.module.Misc
import org.cobalt.qol.module.NameProtect

object QOL : Addon() {

  override fun onLoad() {
    println("QOL Addon loaded!")
  }

  override fun onUnload() {
    println("QOL Addon unloaded!")
  }

  override fun getModules(): List<Module> {
    return listOf(AutoSprint, NameProtect, DiscordRPC, Misc)
  }

}
