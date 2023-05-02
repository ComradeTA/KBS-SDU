import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Projectile {
    requires Common;
    requires com.badlogic.gdx;
    provides IGamePluginService with dk.sdu.mmmi.cbse.projectilesystem.ProjectilePlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.projectilesystem.ProjectileControlSystem;
}