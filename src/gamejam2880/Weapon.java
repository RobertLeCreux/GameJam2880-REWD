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
    
    public Weapon(int x, int y, String weaponType) {
        super(x, y);
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
            default:
                projectiles.add(new Projectile(x + width, y + height / 2));
                break;
        }
        
    }
    
}
