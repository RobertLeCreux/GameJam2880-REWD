package gamejam2880;

/**
 *
 * @author Gen
 */

import gamejam2880.Board;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GameJam2880 extends JFrame implements ActionListener{
    
    private JPanel panel;
    private CardLayout card;
    private Button button;
    
    
    
    public static int WINDOW_WIDTH = 700;
    public static int WINDOW_HEIGHT = 700;
    
    public GameJam2880(){
        initUI();
        
    }
    
    private void initUI(){
        panel = new JPanel();
        card = new CardLayout();
        panel.setLayout(card);
        //panel.add(new Menu());
        panel.add(new Board());
        
        add(panel);
        //pack();
        
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        
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

    @Override
    public void actionPerformed(ActionEvent e) {
        
        card.next(panel);
    }
    
}
