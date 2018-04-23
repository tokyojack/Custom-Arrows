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

	@EventHandler(ignoreCancelled = true)
	public void onArrowHitPlayer(PlayerShootPlayerEvent event) {
		Projectile arrow = event.getProjectile();

		// Checks if the arrow has the Metadata "ca", meaning it is a custom arrow
		if (!arrow.hasMetadata("ca"))
			return;

		String customArrowName = arrow.getMetadata("ca").get(0).asString();

		// Yes I could be using streams/foreach, but it performs worse
		Map<String, CustomArrow> customArrows = Core.getPlugin().getCustomArrows();

		// Checks if the custom arrow type is avaliable
		if (customArrows.containsKey(customArrowName)) {
			
			// Loops through all the potion effects for the arrow and applies them to the victim
			for (PotionEffect potionEffect : customArrows.get(customArrowName).getPotionEffects()) {
				event.getVictim().addPotionEffect(potionEffect);
			}
		}
		
		arrow.removeMetadata("ca", Core.getPlugin());
	}
}
