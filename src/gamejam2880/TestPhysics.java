
package gamejam2880;

/**
 *
 * @author Gen
 */
public class TestPhysics {
    
    public static int GRAVITY = 1;
    public static int GRAVITY_SLOW_FACTOR = 3;
    public static int NO_COLLISION = 0;
    public static int COLLISION_TOP = 1;
    public static int COLLISION_BOTTOM = 2;
    public static int COLLISION_RIGHT = 3;
    public static int COLLISION_LEFT = 4;
    public static int COLLISION_TOP_LEFT = 5;
    public static int COLLISION_TOP_RIGHT = 6;
    public static int COLLISION_BOTTOM_RIGHT = 7;
    public static int COLLISION_BOTTOM_LEFT = 8;
    public static int COLLISION_INDETERMINATE = 9;
    
    
    public static void setGravity(int g){
        GRAVITY = g;
    }
 
    
    public static int[] detectCollisions(SpriteShadow s1, SpriteShadow s2,int testing){
        //calculates if a collision occurs between two sprites, and if so what edge the collision occured on.
        
        int relDX, relX, relDY, relY;
        boolean cRight = true, cLeft = true, cTop = true, cBottom = true, cTopLeft = true, cTopRight = true, cBottomRight = true, cBottomLeft = true;
        //calculate relative velocity and distance between the two objects. Locigally eliminate
        // one collision direction from each axis.
        if ((s1.getX() - s1.getDX()) - (s2.getX() - s2.getDX()) > 0){
            relDX = s2.getDX() - s1.getDX();
            relX = (s1.getX() - s1.getDX()) - (s2.getX() - s2.getDX() - s2.getWidth());
            cRight = false;
            cTopRight = false;
            cBottomRight = false;
            if (testing == 1){
                System.out.println("cRight cTopRight cBottomRight eliiminated due to s1 further right than s2");
            }
            
        } else if ((s1.getX() - s1.getDX()) - (s2.getX() - s2.getDX()) < 0){
            relDX = s1.getDX() - s2.getDX();
            relX = (s2.getX() - s2.getDX()) - (s1.getX() - s1.getDX() - s1.getWidth());
            cLeft = false;
            cTopLeft = false;
            cBottomLeft = false;
            if (testing == 1){
                System.out.println("cLeft cTopLeft cBottomLeft eliiminated due to s1 further left than s2");
            }
        } else{
            relX = 0;
            relDX = 0;
            cRight = false;
            cLeft = false;
            cTopLeft = false;
            cTopRight = false;
            cBottomRight = false;
            cBottomLeft = false;
            if (testing == 1){
                System.out.println("x axis and all corners eliminated due to x coords being equal");
            }
        }
        
        if ((s1.getY() - s1.getDY()) - (s2.getY() - s2.getDY()) > 0){
            relDY = s2.getDY() - s1.getDY();
            relY = (s1.getY() - s1.getDY()) - (s2.getY() - s2.getDY() - s2.getHeight());
            cBottom = false;
            cBottomRight = false;
            cBottomLeft = false;
            if (testing == 1){
                System.out.println("cBottom cBottomRight cBottomLeft eliminated due to s1 being lower than s2");
            }
        } else if ((s1.getY() - s1.getDY()) - (s2.getY() - s2.getDY()) < 0){
            relDY = s1.getDY() - s2.getDY();
            relY = (s2.getY() - s2.getDY()) - (s1.getY() - s1.getDY() - s1.getHeight());
            cTop = false;
            cTopRight = false;
            cTopLeft = false;
            if (testing == 1){
                System.out.println("cTop cTopRight cTopLeft eliminated due to s1 being higher than s2");
            }
        } else{
            relY = 0;
            relDY = 0;
            cTop = false;
            cBottom = false;
            cTopRight = false;
            cTopLeft = false;
            cBottomRight = false;
            cBottomLeft = false;
            if (testing == 1){
                System.out.println(" y axis and all corners eliminated due to y coords being equal");
            }
        }
        if (testing ==1){
            System.out.println("relX: " + relX + " relDX: " + relDX + " relY: " + relY + " relDY: " + relDY + "");
        }
        
        if (relDX > 0 && relDY > 0){
            if ((relX / relDX) > (relY / relDY)){
                cRight = false;
                cLeft = false;
                if (testing == 1){
                    System.out.println("x axis eliminated due to time");
                }
            } else if ((relX / relDX) < (relY / relDY)){
                cTop = false;
                cBottom = false;    
                if (testing == 1){
                    System.out.println("y axis aliminated due to time");
                }
            } else{
                cTop = false;
                cBottom = false;
                cRight = false;
                cLeft = false;
                if (testing == 1){
                    System.out.println("must be a corner due to equal time.");
                }
            }
        } else if (relDX > 0 && relDY <= 0){
            cTop = false;
            cBottom = false;
            cTopRight = false;
            cTopLeft = false;
            cBottomLeft = false;
            cBottomRight = false;
            if (testing == 1){
                System.out.println("y axis and corners eliminated due to rely <= 0");
            }
        } else if (relDX <= 0 && relDY > 0){
            cRight = false;
            cLeft = false;
            cTopRight = false;
            cTopLeft = false;
            cBottomLeft = false;
            cBottomRight = false;
            if (testing == 1){
                System.out.println(" x axis and corners eliminated due to relx <= 0");
            }
        } else{
            cTop = false;
            cBottom = false;
            cRight = false;
            cLeft = false;
            cTopRight = false;
            cTopLeft = false;
            cBottomLeft = false;
            cBottomRight = false;
            if (testing == 1){
                System.out.println("all options eliminated due to negative rv for x and y");
            }
        }
        
        
        if (!s1.getRect().intersects(s2.getRect())){
            return new int[] {NO_COLLISION, NO_COLLISION};
        } else if (cTop){
            return new int[] {COLLISION_TOP, COLLISION_BOTTOM};
        } else if (cLeft){
            return new int[] {COLLISION_LEFT, COLLISION_RIGHT};
        } else if (cBottom){
            return new int[] {COLLISION_BOTTOM,COLLISION_TOP};
        } else if (cRight){
            return new int[] {COLLISION_RIGHT,COLLISION_LEFT};
        } else if(cTopRight){
            return new int[] {COLLISION_TOP_RIGHT, COLLISION_BOTTOM_LEFT};
        } else if (cTopLeft){
            return new int[] {COLLISION_TOP_LEFT, COLLISION_BOTTOM_RIGHT};
        } else if (cBottomLeft){
            return new int[] {COLLISION_BOTTOM_LEFT, COLLISION_TOP_RIGHT};
        } else if (cBottomRight){
            return new int[] {COLLISION_BOTTOM_RIGHT, COLLISION_TOP_LEFT};
        } else{
            return new int[] {COLLISION_INDETERMINATE};
        }
    }
    
}

