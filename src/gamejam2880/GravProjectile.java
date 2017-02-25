/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;

/**
 *
 * @author Robert LeCreux
 */
public class GravProjectile extends Projectile {
    
    private int force;
    
    public GravProjectile(int x, int y, String type) {
        super(x, y, "none");
        this.loadImage("gravAmmo.png");
    }
    
    public int getForce(){
        return force;
    }
    public void setForce(int f){
        force = f;
    }
    
}
