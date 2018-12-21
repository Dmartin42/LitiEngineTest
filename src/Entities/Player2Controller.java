package Entities;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Arrays;

import Engine.mainLoader;
import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.pathfinding.EntityNavigator;
import de.gurkenlabs.litiengine.pathfinding.IPathFinder;
import de.gurkenlabs.litiengine.pathfinding.astar.AStarPathFinder;
import de.gurkenlabs.litiengine.physics.CollisionType;
import de.gurkenlabs.litiengine.physics.GravityForce;
import de.gurkenlabs.litiengine.physics.MovementController;

public class Player2Controller extends MovementController <Player2> {
	private EntityNavigator nav;
	private Player closetP = null;
	
	public Player2Controller (Player2 player2) {
		super(player2);
		this.nav = new EntityNavigator(player2, new AStarPathFinder(Game.getEnvironment().getMap()));
				
	}
	private Player getPlayerDistance() {
		if (!mainLoader.listPlayers.isEmpty()) {
			closetP = mainLoader.listPlayers.get(0);
			mainLoader.listPlayers.stream().forEach(player -> {
/*				if (Math.abs(this.getEntity().getX()-player.getX()) < Math.abs(this.getEntity().getX()-closetP.getX())  
					&&  Math.abs(this.getEntity().getY()-player.getY()) < Math.abs(this.getEntity().getY()-closetP.getY())) {
					closetP = player;
				}*/
				if( ( this.getEntity().getX() - this.getEntity().getWidth() ) < ( player.getX() - player.getWidth() ) )
					{closetP = player;
						
					}
			});
	}
	return closetP; 
	}
	@Override
	public void update() {
		super.update();
		
		this.getEntity().setTarget(this.getPlayerDistance());
		if(Math.abs(this.getEntity().getX() - this.getEntity().getWidth()) < Math.abs(closetP.getX() - closetP.getWidth())) { //If p1 is on the left
			
			if(closetP.getAnimationController().isPlaying("player_left")) {
				Point2D PlayerPosition = new Point2D.Double(Math.abs(closetP.getX()+this.getEntity().getCollisionBoxWidth()+50),closetP.getY()+16);
				this.getEntity().setMoveDestination(PlayerPosition);
			
				
			}
		}
			
		
		if(closetP.getAnimationController().isPlaying("player_right")) {
			Point2D PlayerPosition = new Point2D.Double(Math.abs(closetP.getX()-this.getEntity().getCollisionBoxWidth()),closetP.getY()+16);
			this.getEntity().setMoveDestination(PlayerPosition);
		}
		
		else 
		{
			Point2D PlayerPosition = new Point2D.Double(closetP.getX()-closetP.getWidth(),closetP.getY()+16);
			this.getEntity().setMoveDestination(PlayerPosition);
		}
		this.nav.navigate(this.getEntity().getMoveDestination());
		Game.getCamera().setFocus(closetP.getCenter());


		this.getEntity().getMovementController().apply(new GravityForce(this.getEntity(), 1, Direction.DOWN));
		
		
	}
	
}



