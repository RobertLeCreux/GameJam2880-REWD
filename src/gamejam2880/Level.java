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
    private int furthestReached = 200;
    private int playerLocation = 0;
    private int lastGroundPlaced = 0;
    private Ground testGround = new Ground(1000000,1000000);
    
    public Level(){
        System.out.println("test construct" + testGround.getWidth());
        for (int i = 0; GameJam2880.WINDOW_WIDTH > i * testGround.getWidth() ;i++){
            System.out.println(i);
            groundList.add(new Ground(i*testGround.getWidth(),GameJam2880.WINDOW_HEIGHT / 2));
        }
    }
    
    public void checkCollisions(Player player){
        Rectangle rp = player.getBounds();
        for (Ground ground : groundList){
            Rectangle rg = ground.getBounds();
            if (rg.intersects(rp)){
                player.setDY(0);
                player.setY(ground.getY() - player.getHeight() );
            }
        }
    }
    
    public void moveLevel(Player player){
        playerLocation += player.getDX();
        if (playerLocation + GameJam2880.WINDOW_WIDTH >= furthestReached){
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
        
        
        if ((playerLocation + GameJam2880.WINDOW_WIDTH > furthestReached) && player.getDX() > 0){
            furthestReached += Player.PLAYER_SPEED;
        }
        
    }
    
    
    //dynamically add ground to the end of the level
    public void addGround(){
        groundList.add(new Ground(GameJam2880.WINDOW_WIDTH,GameJam2880.WINDOW_HEIGHT / 2));
    }
    
    //add ground to a specific location
    public void addGround(int x, int y){
        groundList.add(new Ground(x,y));
    }
    
    public void drawLevel(Graphics g, Board canvas){
        for (int i = 0; i < groundList.size(); i++){
            groundList.get(i).doDrawing(g, canvas);
        }
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            //System.out.println("" + (playerLocation + GameJam2880.WINDOW_WIDTH) + "  " + furthestReached + " " + (furthestReached - (playerLocation + GameJam2880.WINDOW_WIDTH)));
            System.out.println("");
        }
    }
    
    public void keyReleased(KeyEvent e){
        
    }
    
}
