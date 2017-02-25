package gamejam2880;

/**
 *
 * @author Gen
 */

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.*;



public class Board extends JPanel implements ActionListener {
    
    public Player player;
    private final int DELAY = 10;
    private Timer timer;
    private Mob mob;
    private Level level;
    private Weapon mainGun;
    
    // ------Constructor-------
    public Board(){
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        player = new Player(10,10);
        
        timer = new Timer(DELAY, this);
        timer.start();
        

        level = new Level();
        
        // ---- add random things to test functionality---
        
    }

    
    //-----public methods-----
    
    //----- private methods------
    
    private void paintAssets(Graphics g){
        player.doDrawing(g,this);
        level.drawLevel(g, this);
        if (player.weapons.get(player.weaponIndex) != null){
            (player.weapons.get(player.weaponIndex)).doDrawing(g,this);
        }
        GameJam2880.key(player);
        
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
        level.cleanUp(player);
        if (player.getY()>GameJam2880.WINDOW_HEIGHT){
            player.setLives(-1);
            System.out.println("Lives: " + player.getLives()); 
        }
        
        
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
            //System.out.println("test");
            player.keyPressed(e);
        }
    }
}
