/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;

import java.util.ArrayList;

/**
 *
 * @author Robert LeCreux
 */
public class Weapon extends NonStationary {
    private String type;
    private ArrayList<Projectile> projectiles;
    private ArrayList<GravProjectile> gravProjectiles;
    private ArrayList<Projectile> flameProjectiles;
    private ArrayList<Projectile> sprayProjectiles;
    private ArrayList<Projectile> shotProjectiles;
    private ArrayList<Projectile> lightningProjectiles;
    private ArrayList<Projectile> cannonProjectiles;
    private ArrayList<Projectile> ionProjectiles;
    
    public static final String GRAV_GUN = "gravGun";
    public static final String FLAME_THROWER = "flameThrower";
    public static final String SPRAY_GUN = "sprayGun";
    public static final String CANNON_GUN = "cannonGun";
    public static final String LIGHTNING_GUN = "lightningGun";
    public static final String ION_GUN = "ionGun";
    public static final String SHOT_GUN = "shotGun";
    
    public Weapon(int x, int y, String weaponType, String img) {
        super(x, y, img);
        type = weaponType;
    }
    
    //---------------------------------------------- get/set methods
    public String getType(){
        return type;
    }
    
    //----------------------------------------------------------- public methods
    
    public void fire() {
        switch (type){
            case "gravGun":
                gravProjectiles.add(new GravProjectile(x + width, y + height / 2));
                break;
            case "flameThrower":
                flameProjectiles.add(new Projectile(x + width, y + height / 2));
                break;
            case "sprayGun":
                sprayProjectiles.add(new Projectile(x + width, y + height / 2));
                break;
            case "cannonGun":
                cannonProjectiles.add(new Projectile(x + width, y + height / 2));
                break;
            case "lightningGun":
                lightningProjectiles.add(new Projectile(x + width, y + height / 2));
                break;
            case "ionGun":
                ionProjectiles.add(new Projectile(x + width, y + height / 2));
                break;
            default:
                shotProjectiles.add(new Projectile(x + width, y + height / 2));
                break;
        }
        
    }
    
}
