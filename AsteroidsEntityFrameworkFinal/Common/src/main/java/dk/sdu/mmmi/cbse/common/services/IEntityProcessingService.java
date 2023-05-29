package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {

    /**
     * For any service that process entities during each cycle, it will be done through this method.
     * <br/>
     * Pre-conditions:      both parameters must not be null, the process is called during each cycle <br/>
     * Post-conditions:     The game is updated in the process method for one cycle, in regard to delta time if necessary
     *
     * @param gameData contains all data about the game
     * @param world contains all entities in the world
     */
    void process(GameData gameData, World world);

}
