package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ShootPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import static dk.sdu.mmmi.cbse.common.data.GameKeys.*;

public class PlayerControlSystem implements IEntityProcessingService {

    /**
     * This will process the behavior of the player
     * <br/>
     * Pre-conditions:      The parameters must not be null, the plugin should have initialized the player<br/>
     * Post-conditions:     The player ship behavior has been processed
     * @param gameData contains all data about the game
     * @param world contains all entities in the world
     */
    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            LifePart lifePart = player.getPart(LifePart.class);
            if(lifePart.isDead()){
                handleDeath(world, player);
            }
            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);
            ShootPart shootPart = player.getPart(ShootPart.class);

            movingPart.setLeft(gameData.getKeys().isDown(LEFT));
            movingPart.setRight(gameData.getKeys().isDown(RIGHT));
            movingPart.setUp(gameData.getKeys().isDown(UP));
            shootPart.setShooting(gameData.getKeys().isDown(SPACE));
            
            
            movingPart.process(gameData, player);
            positionPart.process(gameData, player);
            shootPart.process(gameData, player);

            updateShape(player);
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
        float[] shapeX = entity.getShapeX();
        float[] shapeY = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapeX[0] = (float) (x + Math.cos(radians) * 8 * entity.getSize());
        shapeY[0] = (float) (y + Math.sin(radians) * 8 * entity.getSize());

        shapeX[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8 * entity.getSize());
        shapeY[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8 * entity.getSize());

        shapeX[2] = (float) (x + Math.cos(radians + 3.1415f) * 5 * entity.getSize());
        shapeY[2] = (float) (y + Math.sin(radians + 3.1415f) * 5 * entity.getSize());

        shapeX[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8 * entity.getSize());
        shapeY[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8 * entity.getSize());

        entity.setShapeX(shapeX);
        entity.setShapeY(shapeY);
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
