package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ShootPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    public EnemyPlugin() {
    }

    /**
     * Initialize enemy plugin so that are enemy ship has been spawned
     * <br/>
     * Pre-conditions:       The parameters are not null, start has not been called <br/>
     * Post-conditions:      A enemy ship has been spawned
     * @param gameData contains all data about the game
     * @param world contains all entities in the world
     */
    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    /**
     * Create an enemy ship entity
     * <br/>
     * Pre-conditions:       The parameters are not null, start is being called <br/>
     * Post-conditions:      A fully capable enemy ship is returned
     * @param gameData contains all data about the game
     * @return an enemy ship entity
     */
    private Entity createEnemyShip(GameData gameData) {

        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2 + 120;
        float radians = 3.1415f / 2;

        Entity enemyShip = new Enemy();
        enemyShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemyShip.add(new PositionPart(x, y, radians));
        enemyShip.add(new ShootPart(1, 5, 400));
        enemyShip.add(new LifePart(1,0,false));

        return enemyShip;
    }

    /**
     * Stop the enemy plugin so that the enemy is removed
     * <br/>
     * Pre-conditions:       The parameters are not null, stop has not been called <br/>
     * Post-conditions:      The enemy is removed
     * @param gameData contains all data about the game
     * @param world contains all entities in the world
     */
    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }

}
