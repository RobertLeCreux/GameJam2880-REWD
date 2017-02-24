package gamejam2880;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Gen
 */
public class Level {
    private ArrayList<Ground> groundList = new ArrayList<Ground>();
    private ArrayList<Mob> mobsList = new ArrayList<Mob>();
    private int furthestReached = 0;
    private int playerLocation = 0;
    private int lastGroundPlaced = 0;
    private Ground testGround = new Ground(1000000,1000000);
    private int lastMobAddedWidth;
    
    public Level(){
        for (int i = 0; GameJam2880.WINDOW_WIDTH > i * testGround.getWidth() ;i++){
            System.out.println(i);
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
            addMobs();
            System.out.println("further = " + furthestReached + "\n player location: " + playerLocation);
            furthestReached = playerLocation;
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
    
    public void addMobs(){
        double rando = Math.random();
        if (rando < 0.002){
            
            mobsList.add(new Mob(GameJam2880.WINDOW_WIDTH,10));
            
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
            System.out.println("" + (playerLocation + GameJam2880.WINDOW_WIDTH) + "  " + furthestReached + " " + (furthestReached - (playerLocation + GameJam2880.WINDOW_WIDTH)));
            System.out.println("");
        }
    }
    
    public void keyReleased(KeyEvent e){
        
    }
    
}
