package dk.sdu.mmmi.cbse.projectilesystem;

import com.badlogic.gdx.graphics.Color;
import dk.sdu.mmmi.cbse.common.data.Entity;


/**
 *
 * @author corfixen
 */
public class Projectile extends Entity {
	public int getSegments() {
		return segments;
	}

	private int segments;

	public Projectile(){
		this.segments = 15;
		this.setShapeX(new float[this.segments]);
		this.setShapeY(new float[this.segments]);
		super.setColor(Color.BLUE);
		super.setHitBoxRadius(3);
		super.setSize(3);
	}
}


