/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;

import java.awt.Graphics;
import static java.awt.event.KeyEvent.*;

/**
 *
 * @author rober
 */
public class Projectile extends NonStationary {
    private String ammoType;
    public int timeToLive = 500;
    public int damage = 1;
    
    public Projectile(int x, int y,String type) {
        super(x, y);
        setDX(12);
        ammoType = type;
        if (ammoType.equals("mainGunBullet")){
            this.loadImage("mainGunBullet.png");
            int randoDX = (int) Math.floor(Math.random() * 20);
            setDX(randoDX);
            damage = 2;
        }
        
        if (ammoType.equals("ionGunBullet")){
            this.loadImage("ionGunBulletLeft.png");
            setDX(15);
            int randoDY = (int) Math.floor(Math.random() * 20);
            int randoUpDown = (int) Math.floor(Math.random() * 2);
            if (randoUpDown == 0){
                setDY(randoDY);
            } else{
                setDY(-randoDY);
            }
        }
    }
    
    @Override
    public void doDrawing(Graphics g,Board canvas){
        super.doDrawing(g, canvas);
    }
    
    public void move(){
        setX(getX() + getDX());
        setY(getY() + getDY());
    }
    
}
