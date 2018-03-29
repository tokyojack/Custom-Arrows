package me.tokyojack.spigot.customarrows.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.CommandSender;

import me.tokyojack.spigot.customarrows.utils.kommand.Kommand;

public class CustomArrowsCommand extends Kommand {

	public CustomArrowsCommand() {
		super("customarrows", "Custom arrows command", new ArrayList<String>(Arrays.asList("customarrow","ca")));
	}

	@Override
	public boolean execute(CommandSender commandSender, String label, String[] args) {
		return true;
	}

}
