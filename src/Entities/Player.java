package Entities;

import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.security.Timestamp;
import java.util.List;

import javax.swing.ImageIcon;

import Engine.MoreRotaion;
import Engine.mainLoader;
import Screens.mainMenu;
import de.gurkenlabs.litiengine.Align;
import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.ICollisionEntity;
import de.gurkenlabs.litiengine.entities.IMobileEntity;
import de.gurkenlabs.litiengine.graphics.animation.EntityAnimationController;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.CollisionType;
import de.gurkenlabs.litiengine.physics.GravityForce;

public class Player extends Creature {
	private MoreRotaion rotation;
	public static Point2D p1_loc;
	private Player2 p2;
	public GravityForce gravity = new GravityForce(this, 150, Direction.DOWN);
	PlayersSpawner spawn;
	
	
	public Player (Point2D position) {
		
		this.setName("player1");
		this.setTeam(1);
		this.setMapId(1);
		//this.setLocation(new Point2D.Double(110, 110));
		this.setSize(30,30);
		this.rotation = MoreRotaion.NONE;
		mainLoader.listPlayers.add(this);
		getVelocity().setBaseValue((float) 100);
		this.initialize();
	}
	
	
	
	public void initialize () {
		KeyboardEntityController <Player> keyboardController = new KeyboardEntityController<Player>(this, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
		this.addController(keyboardController);
		this.setController(EntityAnimationController.class, new PlayerAnimationController(this));
		this.getMovementController().apply(gravity);
		this.initializeInputs();
	}
	
	
	
	private void initializeInputs () {
		Input.keyboard().onKeyPressed(KeyEvent.VK_UP, (key) -> this.changeDirection(Direction.UP));
		Input.keyboard().onKeyPressed(KeyEvent.VK_DOWN, (key) -> this.changeDirection(Direction.DOWN));
		Input.keyboard().onKeyPressed(KeyEvent.VK_LEFT, (key) -> this.changeDirection(Direction.LEFT));
		Input.keyboard().onKeyPressed(KeyEvent.VK_RIGHT, (key) -> this.changeDirection(Direction.RIGHT));
		Input.keyboard().onKeyPressed(KeyEvent.VK_SPACE, (key) -> this.jump(this));
		
		Input.keyboard().onKeyTyped(KeyEvent.VK_ESCAPE, (key) -> {
			System.exit(0);
			
			Game.getScreenManager().getRenderComponent().setCursor(new ImageIcon("resources/cursor_white_64.png").getImage(), 0, 0);
			Game.getScreenManager().displayScreen(mainMenu.NAME);
			try {
				//TODO - Finalizes this instance of InGameScreen
				//Game.getScreenManager().getCurrentScreen()..finalize();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		});
	}
	
	
	
	private void changeDirection (Direction direction) {
		switch (direction) {
			case UP:
				if (Input.keyboard().isPressed(KeyEvent.VK_LEFT)) {
					this.getAnimationController().playAnimation("player_left");
					this.rotation = MoreRotaion.ROTATE_270;
				}
				else if (Input.keyboard().isPressed(KeyEvent.VK_RIGHT)) {
					this.getAnimationController().playAnimation("player_right");
					this.rotation = MoreRotaion.ROTATE_45;
				}
				else {
					this.getAnimationController().playAnimation("player_front");
					this.rotation = MoreRotaion.NONE;
				}
				break;
			case DOWN:
				if (Input.keyboard().isPressed(KeyEvent.VK_LEFT)) {
					this.getAnimationController().playAnimation("player_back");
					this.rotation = MoreRotaion.ROTATE_225;
				}
				else if (Input.keyboard().isPressed(KeyEvent.VK_RIGHT)) {
					this.getAnimationController().playAnimation("player_right");
					this.rotation = MoreRotaion.ROTATE_135;
				}
				else {
					this.getAnimationController().playAnimation("player_back");
					this.rotation = MoreRotaion.ROTATE_180;
				}
				break;
			case LEFT:
				if (Input.keyboard().isPressed(KeyEvent.VK_UP)) {
					this.getAnimationController().playAnimation("player_left");
					this.rotation = MoreRotaion.ROTATE_315;
				}
				else if (Input.keyboard().isPressed(KeyEvent.VK_DOWN)) {
					this.getAnimationController().playAnimation("player_back");
					this.rotation = MoreRotaion.ROTATE_225;
				}
				else {
					this.getAnimationController().playAnimation("player_left");
					this.rotation = MoreRotaion.ROTATE_270;
				}
				break;
			case RIGHT:
				if (Input.keyboard().isPressed(KeyEvent.VK_UP)) {
					this.getAnimationController().playAnimation("player_front");
					this.rotation = MoreRotaion.ROTATE_45;
				}
				else if (Input.keyboard().isPressed(KeyEvent.VK_DOWN)) {
					this.getAnimationController().playAnimation("player_right");
					this.rotation = MoreRotaion.ROTATE_135;
				}
				else {
					this.getAnimationController().playAnimation("player_right");
					
					this.rotation = MoreRotaion.ROTATE_90;
				}
				
				break;
			default:
				break;
		}
		this.getAnimationController().update();
		
		
	}
	
	
	
	




	private void jump(Creature player) {
		if(Game.getPhysicsEngine().collides(player.getBoundingBox(), CollisionType.STATIC)
				|| Game.getPhysicsEngine().getCollisionEntities().stream().anyMatch(e -> !e.equals(player) && e.getCollisionBox().intersects(player.getBoundingBox()))){
				  Point2D jumpHeight = new Point2D.Double(player.getX(), player.getY()-10);
				  player.setMoveDestination(jumpHeight);
				  Game.getPhysicsEngine().move(player, player.getMoveDestination());
				}
				
				

		
		
		
		
	}
	
	
	public boolean canBeKilledBy (final IMobileEntity otherEntity) {
		if (otherEntity instanceof Player) {
			return false;
		}
		return true;
	}

	public MoreRotaion getRotation () {
		return this.rotation;
	}

}
