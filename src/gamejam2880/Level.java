package gamejam2880;

import static gamejam2880.GameJam2880.play;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Gen
 */
public class Level {
    private ArrayList<Ground> groundList = new ArrayList<Ground>();
    private ArrayList<Mob> mobsList = new ArrayList<Mob>();
    private ArrayList<String> mobImageList = new ArrayList<String>();
    private long furthestReached;
    private int playerLocation;
    private int lastGroundPlaced;
    private Ground testGround = new Ground(1000000,1000000);
    private int lastMobAddedWidth;
    private int lastMobAddedCount;
    public static String score;
    
    public Level(){
        furthestReached = 0;
        playerLocation = 0;
        lastGroundPlaced = 0;
        mobImageList.add("vulture.png");
        mobImageList.add("cloud1.png");
        mobImageList.add("dragonG1.png");
        mobImageList.add("killerBunny.png");
        for (int i = 0; GameJam2880.WINDOW_WIDTH > i * testGround.getWidth() ;i++){
            groundList.add(new Ground(i*testGround.getWidth(),(GameJam2880.WINDOW_HEIGHT / 2) + 100) );
        }
        
        // -- add test shit
        groundList.add(new Ground(100,100));
    }
    
    public long getFurthestReached(){
        return furthestReached;
    }
    
    
    public void checkCollisions(Player player){
        for (Ground ground : groundList){
            Rectangle rg = ground.getBounds();
            //if (player.detectCollision(rg) == Sprite.COLLISION_BOTTOM){
            //if (player.getBounds().intersects(ground.getBounds())){
                //if ((TestPhysics.detectCollisions(player.getShadow(), ground.getShadow(), 0)[0] != Physics.COLLISION_BOTTOM)){
                    //System.out.println((TestPhysics.detectCollisions(player.getShadow(), ground.getShadow(), 1)[0]));
                //}
            //}
            if ((TestPhysics.detectCollisions(player.getShadow(), ground.getShadow(), 0)[0] == TestPhysics.COLLISION_BOTTOM) || (TestPhysics.detectCollisions(player.getShadow(), ground.getShadow(), 0)[0] == TestPhysics.COLLISION_BOTTOM_LEFT) || (TestPhysics.detectCollisions(player.getShadow(), ground.getShadow(), 0)[0] == TestPhysics.COLLISION_BOTTOM_RIGHT)){
                player.setDY(0);
                player.setY(ground.getY() - player.getHeight());
                player.setTouchedGround(true);
                if(player.equipped.getType() == Weapon.SPRAY_GUN){
                    player.weapons.get(player.weaponIndex).setY((player.getY() + player.getHeight() / 2) + Player.WEAPON_Y_OFFSET - 20);
                } else if(player.equipped.getType() == Weapon.CANNON_GUN){
                    player.weapons.get(player.weaponIndex).setY((player.getY() + player.getHeight() / 2) + Player.WEAPON_Y_OFFSET - 20);
                } else {player.weapons.get(player.weaponIndex).setY((player.getY() + player.getHeight() / 2) + Player.WEAPON_Y_OFFSET);
                }                       
            } else if (player.getBounds().intersects(ground.getBounds())){
                System.out.println("Test output: " + (TestPhysics.detectCollisions(player.getShadow(), ground.getShadow(), 1)[0]));
            }
        }
            
        for (Mob mob : mobsList){
            if (mob.detectCollision(player.getBounds()) > 0){
                //System.out.println(mob.detectCollision(player.getBounds()));
                playerHit(player,mob);
            }
            for (Ground grounds : groundList){
                Rectangle rgs = grounds.getBounds();
                //if (mob.detectCollision(rgs) == Sprite.COLLISION_BOTTOM){
                if (TestPhysics.detectCollisions(mob.getShadow(), grounds.getShadow(), 0)[0] == TestPhysics.COLLISION_BOTTOM || TestPhysics.detectCollisions(mob.getShadow(), grounds.getShadow(), 0)[0] == TestPhysics.COLLISION_BOTTOM_LEFT || TestPhysics.detectCollisions(mob.getShadow(), grounds.getShadow(), 0)[0] == TestPhysics.COLLISION_BOTTOM_RIGHT){
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
            score = "Score: " + (furthestReached / 100);
            //System.out.println(score);
        }  
    }
    
    public void playerHit(Player player, Mob mob){
        //System.out.println("lives to change by: " + (player.getLives() - 1));
        player.setLives(player.getLives() - 1);
        player.setDX(-30);
    }
    
    public void mobHit(Mob mob, Projectile projectile,Player player){
        mob.hp -= projectile.damage;
        if (mob.hp <= 0){
            mob.setVisible(false);
        }
        
        projectile.setVisible(false);
        }
    
    public void cleanUp(Player player){
        for(Weapon weapon : player.weapons){
            weapon.fireCoolDown -= 1;
            for(int i = 0; i < weapon.projectiles.size(); i++){
                weapon.projectiles.get(i).timeToLive -= 1;
                if (weapon.projectiles.get(i).isVisible() == false || (weapon.projectiles.get(i).getY() > GameJam2880.WINDOW_HEIGHT) || (weapon.projectiles.get(i).getX() > GameJam2880.WINDOW_WIDTH || (weapon.projectiles.get(i).timeToLive < 0))){
                    weapon.projectiles.remove(i);
                }       
            }
        }
        
        for(int i = 0; i < mobsList.size(); i++){
            if ((mobsList.get(i).isVisible() == false) || (mobsList.get(i).getY() > GameJam2880.WINDOW_HEIGHT)){
                mobsList.remove(i);
            }
        }
        
        for (int i = 0; i < groundList.size(); i++){
            if (groundList.get(i).getY() > GameJam2880.WINDOW_HEIGHT){
                groundList.remove(i);
            }
        }
    }
    
    //dynamically add ground to the end of the level
    public void addGround(){
        int randoGroundInt = (int) Math.floor(Math.random() * 10);
        if (randoGroundInt > 0){
            groundList.add(new Ground(GameJam2880.WINDOW_WIDTH,(GameJam2880.WINDOW_HEIGHT / 2) + 100));
        }
    }
    
    //add ground to a specific location
    public void addGround(int x, int y){
        int randoGroundInt = (int) Math.floor(Math.random() * 10);
        if (randoGroundInt > 0){
            groundList.add(new Ground(x,y));
        }
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
