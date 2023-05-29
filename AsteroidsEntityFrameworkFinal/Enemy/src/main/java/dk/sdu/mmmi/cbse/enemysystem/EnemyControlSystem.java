package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ShootPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class EnemyControlSystem implements IEntityProcessingService {

    /**
     * This will process the behavior of the enemy
     * <br/>
     * Pre-conditions:      The parameters must not be null, the plugin should have initialized the enemy<br/>
     * Post-conditions:     The enemy ship behavior has been processed
     * @param gameData contains all data about the game
     * @param world contains all entities in the world
     */
    @Override
    public void process(GameData gameData, World world) {
        Random rand = new Random();
        for (Entity enemy : world.getEntities(Enemy.class)) {
            LifePart lifePart = enemy.getPart(LifePart.class);
            if(lifePart.isDead()){
                handleDeath(world, enemy);
            }
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            ShootPart shootPart = enemy.getPart(ShootPart.class);


            movingPart.setLeft(rand.nextBoolean());
            movingPart.setRight(rand.nextBoolean());
            movingPart.setUp(true);
            shootPart.setShooting(true);
            

            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);
            shootPart.process(gameData, enemy);

            updateShape(enemy);
        }
    }

    /**
     * This will update the shape of the entity
     * <br/>
     * Pre-conditions:       The entity is currently being processed <br/>
     * Post-conditions:      The shape of the entity is updated
     * @param entity the entity that needs to update its shape
     */
    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8 * entity.getSize());
        shapey[0] = (float) (y + Math.sin(radians) * 8 * entity.getSize());

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8 * entity.getSize());
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8 * entity.getSize());

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5 * entity.getSize());
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5 * entity.getSize());

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8 * entity.getSize());
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8 * entity.getSize());

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    /**
     * This will handle the death of the entity
     * <br/>
     * Pre-conditions:       The entity is dead<br/>
     * Post-conditions:      The entity is removed from the world
     * @param world contains all entities in the world
     * @param thisEntity the entity which death will be handled
     */
    private void handleDeath(World world, Entity thisEntity){
        world.removeEntity(thisEntity);
    }
}
