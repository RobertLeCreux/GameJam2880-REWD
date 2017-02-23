package gamejam2880;

import java.util.*;

/**
 *
 * @author Gen
 */
public class Level {
    private ArrayList<Ground> groundList = new ArrayList<Ground>();
    private int furthestReached = 500;
    
    public Level(){
        
    }
    
    public void moveLevel(Player player){
        if (player.getX() + GameJam2880.WINDOW_WIDTH/2 >= furthestReached){
            addGround();
        }
    }
    
    public void addGround(){
        groundList.add(new Ground(10,GameJam2880.WINDOW_WIDTH));
    }
    
}
