package gamejam2880;

/**
 *
 * @author Robert LeCreux
 */

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.*;
import java.util.*;



public class Menu extends JPanel implements ActionListener {
    
    private Sprite screen;
    private Sprite button1;
    private Sprite button2;
    
    
    // ------Constructor-------
    public Menu(){
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        
        screen = new Sprite(0, 0,"menu.png");
        button1 = new Button(100, 250, "button1.png");
        button2 = new Button(250, 250, "button2.png");
    }

    
    //-----public methods-----
    
    //----- private methods------
    
    private void paintAssets(Graphics g){
        screen.doDrawing(g,this);
        button1.doDrawing(g,this);
        button2.doDrawing(g,this);
    }
    
    // -------Override methods
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        paintAssets(g);
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        
    }
    
    // ------ Class to detect user keyboard input------
    private class TAdapter extends KeyAdapter{
        
        @Override
        public void keyReleased(KeyEvent e){
            
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println("test");
            //player.keyPressed(e);
        }
    }
}
