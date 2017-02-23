package gamejam2880;


/**
 *
 * @author kylechapman
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import 


public class Ground extends JPanel {
        
        public Image jGround; 
        
        public Ground() {
            initGround();
        }
        private void initGround(){
            loadGround();
            int w = jGround.getWidth(this);
            int h = jGround.getHeight(this);
            setPreferredSize(new Dimension(w, h));
        }
        
        private void loadGround() {
            ImageIcon ii = new ImageIcon("jGround.png");
            jGround = ii.getImage();
        }
        
        private void creatGround(){
                 = jGround;
        }
        
}
