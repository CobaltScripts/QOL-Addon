package org.cobalt.qol

import org.cobalt.qol.command.qolCommand
import org.cobalt.qol.module.qolModule
import org.cobalt.api.addon.Addon
import org.cobalt.api.command.CommandManager
import org.cobalt.api.module.Module

object qol : Addon() {

  override fun onLoad() {
    CommandManager.register(qolCommand)
    println("qol loaded!")
  }

  override fun onUnload() {
    println("qol unloaded!")
  }

  override fun getModules(): List<Module> {
    return listOf(qolModule)
  }

}
