package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {
    private int initAmountOfAsteroids = 8;
    private int spawnEdge = 0;

    public AsteroidPlugin() {
    }

    /**
     * Initialize asteroid plugin so that an initial amount of asteroids spawn along the border.
     * <br/>
     * Pre-conditions:       The parameters are not null, start has not been called <br/>
     * Post-conditions:      The initial amount of asteroids has been spawned
     * @param gameData contains all data about the game
     * @param world contains all entities in the world
     */
    @Override
    public void start(GameData gameData, World world) {
        Random rand = new Random();
        int width = gameData.getDisplayWidth();
        int height = gameData.getDisplayHeight();
        for (int i = 0; i < initAmountOfAsteroids; i++) {
            float x = 0;
            float y = 0;
            float radians = 0;
            Asteroid asteroid = new Asteroid();
            switch (spawnEdge){
                case 0:
                    // Top
                    x = rand.nextFloat() * width;
                    y = height;
                    radians = rand.nextFloat() * (float) Math.PI * 2;
                    spawnEdge = 1;
                    break;
                case 1:
                    // Left ?
                    x = 0;
                    y = rand.nextFloat() * height;
                    radians = rand.nextFloat() * (float) Math.PI * 2;
                    spawnEdge = 2;
                    break;
                case 2:
                    //bottom ?
                    x = rand.nextFloat() * width;
                    y = 0;
                    radians = rand.nextFloat() * (float) Math.PI * 2;
                    spawnEdge = 3;
                    break;
                case 3:
                    // Right ?
                    x = width;
                    y = rand.nextFloat() * height;
                    radians = rand.nextFloat() * (float) Math.PI * 2;
                    spawnEdge = 0;
                    break;
            }
            asteroid.add(new PositionPart(x, y, radians));
            asteroid.add(new MovingPart(0,1000000,150,1));
            asteroid.add(new LifePart(1,1,false));
            world.addEntity(asteroid);
        }
    }


    /**
     * Stop the asteroid plugin so that all asteroids are removed
     * <br/>
     * Pre-conditions:       The parameters are not null, stop has not been called <br/>
     * Post-conditions:      All asteroids have been removed
     * @param gameData contains all data about the game
     * @param world contains all entities in the world
     */
    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }
}
