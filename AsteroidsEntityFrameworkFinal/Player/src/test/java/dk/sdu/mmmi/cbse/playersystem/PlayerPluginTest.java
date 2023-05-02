package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PlayerPluginTest {
    private World world;
    private GameData gameData;
    private PlayerPlugin playerPlugin;

    @BeforeEach
    void setup(){
        world = new World();
        gameData = new GameData();
        playerPlugin = new PlayerPlugin();
    }

    @Test
    @DisplayName("Check that player is created when we start this plugin")
    void startPlayerPlugin(){
        assertTrue(world.getEntities().stream().collect(Collectors.toList()).toArray().length == 0);
        playerPlugin.start(gameData, world);
        assertTrue(world.getEntities().stream().collect(Collectors.toList()).toArray().length == 1);
    }
}