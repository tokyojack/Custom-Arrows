package me.tokyojack.spigot.customarrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.tokyojack.spigot.customarrows.commands.CustomArrowsCommand;
import me.tokyojack.spigot.customarrows.commands.subcommands.Give;
import me.tokyojack.spigot.customarrows.events.ArrowHitPlayer;
import me.tokyojack.spigot.customarrows.events.ArrowShoot;
import me.tokyojack.spigot.customarrows.events.CustomArrowGround;
import me.tokyojack.spigot.customarrows.utils.CustomArrow;
import me.tokyojack.spigot.customarrows.utils.PlayerShootPlayerEvent;
import me.tokyojack.spigot.customarrows.utils.subkommand.SubKommandManager;

public class Core extends JavaPlugin {

	private static Core plugin;

	public static Core getPlugin() {
		return plugin;
	}

	@Getter
	private Map<String, CustomArrow> customArrows = new HashMap<String, CustomArrow>();

	public void onEnable() {
		plugin = this;

		new SubKommandManager(new CustomArrowsCommand(), true).addSubCommand(new Give()).build();

		new PlayerShootPlayerEvent().registerListener(this);

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new ArrowHitPlayer(this), this);
		pm.registerEvents(new ArrowShoot(this), this);
		pm.registerEvents(new CustomArrowGround(this), this);

		Arrays.asList(CustomArrows.values())
				.forEach(customArrow -> customArrows.put(
						ChatColor.stripColor(customArrow.getCustomArrow().getItem().getItemMeta().getDisplayName()),
						customArrow.getCustomArrow()));
	}

}