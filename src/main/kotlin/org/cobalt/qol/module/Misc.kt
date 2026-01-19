package org.cobalt.qol.module

import net.minecraft.client.Minecraft
import net.minecraft.world.inventory.ChestMenu
import net.minecraft.world.inventory.ClickType
import net.minecraft.world.item.BlockItem
import net.minecraft.world.level.block.Blocks
import org.cobalt.api.event.EventBus
import org.cobalt.api.event.annotation.SubscribeEvent
import org.cobalt.api.event.impl.client.TickEvent
import org.cobalt.api.module.Module
import org.cobalt.api.module.setting.impl.CheckboxSetting
import org.cobalt.api.util.InventoryUtils
import org.cobalt.api.util.MouseClickType

object Misc : Module("Misc") {

  var autoHarp by CheckboxSetting(
    name = "Auto Harp",
    description = "Does the harp for you.",
    defaultValue = false
  )

  init {
    EventBus.register(this)
  }

  private val mc: Minecraft = Minecraft.getInstance()
  private var lastInv = 0

  @SubscribeEvent
  fun onTick(event: TickEvent.Start) {
    if (!autoHarp) {
      return
    }

    val menu = mc.player?.containerMenu as? ChestMenu ?: return
    val screen = mc.screen ?: return

    if (!screen.title.string.startsWith("Harp -") || menu.slots.size < 54) return

    val invHash = menu.slots.subList(0, 36)
      .joinToString("") { slot ->
        slot.item.item?.name?.string ?: ""
      }.hashCode()

    if (invHash == lastInv) return
    lastInv = invHash

    repeat(7) {
      val slot = menu.slots[37 + it]

      if ((slot.item.item as? BlockItem)?.block == Blocks.QUARTZ_BLOCK) {
        InventoryUtils.clickSlot(slot.index, MouseClickType.MIDDLE, ClickType.CLONE)
      }
    }
  }

}
