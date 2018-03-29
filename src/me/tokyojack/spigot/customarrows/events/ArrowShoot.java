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

	public ArrowShoot(Core core) {
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void onArrowShoot(ProjectileLaunchEvent event) {
		if (!(event.getEntity() instanceof Arrow))
			return;

		Arrow arrow = (Arrow) event.getEntity();

		if (!(arrow.getShooter() instanceof Player))
			return;

		Player player = (Player) arrow.getShooter();
		PlayerInventory inventory = player.getInventory();

		// May be unneeded
		if (!(inventory.contains(Material.ARROW)))
			return;

		int arrowSlot = isShotFromMainHand(player) ? inventory.first(Material.ARROW) : inventory.getHeldItemSlot();
		ItemStack reference = inventory.getItem(arrowSlot);

		if ((reference == null) || (!reference.getType().equals(Material.ARROW))) {
			arrowSlot = inventory.first(Material.ARROW);
			reference = inventory.getItem(arrowSlot);
		}

		if (!reference.hasItemMeta())
			return;

		if (!reference.getItemMeta().hasDisplayName())
			return;

		String displayName = ChatColor.stripColor(reference.getItemMeta().getDisplayName());
		Map<String, CustomArrow> customArrows = Core.getPlugin().getCustomArrows();

		if (customArrows.containsKey(displayName)) {
			arrow.setMetadata("ca", new FixedMetadataValue(Core.getPlugin(),
					ChatColor.stripColor(customArrows.get(displayName).getItem().getItemMeta().getDisplayName())));

		}

	}

	private boolean isShotFromMainHand(Player player) {
		ItemStack mainHand = player.getInventory().getItemInHand();
		return ((mainHand != null) && (mainHand.getType().equals(Material.BOW)));
	}

}
