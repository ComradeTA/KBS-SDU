package dk.sdu.mmmi.cbse.projectilesystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.ShootPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class ProjectileControlSystem implements IEntityProcessingService {

    /**
     * This will process the behavior of all projectiles and all entities with the shootingPart
     * <br/>
     * Pre-conditions:      The parameters must not be null, projectiles and a means to spawn them should exist<br/>
     * Post-conditions:     All projectiles and all shootingParts have been processed
     * @param gameData contains all data about the game
     * @param world contains all entities in the world
     */
    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities()){
            ShootPart shootPart = entity.getPart(ShootPart.class);
            if (shootPart != null && shootPart.isShooting() && shootPart.getRateOfFire() <= shootPart.getTimeSinceLastShot()){
                shootPart.justShot();
                shootPart.setShooting(false);
                Projectile projectile = new Projectile();
                PositionPart positionPart = entity.getPart(PositionPart.class);

                Float projectileX = positionPart.getX() + (float) cos(positionPart.getRadians()) * entity.getHitBoxRadius() + (float) cos(positionPart.getRadians()) * projectile.getHitBoxRadius() + (float) cos(positionPart.getRadians());
                Float projectileY = positionPart.getY() + (float) sin(positionPart.getRadians()) * entity.getHitBoxRadius() + (float) sin(positionPart.getRadians()) * projectile.getHitBoxRadius() + (float) sin(positionPart.getRadians());

                projectile.add(new PositionPart(projectileX, projectileY, positionPart.getRadians()));
                projectile.add(new MovingPart(0,1000000000,shootPart.getVelocity(),0));
                projectile.add(new LifePart(1,1,true));
                world.addEntity(projectile);
            }
        }

        for (Entity projectile : world.getEntities(Projectile.class)) {
            PositionPart positionPart = projectile.getPart(PositionPart.class);
            MovingPart movingPart = projectile.getPart(MovingPart.class);
            LifePart lifePart = projectile.getPart(LifePart.class);
            movingPart.setUp(true);

            movingPart.process(gameData, projectile);
            positionPart.process(gameData, projectile);
            lifePart.process(gameData, projectile);
            if (lifePart.getExpiration() < 0){
                world.removeEntity(projectile);
                continue;
            }
            if (lifePart.isDead()){
                handleDeath(world, projectile);
                continue;
            }
            updateShape(projectile);
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

        int segments = ((Projectile)entity).getSegments();

        for(int i = 0; i < segments; i++) {
            double math1 = Math.cos(2*Math.PI / segments * i);
            double math2 = Math.sin(2*Math.PI / segments * i);

            shapeX[i] = (float) (x + Math.cos(radians) + math1 * entity.getSize());
            shapeY[i] = (float) (y + Math.sin(radians) + math2 * entity.getSize());
        }

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

