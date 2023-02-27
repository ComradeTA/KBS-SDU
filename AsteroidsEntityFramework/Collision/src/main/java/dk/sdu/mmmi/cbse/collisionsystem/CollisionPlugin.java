package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class CollisionPlugin implements IGamePluginService {

    private Entity enemy;

    public CollisionPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        enemy = createCollisionSystem(gameData);
        world.addEntity(enemy);
    }

    private Entity createCollisionSystem(GameData gameData) {
        Entity CollisionModule = new Collision();

        return CollisionModule;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }

}
