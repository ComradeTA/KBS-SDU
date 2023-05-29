package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidControlSystem implements IEntityProcessingService {

    /**
     * This will process the behavior of all the asteroids in the game
     * <br/>
     * Pre-conditions:      The parameters must not be null, the plugin should have initialized some asteroids<br/>
     * Post-conditions:     All asteroids have been processed
     * @param gameData contains all data about the game
     * @param world contains all entities in the world
     */
    @Override
    public void process(GameData gameData, World world) {

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            LifePart lifePart = asteroid.getPart(LifePart.class);
            if(lifePart.isDead()){
                handleDeath(gameData, world, asteroid);
            }
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);

            movingPart.setUp(true);
            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);

            updateShape(asteroid);
        }
        Random rand = new Random();

        if (rand.nextFloat() < 0.01) {
            int width = gameData.getDisplayWidth();
            int height = gameData.getDisplayHeight();
            float x = 0;
            float y = 0;
            float radians = 0;
            Asteroid asteroid = new Asteroid();
            int spawnEdge = rand.nextInt(4);
            switch (spawnEdge) {
                case 0:
                    // Top
                    x = rand.nextFloat() * width;
                    y = height;
                    radians = rand.nextFloat() * (float) Math.PI * 2;
                    break;
                case 1:
                    // Left
                    x = 0;
                    y = rand.nextFloat() * height;
                    radians = rand.nextFloat() * (float) Math.PI * 2;
                    break;
                case 2:
                    //bottom
                    x = rand.nextFloat() * width;
                    y = 0;
                    radians = rand.nextFloat() * (float) Math.PI * 2;
                    break;
                case 3:
                    // Right
                    x = width;
                    y = rand.nextFloat() * height;
                    radians = rand.nextFloat() * (float) Math.PI * 2;
                    break;
            }
            asteroid.add(new PositionPart(x, y, radians));
            asteroid.add(new MovingPart(0, 1000000, 150, 1));
            asteroid.add(new LifePart(1, 1, false));
            world.addEntity(asteroid);
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
        int segments = ((Asteroid)entity).getSegments();

        Random rand = new Random();
        rand.setSeed(((Asteroid)entity).getSeed());

        for(int i = 0; i < segments; i++) {
            double math1 = Math.cos(2*Math.PI / segments * i);
            double math2 = Math.sin(2*Math.PI / segments * i);

            shapeX[i] = (float) (x + Math.cos(radians) + math1 * entity.getSize() + math1 * (rand.nextInt(((Asteroid) entity).getSegmentFluctuation())- ((Asteroid) entity).getSegmentFluctuationHalved()));
            shapeY[i] = (float) (y + Math.sin(radians) + math2 * entity.getSize() + math2 * (rand.nextInt(((Asteroid) entity).getSegmentFluctuation())- ((Asteroid) entity).getSegmentFluctuationHalved()));
        }

        entity.setShapeX(shapeX);
        entity.setShapeY(shapeY);
    }

    /**
     * This will handle the death of the entity, for the asteroid it will split into two smaller ones until a too small
     * <br/>
     * Pre-conditions:       The entity is dead<br/>
     * Post-conditions:      The entity is removed from the world, depending on the size, two new ones are spawned
     * @param gameData contains all data about the game
     * @param world contains all entities in the world
     * @param thisEntity the entity which death will be handled
     */
    private void handleDeath(GameData gameData, World world, Entity thisEntity){
        if (thisEntity.getSize() > 8) {
            PositionPart positionPart = thisEntity.getPart(PositionPart.class);
            float x = positionPart.getX();
            float y = positionPart.getY();
            float radians = positionPart.getRadians();
            float newSize = thisEntity.getSize()/2;
            float newHitBoxRadius = thisEntity.getHitBoxRadius()/2;
            world.removeEntity(thisEntity);

            Asteroid asteroid1 = new Asteroid();
            asteroid1.setSize(newSize);
            asteroid1.setHitBoxRadius(newHitBoxRadius);
            asteroid1.add(new PositionPart(x - newHitBoxRadius, y - newHitBoxRadius, radians + (float) (Math.PI/4)));
            MovingPart movingPart1 = new MovingPart(0,1000000,150,1);
            movingPart1.process(gameData, asteroid1);
            asteroid1.add(movingPart1);
            asteroid1.add(new LifePart(1,1,false));
            world.addEntity(asteroid1);

            Asteroid asteroid2 = new Asteroid();
            asteroid2.setSize(newSize);
            asteroid2.setHitBoxRadius(newHitBoxRadius);
            asteroid2.add(new PositionPart(x + newHitBoxRadius, y + newHitBoxRadius, radians - (float) (Math.PI/4)));
            MovingPart movingPart2 = new MovingPart(0,1000000,150,1);
            movingPart2.process(gameData, asteroid2);
            asteroid2.add(movingPart2);
            asteroid2.add(new LifePart(1,1,false));
            world.addEntity(asteroid2);
        } else{
            world.removeEntity(thisEntity);
        }
    }
}
