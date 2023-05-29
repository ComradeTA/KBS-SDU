package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ShootPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    public PlayerPlugin() {
    }


    /**
     * Initialize player plugin so that are player ship has been spawned
     * <br/>
     * Pre-conditions:       The parameters are not null, start has not been called <br/>
     * Post-conditions:      A player ship has been spawned
     * @param gameData contains all data about the game
     * @param world contains all entities in the world
     */
    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    /**
     * Create a player ship entity
     * <br/>
     * Pre-conditions:       The parameters are not null, start is being called <br/>
     * Post-conditions:      A fully capable player ship is returned
     * @param gameData contains all data about the game
     * @return a player ship entity
     */
    private Entity createPlayerShip(GameData gameData) {

        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2f;
        float y = gameData.getDisplayHeight() / 2f;
        float radians = 3.1415f / 2;
        
        Entity playerShip = new Player();
        playerShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        playerShip.add(new PositionPart(x, y, radians));
        playerShip.add(new ShootPart(1, (float) 0.5,400));
        playerShip.add(new LifePart(1,1,false));
        playerShip.setSize(1);
        playerShip.setHitBoxRadius(1);
        return playerShip;
    }

    /**
     * Stop the player plugin so that the player is removed
     * <br/>
     * Pre-conditions:       The parameters are not null, stop has not been called <br/>
     * Post-conditions:      The player is removed
     * @param gameData contains all data about the game
     * @param world contains all entities in the world
     */
    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
