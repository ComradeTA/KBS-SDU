package dk.sdu.mmmi.cbse.projectilesystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import dk.sdu.mmmi.cbse.common.services.IGamePluginService;


public class ProjectilePlugin implements IGamePluginService {

    public ProjectilePlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
    }


    /**
     * Stop the projectile plugin so that all projectiles are removed
     * <br/>
     * Pre-conditions:       The parameters are not null, stop has not been called <br/>
     * Post-conditions:      All asteroids have been removed
     * @param gameData contains all data about the game
     * @param world contains all entities in the world
     */
    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity projectile : world.getEntities(Projectile.class)) {
            world.removeEntity(projectile);
        }
    }
}
