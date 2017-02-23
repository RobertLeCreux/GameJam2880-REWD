/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;

import java.util.*;

/**
 *
 * @author rober
 */
public class Player extends Sprite{
    private int dx, dy, lives;
    private ArrayList<Rounds> rounds;
    
    private final int MAX_LIVES = 3;
    
    public Player(int x, int y) {
        super(x, y);
        lives = MAX_LIVES;
    }
    
    public void move(){
        x += dx;
        y += dy;
        
        if (x<1) x=1;
        if (y<1) y=1;
    }
    
    public ArrayList getRounds(){
        return rounds;
    }
    
    
}
