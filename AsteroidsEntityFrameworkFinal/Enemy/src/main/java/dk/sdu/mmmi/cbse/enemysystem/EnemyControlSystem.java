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

/**
 *
 * @author jcs
 */
public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        Random rand = new Random();
        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            ShootPart shootPart = enemy.getPart(ShootPart.class);


            movingPart.setLeft(rand.nextBoolean());
            movingPart.setRight(rand.nextBoolean());
            movingPart.setUp(false);
            shootPart.setShooting(false);
            

            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);
            shootPart.process(gameData, enemy);

            updateShape(enemy);
        }
    }

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

}
