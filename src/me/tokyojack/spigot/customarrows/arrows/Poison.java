package me.tokyojack.spigot.customarrows.arrows;

import java.util.List;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.tokyojack.spigot.customarrows.utils.CustomArrow;

public class Poison extends CustomArrow {

	public Poison() {
		super("&aPoison");
	}

	@Override
	public List<PotionEffect> getPotionEffects() {
		return potionEffectsToList(createPotionEffect(
			PotionEffectType.POISON, 5, 0)
			);
	}

}
