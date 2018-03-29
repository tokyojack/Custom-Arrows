package me.tokyojack.spigot.customarrows.arrows;

import java.util.List;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.tokyojack.spigot.customarrows.utils.CustomArrow;

public class Whither extends CustomArrow {

	public Whither() {
		super("&9Slow");
	}

	@Override
	public List<PotionEffect> getPotionEffects() {
		return potionEffectsToList(createPotionEffect(
			PotionEffectType.WITHER, 5, 0)
			);
	}

}
