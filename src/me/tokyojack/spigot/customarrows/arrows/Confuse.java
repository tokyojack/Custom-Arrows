package me.tokyojack.spigot.customarrows.arrows;

import java.util.List;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.tokyojack.spigot.customarrows.utils.CustomArrow;

public class Confuse extends CustomArrow {

	public Confuse() {
		super("&8Confuse");
	}

	@Override
	public List<PotionEffect> getPotionEffects() {
		return potionEffectsToList(createPotionEffect(
			PotionEffectType.CONFUSION, 5, 0)
			);
	}

}
