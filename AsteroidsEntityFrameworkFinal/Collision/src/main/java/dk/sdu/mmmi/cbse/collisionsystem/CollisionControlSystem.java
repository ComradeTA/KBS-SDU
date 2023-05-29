package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.Random;

public class CollisionControlSystem implements IPostEntityProcessingService {

    /**
     * This will process the behavior of collision for all entities which have collision enabled
     * <br/>
     * Pre-conditions:      The parameters must not be null, collision enabled entities should exists<br/>
     * Post-conditions:     Any collision enabled entities that collide take damage
     * @param gameData contains all data about the game
     * @param world contains all entities in the world
     */
    @Override
    public void process(GameData gameData, World world) {
        Object[] entities = world.getEntities().toArray();

        for (int i = 0; i < entities.length; i++) {
            Entity entity1 = (Entity) entities[i];
            if (entity1.getPart(PositionPart.class) == null || entity1.getPart(LifePart.class) == null) {
                continue;
            }
            for (int j = i + 1; j < entities.length; j++) {
                Entity entity2 = (Entity) entities[j];
                if (entity2.getPart(PositionPart.class) == null || entity2.getPart(LifePart.class) == null) {
                    continue;
                }
                PositionPart positionPart1 = entity1.getPart(PositionPart.class);
                PositionPart positionPart2 = entity2.getPart(PositionPart.class);

                if(colliding(positionPart1.getX(), positionPart1.getY(), entity1.getHitBoxRadius(), positionPart2.getX(), positionPart2.getY(), entity2.getHitBoxRadius())){
                    LifePart lifePart1 = entity1.getPart(LifePart.class);
                    LifePart lifePart2 = entity2.getPart(LifePart.class);
                    lifePart1.takeDamage(lifePart2.getCollisionDamage());
                    lifePart2.takeDamage(lifePart1.getCollisionDamage());

                    //Debug collision
                    //System.out.println(entity1 + " collided with " + entity2);
                    //System.out.println(lifePart1.getLife());
                }
            }
        }
    }

    /**
     * Check for collision between to points with their hitboxes
     * <br/>
     * Pre-conditions:      No parameters are null<br/>
     * Post-conditions:     Collision is returned as a bool
     * @param x1 X coordinate of point 1
     * @param y1 Y coordinate of point 1
     * @param hitBox1 hitbox radius of point 1
     * @param x2 X coordinate of point 2
     * @param y2 Y coordinate of point 2
     * @param hitBox2 hitbox radius of point 2
     * @return Returns state of collision
     */
    private boolean colliding(float x1, float y1, float hitBox1, float x2, float y2, float hitBox2){
        if ((Math.abs(x2-x1)+Math.abs(y2-y1)) > (hitBox1+hitBox2)){
            return false;
        } else {
            return true;
        }
    }
}
