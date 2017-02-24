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
    private int furthestReached = 0;
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
        player.setLives(player.getLives() - 1);
        player.setDX(-30);
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
        if (rando < 0.02){
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
                mobsList.add(new Mob(GameJam2880.WINDOW_WIDTH,10,mobImageList.get(imageIndexInt)));
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
