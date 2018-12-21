package Screens;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import Engine.MoreRotaion;
import Engine.mainLoader;
import Entities.Player;
import Entities.Player2;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.EntitySpawner;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.environment.EnvironmentEntityListener;
import de.gurkenlabs.litiengine.environment.tilemap.IMap;
import de.gurkenlabs.litiengine.gui.ImageComponent;
import de.gurkenlabs.litiengine.gui.screens.Screen;

public class mainGameScreen extends Screen{
	public static final String NAME = "Game";
	private static mainGameScreen instance;
	private Player player1;
	private  Player2 player2;
	//private EntitySpawner <Ennemy> ennemySpawner;
	protected mainGameScreen() {
		super("Game");
	}
	public static mainGameScreen getInstance() {
		if(instance==null)
			instance = new mainGameScreen();
		return instance;
	}
	public void prepare () {
		super.prepare();
		
		// Initialize Game
		final double startX = 10;
		final double startY = 100;
		
		Game.load("Resources\\maps\\Level1.litidata");
		Environment myEnv = new Environment("Level1.tmx");
		Game.loadEnvironment(myEnv);
		Game.getCamera().setClampToMap(true);
		Game.getConfiguration().graphics().setAntiAliasing(true);		
	
		
		
		Player player = new Player(new Point2D.Double(startX+121, startY)); //Adds the first player
		Game.getEnvironment().add(player);
		
		Player2 p2 = new Player2(new Point2D.Double(startX+100,startY+100)); //Adds the second player
		Game.getEnvironment().add(p2);
		System.out.println(Game.getEnvironment().getCreatures().size());
		
		
		
		
		
	}
	public void render (final Graphics2D graphics2D) {
		if (Game.getEnvironment() != null) {
			Game.getEnvironment().render(graphics2D);
		}
		
		
		super.render(graphics2D);
		
		
	}
}
