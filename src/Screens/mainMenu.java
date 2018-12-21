package Screens;

import java.awt.Image;

import javax.swing.ImageIcon;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.ImageComponent;
import de.gurkenlabs.litiengine.gui.screens.Screen;

public class mainMenu extends Screen{
	public static final String NAME = "Menu";
	public mainMenu() {
		super("Menu");
	}
	public void prepare() {
		super.prepare();
	}
	protected void initializeComponents () {
		Image imageCursorMain = new ImageIcon("resources/cursor_white_164.png").getImage();
		Game.getScreenManager().getRenderComponent().setCursor(imageCursorMain,0, 0);
		final double screenCenterX = Game.getScreenManager().getResolution().getWidth() / 2.0;
		final double screenCenterY = Game.getScreenManager().getResolution().getHeight() / 2.0;
		ImageComponent start = new ImageComponent(screenCenterX-150, screenCenterY-50, 300, 100, null, "Start Game", null);
		ImageComponent exit = new ImageComponent(start.getX(), start.getY()+start.getHeight(), 300, 100, null, "Exit Game", null);
		
		start.onClicked(e -> {
			Image imageCursor = new ImageIcon("resources/cursor_invisible_1.png").getImage();
			Game.getScreenManager().getRenderComponent().setCursor(imageCursor, 0, 0);
			Game.getScreenManager().displayScreen(mainGameScreen.NAME);
		});
		
		exit.onClicked(e -> System.exit(0));
		
		this.getComponents().add(start);
		this.getComponents().add(exit);
	}

	

}
