/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Robert LeCreux
 */
public class Weapon extends Sprite {
    private String type;
    public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    private ArrayList<GravProjectile> gravProjectiles;
    
    public static final String GRAV_GUN = "gravGun";
    public static final String FLAME_THROWER = "flameThrower";
    public static final String SPRAY_GUN = "sprayGun";
    public static final String CANNON_GUN = "cannonGun";
    public static final String LIGHTNING_GUN = "lightningGun";
    public static final String ION_GUN = "ionGun";
    public static final String SHOT_GUN = "shotGun";
    
    
    public int fireDelay = 10;
    public int fireCoolDown = 0;
    
    public Weapon(int x, int y, String weaponType, String img) {
        super(x, y, img);
        type = weaponType;
        if (type.equals(SHOT_GUN)){
            fireDelay =1;
        } else if (type.equals(ION_GUN)){
            fireDelay = 1;
        } else if (type.equals(LIGHTNING_GUN)){
            fireDelay = 5;
        }
    }
    
    //---------------------------------------------- get/set methods
    public String getType(){
        return type;
    }
    
    //----------------------------------------------------------- public methods
        
    
    public void move(Player player){
        if (player.getDirection() == Player.FACING_RIGHT){
            //this.setX(player.getX() + player.getWidth());
            //this.setY(player.getY() + player.getHeight() / 2);
            //System.out.println(player.getY());
            this.setX(player.getX() + player.getWidth() + Player.WEAPON_X_RIGHT_OFFSET);
        } else {
            this.setX(player.getX() - this.getWidth() + 3);
        }
        if(type == Weapon.SPRAY_GUN){
                this.setY((player.getY() + player.getHeight() / 2) + Player.WEAPON_Y_OFFSET - 20);
            } else {
                this.setY((player.getY() + player.getHeight() / 2) + Player.WEAPON_Y_OFFSET);
            }
        for (Projectile projectile : projectiles){
            projectile.move();
        }
    }
    
    public void fire(Player player){
            if(fireCoolDown <= 0){
                fireCoolDown = fireDelay;
                    if (type.equals(GRAV_GUN)){
                        gravProjectiles.add(new GravProjectile(x + width, y + height / 2,"none"));
                    } else{
                        //System.out.println("Firing weapon!");
                        //Projectile testBullet = new Projectile(player.getX() + player.getWidth(),player.getY() + player.getHeight());
                        if (type.equals(SHOT_GUN)){
                            projectiles.add(new Projectile(player.getX() + player.getWidth(),player.getY() + player.getHeight()/2, "mainGunBullet"));
                            try 
                            {
                            File file = new File("pew.wav");
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(file));
                            clip.start();
                            }
                            catch (Exception e) 
                            {
                            System.err.println(e.getMessage());
                            }
                        } else if (type.equals(ION_GUN)){
                            projectiles.add(new Projectile(player.getX() + player.getWidth(),player.getY() + player.getHeight()/2, "ionGunBullet"));
                            try 
                            {
                            File file = new File("zew.wav");
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(file));
                            clip.start();
                            }
                            catch (Exception e) 
                            {
                            System.err.println(e.getMessage());
                            }
                        }else if (type.equals(FLAME_THROWER)){
                            projectiles.add(new Projectile(player.getX() + player.getWidth(),player.getY() + player.getHeight()/2, "flameShot"));
                            try 
                            {
                            File file = new File("zew.wav");
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(file));
                            clip.start();
                            }
                            catch (Exception e) 
                            {
                            System.err.println(e.getMessage());
                            }
                        } else if (type.equals(LIGHTNING_GUN)){
                            projectiles.add(new Projectile(player.getX() + player.getWidth(),player.getY() + player.getHeight()/2, "lightningGunBullet"));
                            try 
                            {
                            File file = new File("zap.wav");
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(file));
                            clip.start();
                            }
                            catch (Exception e) 
                            {
                            System.err.println(e.getMessage());
                            }
                        }
                        //projectiles.add(testBullet);
                        //System.out.println(projectiles.size());
                    }
                }   
            } 
    
    //
    
    public void moveProjectiles(){
        for (Projectile projectile : projectiles){
            projectile.move();
        }
    }
    
    public void drawProjectiles(Graphics g,Board canvas){
        for (Projectile projectile : projectiles){
            projectile.doDrawing(g, canvas);
        }
    }
}
