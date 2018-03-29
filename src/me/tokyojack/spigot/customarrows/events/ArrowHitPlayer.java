package me.tokyojack.spigot.customarrows.events;

import java.util.Map;

import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;

import me.tokyojack.spigot.customarrows.Core;
import me.tokyojack.spigot.customarrows.utils.CustomArrow;
import me.tokyojack.spigot.customarrows.utils.PlayerShootPlayerEvent;

public class ArrowHitPlayer implements Listener {

	public ArrowHitPlayer(Core core) {
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void onArrowHitPlayer(PlayerShootPlayerEvent event) {
		Projectile arrow = event.getProjectile();

		if (!arrow.hasMetadata("ca"))
			return;

		String customArrowName = arrow.getMetadata("ca").get(0).asString();

		// Yes I could be using streams/foreach, but they perform worse
		Map<String, CustomArrow> customArrows = Core.getPlugin().getCustomArrows();

		if (customArrows.containsKey(customArrowName)) {
			for (PotionEffect potionEffect : customArrows.get(customArrowName).getPotionEffects()) {
				event.getVictim().addPotionEffect(potionEffect);
			}

			arrow.removeMetadata("ca", Core.getPlugin());

		}

	}
}
