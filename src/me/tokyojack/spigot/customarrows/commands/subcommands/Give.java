package me.tokyojack.spigot.customarrows.commands.subcommands;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.tokyojack.spigot.customarrows.Core;
import me.tokyojack.spigot.customarrows.utils.CustomArrow;
import me.tokyojack.spigot.customarrows.utils.subkommand.SubKommand;

public class Give extends SubKommand {

	public Give() {
		super("<type> (player)");
	}

	@Override
	public boolean execute(CommandSender commandSender, String[] args) {
		Map<String, CustomArrow> customArrows = Core.getPlugin().getCustomArrows();

		if (args.length <= 0) {
			
			// Get's all the custom potions key's (name),
			// Runs toString on them (just in case),
			// Removes " Elixir" from the name, 
			// Joins them together with ", " between,
			// Removes "[" and "]" as it's a list
			String customArrowList = Arrays.asList(customArrows.keySet()).stream().map(Object::toString)
					.map(item -> item.replace(" Arrow", "")).collect(Collectors.joining(", ")).toString()
					.replace("[", "").replace("]", "");

			commandSender.sendMessage("Custom arrow list: " + customArrowList);
			return false;
		}

		String customArrowType = WordUtils.capitalize(args[0]) + " Arrow";

		if (!customArrows.containsKey(customArrowType)) {
			commandSender.sendMessage("That is not a valid custom arrow!");
			return false;
		}

		ItemStack customArrow = customArrows.get(customArrowType).getItem();

		if (args.length >= 2) {
			Player targetPlayer = Bukkit.getPlayer(args[1]);

			if (targetPlayer == null) {
				commandSender.sendMessage("That player isn't online!");
				return false;
			}

			targetPlayer.getInventory().addItem(customArrow);
			return true;
		}

		if (commandSender instanceof Player) {
			((Player) commandSender).getInventory().addItem(customArrow);
		}

		return true;
	}

}