package org.cobalt.qol.mixins;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.Component;
import org.cobalt.qol.module.NameProtect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Font.class)
public class NameProtect_FontMixin {

  @Unique
  private static FormattedCharSequence replaceWordWithText(FormattedCharSequence orderedText, String target, MutableComponent replacement) {
    List<String> chars = new ArrayList<>();
    List<Style> styles = new ArrayList<>();

    orderedText.accept((index, style, codePoint) -> {
      chars.add(new String(Character.toChars(codePoint)));
      styles.add(style);
      return true;
    });

    StringBuilder rawBuilder = new StringBuilder(chars.size());
    for (String c : chars) rawBuilder.append(c);
    String raw = rawBuilder.toString();

    if (!raw.contains(target)) return orderedText;

    MutableComponent rebuilt = Component.empty();
    int searchIndex = 0;
    int rawLen = raw.length();

    while (searchIndex < rawLen) {
      int found = raw.indexOf(target, searchIndex);
      if (found == -1) {
        for (int i = searchIndex; i < rawLen; i++) {
          rebuilt.append(Component.literal(chars.get(i)).setStyle(styles.get(i)));
        }

        break;
      }

      for (int i = searchIndex; i < found; i++) {
        rebuilt.append(Component.literal(chars.get(i)).setStyle(styles.get(i)));
      }

      rebuilt.append(replacement);
      searchIndex = found + target.length();
    }

    return rebuilt.getVisualOrderText();
  }

  @ModifyVariable(
    method = "prepareText(Lnet/minecraft/util/FormattedCharSequence;FFIZI)Lnet/minecraft/client/gui/Font$PreparedText;",
    at = @At("HEAD"),
    argsOnly = true
  )
  private FormattedCharSequence modifyMinecraftName(FormattedCharSequence text) {
    if (NameProtect.INSTANCE.getEnabled()) {
      MutableComponent replacement = NameProtect.getName();
      return replaceWordWithText(text, NameProtect.getMcIGN(), replacement);
    }

    return text;
  }

}
