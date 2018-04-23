package me.tokyojack.spigot.customarrows.events;

import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;

import me.tokyojack.spigot.customarrows.Core;
import me.tokyojack.spigot.customarrows.utils.CustomArrow;

public class ArrowShoot implements Listener {

	@EventHandler(ignoreCancelled = true)
	public void onProjectileLaunch(ProjectileLaunchEvent event) {
		
		// Checks if projectile is an arrow, as snowballs, fieballs, etc. count as projectiles
		if (!(event.getEntity() instanceof Arrow))
			return;

		Arrow arrow = (Arrow) event.getEntity();

		// Checks if the shooter is a player (as dispensers can launch projectiles)
		if (!(arrow.getShooter() instanceof Player))
			return;

		Player player = (Player) arrow.getShooter();
		PlayerInventory inventory = player.getInventory();

		// May be unneeded
		if (!(inventory.contains(Material.ARROW)))
			return;

		// Finds what slot the arrow shot from
		int arrowSlot = isShotFromMainHand(player) ? inventory.first(Material.ARROW) : inventory.getHeldItemSlot();
		ItemStack arrowItem = inventory.getItem(arrowSlot);

		if (arrowItem == null || !arrowItem.getType().equals(Material.ARROW)) {
			arrowSlot = inventory.first(Material.ARROW);
			arrowItem = inventory.getItem(arrowSlot);
		}

		if (!arrowItem.hasItemMeta())
			return;

		if (!arrowItem.getItemMeta().hasDisplayName())
			return;

		String displayName = ChatColor.stripColor(arrowItem.getItemMeta().getDisplayName());
		Map<String, CustomArrow> customArrows = Core.getPlugin().getCustomArrows();

		// Checks if the arrow is an custom arrow
		if (customArrows.containsKey(displayName)) 
			// Gives the arrow shot the Metadata with the key "ca" and the value of the arrow's name
			arrow.setMetadata("ca", new FixedMetadataValue(Core.getPlugin(),
					ChatColor.stripColor(customArrows.get(displayName).getItem().getItemMeta().getDisplayName())));

		

	}

	private boolean isShotFromMainHand(Player player) {
		ItemStack mainHand = player.getInventory().getItemInHand();
		return ((mainHand != null) && (mainHand.getType().equals(Material.BOW)));
	}

}
