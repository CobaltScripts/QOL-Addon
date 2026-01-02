package org.cobalt.qol.module

import java.awt.Color
import org.cobalt.api.module.Module
import org.cobalt.api.module.setting.impl.*
import org.lwjgl.glfw.GLFW

object qolModule : Module(
  name = "qol Module",
) {

  val checkbox by CheckboxSetting(
    name = "Checkbox",
    description = "qol checkbox setting",
    defaultValue = true
  )

  val color by ColorSetting(
    name = "Color",
    description = "qol color setting",
    defaultValue = Color.WHITE.rgb
  )

  val keyBind by KeyBindSetting(
    name = "KeyBind",
    description = "qol keybind setting",
    defaultValue = GLFW.GLFW_KEY_ESCAPE
  )

  val mode by ModeSetting(
    name = "Mode",
    description = "qol mode setting",
    defaultValue = 0,
    options = arrayOf("Mode1", "Mode2", "Mode3")
  )

  val range by RangeSetting(
    name = "Range",
    description = "qol range setting",
    defaultValue = Pair(3.0, 5.0),
    min = 0.0,
    max = 10.0
  )

  val slider by SliderSetting(
    name = "Slider",
    description = "qol slider setting",
    defaultValue = 3.0,
    min = 1.0,
    max = 10.0
  )

  val text by TextSetting(
    name = "Text",
    description = "qol text setting",
    defaultValue = "Hello"
  )

}
