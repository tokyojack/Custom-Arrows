package me.tokyojack.spigot.customarrows.events;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import me.tokyojack.spigot.customarrows.Core;

public class CustomArrowGround implements Listener {
	
	public CustomArrowGround(Core core) {
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void onHitGround(ProjectileHitEvent event) {
		Entity arrow = event.getEntity();

		if (!(arrow instanceof Arrow))
			return;

		if (!arrow.hasMetadata("ca"))
			return;

		Core.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(Core.getPlugin(), new Runnable() {
			public void run() {
				arrow.removeMetadata("ca", Core.getPlugin());
			}
		}, 1);

	}
}
