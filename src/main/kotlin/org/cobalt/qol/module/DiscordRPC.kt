package org.cobalt.qol.module

import meteordevelopment.discordipc.DiscordIPC
import meteordevelopment.discordipc.RichPresence
import org.cobalt.Cobalt
import org.cobalt.api.event.EventBus
import org.cobalt.api.event.annotation.SubscribeEvent
import org.cobalt.api.event.impl.client.TickEvent
import org.cobalt.api.module.Module
import org.cobalt.api.module.setting.impl.CheckboxSetting

object DiscordRPC : Module(
  name = "Discord RPC"
) {

  var enabled by CheckboxSetting(
    name = "Enabled",
    description = "Toggle discord RPC on or off",
    defaultValue = false
  )

  private val rpc: RichPresence = RichPresence()
  private var lastUpdate: Long = System.currentTimeMillis()

  private val states = listOf(
    "Arguing With Jerry",
    "Selling Dirt",
    "Not Macroing (Trust)",
    "Fell Into the Void",
    "Waiting for Diana",
    "Mining Cobble",
    "Talking to Bank Guard",
    "Broke Again",
    "AFK But Not Really",
    "Forgot Arrows",
    "Reorganizing Chests",
    "Selling My Minions",
    "Questioning Life",
    "Lagging in the Hub",
    "Flexing Nonexistent Necron",
    "Staring at Minions",
    "Lost 10M to Taxes",
    "Fishing Seaweed",
    "Mining Dirt Passionately",
    "Touching Grass (Rare)"
  )

  init {
    DiscordIPC.start(1406359679772266608L, null)

    rpc.setStart(System.currentTimeMillis() / 1000L)
    rpc.setLargeImage("logo", "Cobalt 1.0.0")
    rpc.setDetails("Minecraft 1.21.10")
    rpc.setState(states.random())

    DiscordIPC.setActivity(rpc)
    EventBus.register(this)
  }

  @Suppress("unused")
  @SubscribeEvent
  fun onTick(ignored: TickEvent.End) {
    if (!enabled) {
      if (DiscordIPC.isConnected()) {
        DiscordIPC.stop()
      }

      return
    }

    if (!DiscordIPC.isConnected()) {
      DiscordIPC.start(1406359679772266608L, null)

      rpc.setStart(System.currentTimeMillis() / 1000L)
      rpc.setLargeImage("logo", "Cobalt 1.0.0")
      rpc.setDetails("Minecraft 1.21.10")
      rpc.setState(states.random())

      DiscordIPC.setActivity(rpc)
    }

    if (System.currentTimeMillis() - lastUpdate < 1_800_000)
      return

    rpc.setState(states.random())
    DiscordIPC.setActivity(rpc)
    lastUpdate = System.currentTimeMillis()
  }

}
