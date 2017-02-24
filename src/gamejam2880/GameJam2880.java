package gamejam2880;

/**
 *
 * @author Gen
 */

import gamejam2880.Board;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GameJam2880 {
    
    
    private MenuBetter menu;
    private Board board;
    private JFrame frame;
    private JButton btnForward;
    private JButton btnExit;
    
    
    public static int WINDOW_WIDTH = 1000;
    public static int WINDOW_HEIGHT = 700;
    
    public GameJam2880(){
        initUI();
        
    }
    
    private void initUI(){
        frame =  new JFrame("GameJam2880");
        menu = new MenuBetter();
        board = new Board();
        
        frame.add(menu);
        
        btnForward = (JButton) menu.getComponent(1);
        btnForward.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent ae){
               frame.remove(menu);
               frame.add(board);
               board.requestFocus();
               frame.revalidate();
               frame.repaint();
           }
        });
        btnExit = (JButton) menu.getComponent(4);
        btnExit.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent ae){
               System.exit(0);
           }
        });
        
        //pack();
        
        frame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        frame.setTitle("GameJam2880");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                new GameJam2880();
                
            }
        });
    }
}
