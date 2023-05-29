package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {
    /**
     * This method will initialize a module through start if needed
     *<br/>
     * Pre-conditions:      both parameters must not be null,
     *                      the method must not have already been called<br/>
     * Post-conditions:     The world has been updated with entities through this method
     *
     * @param gameData contains all data about the game
     * @param world contains all entities in the world
     */
    void start(GameData gameData, World world);

    /**
     * This method will stop a module if needed
     * Pre-conditions:      both parameters must not be null,
     *                      the method must not have already been called
     * Post-conditions:     The plugin is stopped and relevant data is removed from the world
     * @param gameData contains all data about the game
     * @param world contains all entities in the world
     */
    void stop(GameData gameData, World world);
}
