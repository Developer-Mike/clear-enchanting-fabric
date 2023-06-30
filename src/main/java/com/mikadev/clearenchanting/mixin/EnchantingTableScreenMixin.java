package com.mikadev.clearenchanting.mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.ingame.EnchantingPhrases;
import net.minecraft.client.gui.screen.ingame.EnchantmentScreen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

@Mixin(EnchantmentScreen.class)
public class EnchantingTableScreenMixin {
    @Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/EnchantingPhrases;generatePhrase(Lnet/minecraft/client/font/TextRenderer;I)Lnet/minecraft/text/StringVisitable;"))
    private StringVisitable onRandomTextGet(EnchantingPhrases parent, TextRenderer textRenderer, int width,
            @Local(ordinal = 5) LocalIntRef l) {
        EnchantmentScreen screen = (EnchantmentScreen) (Object) this;
        int enchantmentId = screen.getScreenHandler().enchantmentId[l.get()];
        int enchantmentLevel = screen.getScreenHandler().enchantmentLevel[l.get()];

        Enchantment enchantment = Enchantment.byRawId(enchantmentId);
        if (enchantment == null) return parent.generatePhrase(textRenderer, width);
        Text enchantmentName = Enchantment.byRawId(enchantmentId).getName(enchantmentLevel);

        return textRenderer.getTextHandler().trimToWidth(enchantmentName, width, Style.EMPTY);
    }
}