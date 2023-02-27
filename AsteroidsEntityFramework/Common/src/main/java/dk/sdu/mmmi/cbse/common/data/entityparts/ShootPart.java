package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;



public class ShootPart implements EntityPart{

	private int damage;
	private float rateOfFire;
	private float velocity;
	private float timeSinceLastShot;
	private boolean isShooting = false;

	public ShootPart(int damage, float rateOfFire, float velocity) {
		this.damage = damage;
		this.rateOfFire = rateOfFire;
		this.timeSinceLastShot = rateOfFire;
		this.velocity = velocity;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean shooting) {
		isShooting = shooting;
	}

	public void justShot() {
		timeSinceLastShot = 0;
	}

	public float getTimeSinceLastShot() {
		return timeSinceLastShot;
	}

	public int getDamage() {
		return damage;
	}

	public float getRateOfFire() {
		return rateOfFire;
	}

	public float getVelocity() {
		return velocity;
	}

	@Override
	public void process(GameData gameData, Entity entity) {
		timeSinceLastShot += gameData.getDelta();
	}
}
