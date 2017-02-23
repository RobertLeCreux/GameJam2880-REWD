package gamejam2880;

/**
 *
 * @author Gen
 */

import gamejam2880.Board;
import java.awt.EventQueue;
import javax.swing.JFrame;


public class GameJam2880 extends JFrame{
    
    public GameJam2880(){
        initUI();
    }
    
    private void initUI(){
        add(new Board());
        
        setSize(250,200);
        
        setTitle("GameJam2880");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                GameJam2880 game = new GameJam2880();
                game.setVisible(true);
            }
        });
    }
    
}
