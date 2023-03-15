package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {
    private int initAmountOfAsteroids = 8;
    private int spawnEdge= 0;

    public AsteroidPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < initAmountOfAsteroids; i++) {
            int x;
            int y;
            Asteroid asteroid = new Asteroid();
            switch (spawnEdge){
                case 0:

                    x = 0;
                    y = 0;

            }

            asteroid.add(new PositionPart()
        }
    }


    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }
}
