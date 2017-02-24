/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;

/**
 *
 * @author Robert LeCreux
 */
public class NonStationary extends Sprite{
    
    private static int gravity;
    
    
    public NonStationary(int x, int y) {
        super(x, y);
        gravity = 9;
    }
    public NonStationary(int x, int y, String img) {
        super(x, y, img);
        gravity = 9;
    }
    
    public void setGravity(int grav){
        gravity = grav;
    }
        
    public void move(){
        y += dy;
        dy += Physics.GRAVITY;
    }
}