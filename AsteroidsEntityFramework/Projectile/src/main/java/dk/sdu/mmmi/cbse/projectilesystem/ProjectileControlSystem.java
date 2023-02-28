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

/**
 *
 * @author jcs
 */
public class ProjectileControlSystem implements IEntityProcessingService {

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
            updateShape(projectile);
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
