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
    
    private Level level;
    private Player player;
    private final int DELAY = 10;
    private Timer timer;
    
    
    // ------Constructor-------
    public Board(){
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        level = new Level();
        player = new Player(10,10);
        timer = new Timer(DELAY, this);
        timer.start();     
        
        // ---- add random things to test functionality---
        //level.addGround();
        //level.addGround(10,200);
        
    }

    
    //-----public methods-----
    
    //----- private methods------
    
    private void paintAssets(Graphics g){
        player.doDrawing(g,this);
        level.drawLevel(g, this);
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
        level.moveLevel(player);        
        level.checkCollisions(player);
        
        repaint();
    }
    
    // ------ Class to detect user keyboard input------
    private class TAdapter extends KeyAdapter{
        
        @Override
        public void keyReleased(KeyEvent e){
            player.keyReleased(e);
            level.keyReleased(e);
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
            level.keyPressed(e);
        }
    }
}
