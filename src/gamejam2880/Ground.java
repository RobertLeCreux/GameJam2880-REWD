package gamejam2880;


/**
 *
 * @author kylechapman
 */

import java.awt.Image;
import javax.swing.ImageIcon;



public class Ground extends Sprite {
        
        public int a;
        public int b; 
        
        
        public Image jGround;
        
        public void setB(int a){
            a = 50;
        }
        public void setA(int b){
            b = 10;
    }
        
        public Ground(int x,int y) {
            super(x, y);
            loadImage("floorRock.png");
            
        }
        
        
        private void loadGround() {
            ImageIcon ii = new ImageIcon("rock.png");
            jGround = ii.getImage();
        }            
}
