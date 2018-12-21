package Entities;

import java.util.ArrayList;

import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.EntityAnimationController;

public class PlayerAnimationController extends EntityAnimationController<Player> {
	
	public PlayerAnimationController(Player player) {
		super(player, new Animation(Spritesheet.find("player_front"), true), getPlayerAnimations());
	}

	private static Animation[] getPlayerAnimations () {
		ArrayList <Animation> animations = new ArrayList <Animation> ();
		animations.add(new Animation(Spritesheet.find("player_front"), true));
		animations.add(new Animation(Spritesheet.find("player_back"), true));
		animations.add(new Animation(Spritesheet.find("player_left"), true));
		animations.add(new Animation(Spritesheet.find("player_right"), true));
		return animations.toArray(new Animation[animations.size()]);
	}
	
}



