package me.tokyojack.spigot.customarrows;

import lombok.Getter;
import me.tokyojack.spigot.customarrows.arrows.Confuse;
import me.tokyojack.spigot.customarrows.arrows.Poison;
import me.tokyojack.spigot.customarrows.arrows.Slow;
import me.tokyojack.spigot.customarrows.arrows.Whither;
import me.tokyojack.spigot.customarrows.utils.CustomArrow;

@Getter
public enum CustomArrows {
	CONFUSE(new Confuse()),
	POISON(new Poison()),
	SLOW(new Slow()),
	WHITHER(new Whither());


	private CustomArrow customArrow;

	private CustomArrows(CustomArrow customArrow) {
		this.customArrow = customArrow;
	}

}
