package me.tokyojack.spigot.customarrows.arrows;

import java.util.List;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.tokyojack.spigot.customarrows.utils.CustomArrow;

public class Slow extends CustomArrow {

	public Slow() {
		super("&7Slow");
	}

	@Override
	public List<PotionEffect> getPotionEffects() {
		return potionEffectsToList(createPotionEffect(
			PotionEffectType.SLOW, 5, 0)
			);
	}

}
