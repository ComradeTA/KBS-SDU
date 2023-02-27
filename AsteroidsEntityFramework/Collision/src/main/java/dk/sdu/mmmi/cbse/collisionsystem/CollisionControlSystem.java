package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.Random;

/**
 *
 * @author jcs
 */
public class CollisionControlSystem implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        Random rand = new Random();
        Object[] array = world.getEntities().toArray();

        for (int i = 0; i < array.length; i++) {
            Entity entity1 = (Entity) array[i];
            if (entity1.getPart(PositionPart.class) == null) {
                continue;
            }
            for (int j = i + 1; j < array.length; j++) {
                Entity entity2 = (Entity) array[j];
                if (entity2.getPart(PositionPart.class) == null) {
                    continue;
                }

                PositionPart positionPart1 = entity1.getPart(PositionPart.class);
                PositionPart positionPart2 = entity2.getPart(PositionPart.class);

                if(colliding(positionPart1.getX(), positionPart1.getY(), entity1.getHitBoxRadius(), positionPart2.getX(), positionPart2.getY(), entity2.getHitBoxRadius())){
                    System.out.println(entity1 + " collided with " + entity2);
                }
            }
        }
    }

    private boolean colliding(float x1, float y1, float hitBox1, float x2, float y2, float hitBox2){
        if ((Math.abs(x2-x1)+Math.abs(y2-y1)) > (hitBox1+hitBox2)){
            return false;
        } else {
            return true;
        }
    }
}
