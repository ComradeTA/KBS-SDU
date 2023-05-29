package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Timer;

import static org.junit.jupiter.api.Assertions.*;
class EnemyControlSystemTest {

    private World world;
    private GameData gameData;
    private EnemyPlugin enemyPlugin;
    private EnemyControlSystem enemyControlSystem;

    @BeforeEach
    void setup(){
        world = new World();
        gameData = new GameData();
        enemyPlugin = new EnemyPlugin();
        enemyControlSystem = new EnemyControlSystem();
        gameData.setDisplayWidth(1000);
        gameData.setDisplayHeight(800);
        gameData.setDelta(0.01f);
    }

    @Test
    @DisplayName("Check that enemy has moved")
    void startPlayerPlugin(){
        enemyPlugin.start(gameData, world);

        PositionPart positionPart = null;

        for (Entity enemy : world.getEntities(Enemy.class)){
            positionPart = enemy.getPart(PositionPart.class);
        }

        float initX = positionPart.getX();
        float initY = positionPart.getY();

        // Process for 10 cycles
        for (int i = 0; i <10; i++) {
            // Process each cycle
            enemyControlSystem.process(gameData, world);
        }

        assertNotEquals(initX, positionPart.getX());
        assertNotEquals(initY, positionPart.getY());
    }
}