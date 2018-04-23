package me.tokyojack.spigot.customarrows.events;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import me.tokyojack.spigot.customarrows.Core;

public class CustomArrowGround implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	public void onHitGround(ProjectileHitEvent event) {
		Entity arrow = event.getEntity();

		// Checks if projectile is an arrow, as snowballs, fieballs, etc. count as projectiles
		if (!(arrow instanceof Arrow))
			return;

		// Checks if the arrow has the Metadata "ca", meaning it is a custom arrow
		if (!arrow.hasMetadata("ca"))
			return;

		// Removes the Metadata one tick late so if it hits a player, it'll register that first
		Core.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(Core.getPlugin(), new Runnable() {
			public void run() {
				arrow.removeMetadata("ca", Core.getPlugin());
			}
		}, 1);

	}
}
