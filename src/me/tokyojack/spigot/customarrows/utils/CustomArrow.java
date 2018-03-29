package me.tokyojack.spigot.customarrows.utils;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import lombok.Getter;

@Getter
public abstract class CustomArrow {

	private String name;
	private List<String> lore;

	public CustomArrow(String name, String... lore) {
		this.name = ChatColor.translateAlternateColorCodes('&', name + " &fArrow");
		Arrays.asList(lore).forEach(loreLine -> this.lore.add(ChatColor.translateAlternateColorCodes('&', loreLine)));
	}

	public abstract List<PotionEffect> getPotionEffects();

	protected List<PotionEffect> potionEffectsToList(PotionEffect... potionEffects) {
		return Arrays.asList(potionEffects);
	}

	protected PotionEffect createPotionEffect(PotionEffectType potionEffectType, int duration, int level) {
		return new PotionEffect(potionEffectType, duration * 20, level - 1);
	}

	public ItemStack getItem() {
		return new ItemBuilder(Material.ARROW).setName(this.name).setLore(this.lore).toItemStack();
	}

}
