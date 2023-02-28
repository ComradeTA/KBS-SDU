/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 *
 * @author Someone
 */
public class LifePart implements EntityPart {

    private int life;
    private boolean isHit = false;
    private float expiration;
    private boolean canExpire;
    private boolean bCollidable = true;
    private int collisionDamage = 1;

    public LifePart(int life, float expiration, boolean canExpire) {
        this.life = life;
        this.expiration = expiration;
        this.canExpire = canExpire;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void takeDamage(int damage){
        life -= damage;
    }

    public boolean isIsHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public float getExpiration() {
        return expiration;
    }

    public void setExpiration(float expiration) {
        this.expiration = expiration;
    }  
    
    public void reduceExpiration(float delta){
        this.expiration -= delta;
    }

    public boolean isbCollidable() {
        return bCollidable;
    }

    public void setbCollidable(boolean bCollidable) {
        this.bCollidable = bCollidable;
    }

    public int getCollisionDamage() {
        return collisionDamage;
    }

    public void setCollisionDamage(int collisionDamage) {
        this.collisionDamage = collisionDamage;
    }
    
    @Override
    public void process(GameData gameData, Entity entity) {
        if (canExpire){
            reduceExpiration(gameData.getDelta());
        }
    }
}
