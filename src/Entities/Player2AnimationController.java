package Entities;

import java.util.ArrayList;

import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.EntityAnimationController;

public class Player2AnimationController extends EntityAnimationController<Player2> {
	
	public Player2AnimationController(Player2 player) {
		super(player, new Animation(Spritesheet.find("playertwo_front"), true), getPlayerAnimations());
		
	}

	private static Animation[] getPlayerAnimations () {
		ArrayList <Animation> animations2 = new ArrayList <Animation> ();
		animations2.add(new Animation(Spritesheet.find("playertwo_front"), true));
		animations2.add(new Animation(Spritesheet.find("playertwo_back"), true));
		animations2.add(new Animation(Spritesheet.find("playertwo_left"), true));
		animations2.add(new Animation(Spritesheet.find("playertwo_right"), true));
		return animations2.toArray(new Animation[animations2.size()]);	
	}
	
	
	
}



