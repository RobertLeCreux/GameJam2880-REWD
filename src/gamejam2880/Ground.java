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
        public int timer = 500;
        
        
        public Image jGround;
        
        public void setB(int a){
            a = 50;
        }
        public void setA(int b){
            b = 10;
    }
        
        public Ground(int x,int y) {
            super(x, y);
            setDY(-12);
            loadImage("floorRock.png");
            
        }
        
        
        private void loadGround() {
            ImageIcon ii = new ImageIcon("floorRock.png");
            jGround = ii.getImage();
        }            
}
