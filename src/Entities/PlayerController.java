package Entities;

import java.awt.geom.Point2D;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.physics.GravityForce;
import de.gurkenlabs.litiengine.physics.MovementController;


public class PlayerController extends MovementController <Player> {
	public GravityForce gravity = new GravityForce(this.getEntity(),1, Direction.DOWN);
	public PlayerController (Player player) {
		super(player);
		
	}


		
	
	
}
