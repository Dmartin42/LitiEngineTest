package Entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D; 
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.ImageIcon;

import Engine.MoreRotaion;
import Engine.mainLoader;
import Screens.mainMenu;
import de.gurkenlabs.litiengine.Align;
import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.Valign;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityListener;
import de.gurkenlabs.litiengine.entities.EntityTransformListener;
import de.gurkenlabs.litiengine.entities.ICollisionEntity;
import de.gurkenlabs.litiengine.entities.IEntityController;
import de.gurkenlabs.litiengine.entities.IMobileEntity;
import de.gurkenlabs.litiengine.entities.MessageListener;
import de.gurkenlabs.litiengine.entities.ai.IBehaviorController;
import de.gurkenlabs.litiengine.environment.tilemap.ICustomPropertyProvider;
import de.gurkenlabs.litiengine.graphics.RenderType;
import de.gurkenlabs.litiengine.graphics.animation.EntityAnimationController;
import de.gurkenlabs.litiengine.graphics.animation.IEntityAnimationController;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.Force;
import de.gurkenlabs.litiengine.physics.GravityForce;
import de.gurkenlabs.litiengine.physics.MovementController;

public class Player2 extends Creature implements ICollisionEntity{

private MoreRotaion rotation;
private Creature Player1; 


	
	
	
	public Player2 (Point2D position) {
		this.setName("player2");
		this.setTeam(1);
		this.setMapId(2);
		Player1 = Game.getEnvironment().getCreature("player1");
		this.setLocation(Player1.getX()-Player1.getWidth(),Player1.getY());
		this.rotation = MoreRotaion.NONE;
		mainLoader.listPlayers2.add(this);
		getVelocity().setBaseValue((float) 50);
		this.initialize();
	}
	
	
	
	public void initialize () {
		Game.getPhysicsEngine().add(this);
		this.setCollision(true);
		this.setCollisionBoxHeight(32.0f);
		this.setCollisionBoxWidth(32.0f);
		KeyboardEntityController <Player2> keyboardController = new KeyboardEntityController<Player2>(this, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
		this.addController(keyboardController);
		this.setController(MovementController.class,new Player2Controller(this));
		this.setController(EntityAnimationController.class, new Player2AnimationController(this));	
		
		this.initializeInputs();
	}
	
	
	
	private void initializeInputs () {
		
		Input.keyboard().onKeyPressed(KeyEvent.VK_W, (key) -> this.changeDirection(Direction.UP));
		Input.keyboard().onKeyPressed(KeyEvent.VK_S, (key) -> this.changeDirection(Direction.DOWN));
		Input.keyboard().onKeyPressed(KeyEvent.VK_A, (key) -> this.changeDirection(Direction.LEFT));
		Input.keyboard().onKeyPressed(KeyEvent.VK_D, (key) -> this.changeDirection(Direction.RIGHT));
		
//		Input.keyboard().onKeyTyped(KeyEvent.VK_SPACE, (key) -> {
//			this.fire(this);
//		});
		
		
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
	
	
	
	public void changeDirection (Direction direction) {
	
		switch (direction) {
			case UP:
				if (Input.keyboard().isPressed(KeyEvent.VK_A)) {
					this.getAnimationController().playAnimation("playertwo_left");
					this.rotation = MoreRotaion.ROTATE_270;
				}
				else if (Input.keyboard().isPressed(KeyEvent.VK_D)) {
					this.getAnimationController().playAnimation("playertwo_right");
					this.rotation = MoreRotaion.ROTATE_45;
				}
				else {
					this.getAnimationController().playAnimation("playertwo_back");
					this.rotation = MoreRotaion.NONE;
				}
				
				break;
			case DOWN:
				if (Input.keyboard().isPressed(KeyEvent.VK_A)) {
					this.getAnimationController().playAnimation("playertwo_left");
					this.rotation = MoreRotaion.ROTATE_225;
				}
				else if (Input.keyboard().isPressed(KeyEvent.VK_D)) {
					this.getAnimationController().playAnimation("playertwo_right");
					this.rotation = MoreRotaion.ROTATE_135;
				}
				else {
					
					this.getAnimationController().playAnimation("playertwo_front");
					this.rotation = MoreRotaion.ROTATE_180;
				}

				break;
			case LEFT:
				if (Input.keyboard().isPressed(KeyEvent.VK_W)) {
					this.getAnimationController().playAnimation("playertwo_left");
					this.rotation = MoreRotaion.ROTATE_315;
				}
				else if (Input.keyboard().isPressed(KeyEvent.VK_S)) {
					this.getAnimationController().playAnimation("playertwo_back");
					this.rotation = MoreRotaion.ROTATE_225;
				}
				else {
					this.getAnimationController().playAnimation("playertwo_left");
					this.rotation = MoreRotaion.ROTATE_270;
				}
				break;
			case RIGHT:
				if (Input.keyboard().isPressed(KeyEvent.VK_W)) {
					this.getAnimationController().playAnimation("playertwo_front");
					this.rotation = MoreRotaion.ROTATE_45;
				}
				else {
					this.rotation = MoreRotaion.ROTATE_90;
					this.getAnimationController().playAnimation("playertwo_right");
				}
				break;
			default:
				break;
		}
		
		this.getAnimationController().update();
		
	
	}

	
	

	
	
	
	public boolean canBeKilledBy (final IMobileEntity otherEntity) {
		if (otherEntity instanceof Player2) {
			return false;
		}
		return true;
	}
	
	
	
	public MoreRotaion getRotation () {
		
		return this.rotation;
	}

}

