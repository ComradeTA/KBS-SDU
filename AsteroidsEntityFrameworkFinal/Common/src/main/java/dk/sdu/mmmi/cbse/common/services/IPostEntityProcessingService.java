package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * The interface is for services that process entities before each cycle
 * <br/>
 * Pre-conditions:      both parameters must not be null, the process is called before each cycle <br/>
 * Post-conditions:     The game is updated in the process for one cycle, in regard to delta time if necessary
 */
public interface IPostEntityProcessingService  {
        /**
         * For any service that process entities after each cycle, it will be done through this method.
         *
         * Pre-conditions:      both parameters must not be null, the process is called after each cycle
         * Post-conditions:     The game is updated in the process method for one cycle, in regard to delta time if necessary
         *
         * @param gameData contains all data about the game
         * @param world contains all entities in the world
         */
        void process(GameData gameData, World world);
}
