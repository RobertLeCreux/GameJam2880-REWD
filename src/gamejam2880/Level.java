package gamejam2880;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

/**
 *
 * @author Gen
 */
public class Level {
    private ArrayList<Ground> groundList = new ArrayList<Ground>();
    private ArrayList<Mob> mobsList = new ArrayList<Mob>();
    private ArrayList<String> mobImageList = new ArrayList<String>();
    private long furthestReached = 0;
    private int playerLocation = 0;
    private int lastGroundPlaced = 0;
    private Ground testGround = new Ground(1000000,1000000);
    private int lastMobAddedWidth;
    private int lastMobAddedCount;
    
    public Level(){
        mobImageList.add("vulture.png");
        mobImageList.add("cloud1.png");
        mobImageList.add("dragonG1.png");
        mobImageList.add("mob3.png");
        for (int i = 0; GameJam2880.WINDOW_WIDTH > i * testGround.getWidth() ;i++){
            groundList.add(new Ground(i*testGround.getWidth(),(GameJam2880.WINDOW_HEIGHT / 2) + 100) );
        }
    }
    
    public long getFurthestReached(){
        return furthestReached;
    }
    
    public void checkCollisions(Player player){
        for (Ground ground : groundList){
            Rectangle rg = ground.getBounds();
            if (player.detectCollision(rg) == Sprite.COLLISION_BOTTOM){
                player.setDY(0);
                player.setY(ground.getY() - player.getHeight());
                player.setTouchedGround(true);
                if (player.getDirection() == Player.FACING_RIGHT){
                    player.weapons.get(player.weaponIndex).setY((player.getY() + player.getHeight() / 2) + Player.WEAPON_Y_OFFSET);
                } else {
                    player.weapons.get(player.weaponIndex).setY((player.getY() + player.getHeight() / 2) + Player.WEAPON_Y_OFFSET);
                }              
            }
        }
            
        for (Mob mob : mobsList){
            if (mob.detectCollision(player.getBounds()) > 0){
                System.out.println(mob.detectCollision(player.getBounds()));
                playerHit(player,mob);
            }
            for (Ground grounds : groundList){
                Rectangle rgs = grounds.getBounds();
                if (mob.detectCollision(rgs) == Sprite.COLLISION_BOTTOM){
                    mob.setDY(0);
                    mob.setY(grounds.getY() - mob.getHeight() - 1);
                }
            }
            
            for(Weapon weapon : player.weapons){
                for (Projectile projectile : weapon.projectiles){
                    if (mob.detectCollision(projectile.getBounds()) > 0){
                        mobHit(mob,projectile,player);
                    }
                }
            }
        }
    }
    
    public void moveLevel(Player player){
        playerLocation += player.getDX();
        if (playerLocation > furthestReached){
            if(lastGroundPlaced >= testGround.getWidth() - 10){
                addGround();
                lastGroundPlaced = 0;
            } else{
                lastGroundPlaced += player.getDX();
            }
            
        }
        
        for (int i = 0; i < groundList.size(); i++){
            groundList.get(i).setX(groundList.get(i).getX() - player.getDX());
            groundList.get(i).timer -= 1;
            if (groundList.get(i).timer <= 0){
                groundList.get(i).setDY(groundList.get(i).getDY() + Physics.GRAVITY);
                groundList.get(i).setY(groundList.get(i).getY() + groundList.get(i).getDY());
            }
        }
        
        for (Mob mob : mobsList){
            mob.move();
            mob.setX(mob.getX() - player.getDX());
        }
        
        
        if (playerLocation > furthestReached){
            addMobs(player);
            furthestReached = playerLocation;
        }  
    }
    
    public void playerHit(Player player, Mob mob){
        System.out.println("lives to change by: " + (player.getLives() - 1));
        player.setLives(player.getLives() - 1);
        player.setDX(-30);
    }
    
    public void mobHit(Mob mob, Projectile projectile,Player player){
        mob.hp -= 1;
        if (mob.hp <= 0){
            mob.setVisible(false);
        }
        
        projectile.setVisible(false);
        }
    
    public void cleanUp(Player player){
        for(Weapon weapon : player.weapons){
            for(int i = 0; i < weapon.projectiles.size(); i++){
                if (weapon.projectiles.get(i).isVisible() == false){
                    weapon.projectiles.remove(i);
                }       
            }
        }
        
        for(int i = 0; i < mobsList.size(); i++){
            if (mobsList.get(i).isVisible() == false){
                mobsList.remove(i);
            }
        }
    }
    
    //dynamically add ground to the end of the level
    public void addGround(){
        groundList.add(new Ground(GameJam2880.WINDOW_WIDTH,(GameJam2880.WINDOW_HEIGHT / 2) + 100));
    }
    
    //add ground to a specific location
    public void addGround(int x, int y){
        groundList.add(new Ground(x,y));
    }
    
    //dynamically add mobs to end of level
    
    public void addMobs(Player player){
        lastMobAddedCount += player.getDX();
        double rando = Math.random();
        if (rando < Mob.MOB_SPAWN_FREQ){
            long imageIndex = Math.round(Math.random() * mobImageList.size());
            int imageIndexInt = (int) imageIndex;
            if(imageIndexInt >= mobImageList.size()){
                imageIndexInt = mobImageList.size() - 1;
            }
            ImageIcon tempImageIcon = new ImageIcon(mobImageList.get(imageIndexInt));
            Image tempImage = tempImageIcon.getImage();
            if (lastMobAddedCount > lastMobAddedWidth){
                lastMobAddedWidth = tempImage.getWidth(null);
                lastMobAddedCount = 0;
                String mobSelected = mobImageList.get(imageIndexInt);
                int mobHp = 1;
                if (mobSelected.equals("vulture.png")){
                    mobHp = Mob.VULTURE_HP;
                } else if (mobSelected.equals("cloud1.png")){
                    mobHp = Mob.CLOUD_HP;
                } else if (mobSelected.equals("dragonG1.png")){
                    mobHp = Mob.DRAGON_HP;
                }
                mobsList.add(new Mob(GameJam2880.WINDOW_WIDTH,10,mobSelected,mobHp));
            }
        }
    }
    
    public void drawLevel(Graphics g, Board canvas){
        for (int i = 0; i < groundList.size(); i++){
            groundList.get(i).doDrawing(g, canvas);
        }
        
        for (Mob mob : mobsList){
            mob.doDrawing(g, canvas);
        }
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
        }
    }
    
    public void keyReleased(KeyEvent e){
        
    }
    
}
