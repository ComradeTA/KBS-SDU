package dk.sdu.mmmi.cbse.projectilesystem;

import com.badlogic.gdx.graphics.Color;
import dk.sdu.mmmi.cbse.common.data.Entity;


/**
 *
 * @author corfixen
 */
public class Projectile extends Entity {
	public Projectile(){
		super.setColor(Color.BLUE);
		super.setSize((float)0.5);
	}
}


