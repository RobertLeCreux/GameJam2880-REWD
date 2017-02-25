package gamejam2880;

/**
 *
 * @author Gen
 */
import gamejam2880.Board;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class GameJam2880 {
    
    
    private static MenuBetter menu;
    private static Board board;
    private static JFrame frame;
    private static JButton btnForward;
    private static JButton btnExit;
    private static Player player;
    private boolean playing;
    
    public static int WINDOW_WIDTH = 1000;
    public static int WINDOW_HEIGHT = 700;
    
    public GameJam2880(){

       
        
        frame =  new JFrame("GameJam2880");
        menu = new MenuBetter();
       
            
        
        initUI();
        player = board.player;
        playing = true;
        
        
        play();
        
        
    }
   
    private static void initUI(){
        board = new Board();
        frame.add(menu);
        menu.requestFocus();
        frame.revalidate();
        frame.setResizable(false);
        
        btnForward = (JButton) menu.getComponent(0);
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
        btnExit = (JButton) menu.getComponent(1);
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
    


    public static void key(Player player){
        // System.out.println("Key pressed should exit" + player.keyPressed);
        if((player.keyPressed == 27) || (player.getLives() <= 0)){
            frame.remove(board);
            initUI();
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                new GameJam2880();
            }
        });
    }
    public static void play() {
        
    
        
       
        try {
            File file = new File("Beats.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) 
            {
            System.err.println(e.getMessage());
            }
            } 
   
    }
    
   
     

