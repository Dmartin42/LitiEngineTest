package Engine;

import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import Entities.Player;
import Entities.Player2;
import Screens.mainGameScreen;
import Screens.mainMenu;
import de.gurkenlabs.litiengine.Align;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.Resources;
import de.gurkenlabs.litiengine.Valign;
import de.gurkenlabs.litiengine.entities.EntityListener;
import de.gurkenlabs.litiengine.entities.EntityTransformListener;
import de.gurkenlabs.litiengine.entities.ICollisionEntity;
import de.gurkenlabs.litiengine.entities.IEntityController;
import de.gurkenlabs.litiengine.entities.MessageListener;
import de.gurkenlabs.litiengine.entities.ai.IBehaviorController;
import de.gurkenlabs.litiengine.environment.tilemap.ICustomPropertyProvider;
import de.gurkenlabs.litiengine.graphics.RenderType;
import de.gurkenlabs.litiengine.graphics.animation.IEntityAnimationController;
import de.gurkenlabs.litiengine.gui.screens.Resolution;

public class mainLoader {
	
	public static List <Player> listPlayers = new ArrayList <Player> ();
	public static List <Player2> listPlayers2 = new ArrayList <Player2> ();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Resources.setEncoding(Resources.ENCODING_UTF_8);
		Game.setInfo("gameInfo.xml");
		Game.init();
		Game.getRenderEngine().setBaseRenderScale((float) 2);
		Game.getScreenManager().setResolution(Resolution.custom(1280, 720, "1280*720"));
		Game.getScreenManager().addScreen(new mainMenu());
		Game.getScreenManager().addScreen(mainGameScreen.getInstance());
		Game.getScreenManager().displayScreen(mainMenu.NAME);
		Game.start();
		System.out.println("Game version is: " + Game.getInfo().getVersion());
		System.out.println("Currently active screen: " + Game.getScreenManager().getCurrentScreen().getName());
		
	}

}
