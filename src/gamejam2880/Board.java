package gamejam2880;

/**
 *
 * @author Gen
 */

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.*;
import java.util.*;



public class Board extends JPanel implements ActionListener {
    
    private Player player;
    private final int DELAY = 10;
    private Timer timer;
    private Ground ground;
    
    
    // ------Constructor-------
    public Board(){
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        player = new Player(10,10);
        timer = new Timer(DELAY, this);
        timer.start();     
        
        ground = new Ground(10, player.height);
    }

    
    //-----public methods-----
    
    //----- private methods------
    
    private void paintAssets(Graphics g){
        player.doDrawing(g,this);
        ground.doDrawing(g,this);
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
        player.move();
        repaint();
    }
    
    // ------ Class to detect user keyboard input------
    private class TAdapter extends KeyAdapter{
        
        @Override
        public void keyReleased(KeyEvent e){
            player.keyReleased(e);
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("test");
            player.keyPressed(e);
        }
    }
}
