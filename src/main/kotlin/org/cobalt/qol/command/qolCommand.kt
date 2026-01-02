package org.cobalt.qol.command

import org.cobalt.api.command.Command
import org.cobalt.api.command.annotation.DefaultHandler
import org.cobalt.api.command.annotation.SubCommand
import org.cobalt.api.util.ChatUtils

object qolCommand : Command(
  name = "example",
  aliases = arrayOf("qol", "ea"),
) {

  @DefaultHandler
  fun main() {
    println("qol default handler!")
  }

  @SubCommand
  fun hello(name: String) {
    ChatUtils.sendMessage("Hello, $name!")
  }

}
