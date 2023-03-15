package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

/**
 *
 * @author jcs
 */
public class AsteroidControlSystem implements IEntityProcessingService {
    private final Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);

            movingPart.process(gameData, player);
            positionPart.process(gameData, player);

            updateShape(player);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapeX = entity.getShapeX();
        float[] shapeY = entity.getShapeY();

        PositionPart positionPart = entity.getPart(PositionPart.class);

        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        int segments = ((Asteroid)entity).getSegments();

        this.random.setSeed(((Asteroid)entity).getSeed());

        for(int i = 0; i < segments; i++) {
            double math1 = Math.cos(2*Math.PI / segments * i);
            double math2 = Math.sin(2*Math.PI / segments * i);

            shapeX[i] = (float) (x + Math.cos(radians) + math1 * entity.getSize() + math1 * (random.nextInt(((Asteroid) entity).getSegmentFluctuation())- ((Asteroid) entity).getSegmentFluctuationHalved()));
            shapeY[i] = (float) (y + Math.sin(radians) + math2 * entity.getSize() + math2 * (random.nextInt(((Asteroid) entity).getSegmentFluctuation())- ((Asteroid) entity).getSegmentFluctuationHalved()));
        }

        entity.setShapeX(shapeX);
        entity.setShapeY(shapeY);
    }
}
