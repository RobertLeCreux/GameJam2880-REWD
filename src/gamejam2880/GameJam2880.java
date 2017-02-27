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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;


public class GameJam2880 {
    
    
    private static MenuBetter menu;
    private static Board board;
    private static JFrame frame;
    private static JButton btnForward;
    private static JButton btnExit;
    private static JButton btnResume;
    private static Player player;
    private static File file;
    private static Clip clip;
    private boolean playing;
    
    public static int WINDOW_WIDTH = 1000;
    public static int WINDOW_HEIGHT = 700;
    
    public GameJam2880() throws LineUnavailableException{

        file = new File("Beats.wav");
        clip = AudioSystem.getClip();
        frame =  new JFrame("GameJam2880");
        menu = new MenuBetter();
        initUI();
        player = board.player;
        playing = true;
        
        
        //play();
        
        
    }
   
    private static void initUI(){
        board = new Board();
        frame.add(menu);
        menu.requestFocus();
        frame.revalidate();
        frame.setResizable(false);
        Timer timer = Board.getTimer();
        
        btnForward = (JButton) menu.getComponent(0);
        btnForward.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent ae){
               frame.remove(menu);
               frame.add(board);
               play();
               board.requestFocus();
               frame.revalidate();
               frame.repaint();
           }
        });
        btnResume = (JButton) menu.getComponent(0);
        btnResume.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent ae){
               frame.remove(menu);
               frame.add(board);
               board.paused = false;
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
        Timer timer = Board.getTimer();
        if(player.getLives() <= 0){
            frame.remove(board);
            stopPlay();
            timer.restart();
            initUI();
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                try {
                    new GameJam2880();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GameJam2880.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    public static void play() {
        if(!clip.isOpen()){
            try {
                clip.open(AudioSystem.getAudioInputStream(file));
            } catch (Exception e){
                System.err.println(e.getMessage());
            }        
            }
        
            clip.setMicrosecondPosition(0);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            } 
   
        public static void stopPlay() {
            
            clip.stop();
            
        } 
    
        
       
            
   
    }
    
   
     

