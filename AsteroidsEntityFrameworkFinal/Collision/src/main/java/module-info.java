import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Collision {
    requires Common;
    requires com.badlogic.gdx;

    provides IGamePluginService with dk.sdu.mmmi.cbse.collisionsystem.CollisionPlugin;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisionsystem.CollisionControlSystem;
}