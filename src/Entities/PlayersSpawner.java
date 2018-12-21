package Entities;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Random;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.environment.EntitySpawner;
import de.gurkenlabs.litiengine.entities.Spawnpoint;

public class PlayersSpawner extends EntitySpawner<Player>{
	public PlayersSpawner(List<Spawnpoint>spawnpoints, int interval, int amount) {
			super(Game.getEnvironment(),Game.getLoop(),spawnpoints,interval, amount);
			this.setSpawnMode(SpawnMode.RANDOMSPAWNPOINTS);
	}
	private Point2D SpawnLocation() {
		Random r = new Random();
		Spawnpoint spawnPoints = this.getSpawnPoints().get(r.nextInt(this.getSpawnPoints().size()-1));
		Point2D resultLocation = spawnPoints.getLocation();
		return resultLocation;
	}


	@Override
	public Player createNew() {
		// TODO Auto-generated method stub
		return new Player(SpawnLocation());
	}
}
