package gamejam2880;

/**
 *
 * @author Gen
 */

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JLabel;



public class Board extends JPanel implements ActionListener {
    
    public Player player;
    private final int DELAY = 10;
    private static Timer timer;
    private Mob mob;
    private Level level;
    private Weapon mainGun;
    private Sprite background;
    private JLabel lblScore;
    
    // ------Constructor-------
    public Board(){
        lblScore = new JLabel("Score: ");
        addKeyListener(new TAdapter());
        this.requestFocus();
        background = new Sprite(0,this.getHeight(),"backgroundMoutains.png");
        
        player = new Player(10,10);
        
        timer = new Timer(DELAY, this);
        timer.start();
        

        level = new Level();
        
        // ---- add random things to test functionality---
        
    }
    
    //------------------------------------------------------ get/set
    public static Timer getTimer(){
        return timer;
    }

    
    //-----public methods-----
    
    //----- private methods------
    
    private void paintAssets(Graphics g){
        background.doDrawing(g, this);
        player.doDrawing(g,this);
        level.drawLevel(g, this);
        lblScore.setText(Level.score);
        this.add(lblScore);
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
            // System.out.println("Lives: " + player.getLives()); 
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
