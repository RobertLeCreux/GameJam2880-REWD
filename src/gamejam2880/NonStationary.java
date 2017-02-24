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
    private int dx;
    private int dy;
    
    private static int gravity;
    
    
    public NonStationary(int x, int y) {
        super(x, y);
        gravity = 9;
    }
    
    public void setGravity(int grav){
        gravity = grav;
    }
    
    public int getDX(){
        return dx;
    }
    public void setDX(int deltaX){
        dx = deltaX;
    }
    
    public int getDY(){
        return dy;
    }
    public void setDY(int deltaY){
        dy = deltaY;
    }
    
    public void move(){
        y += dy;
        dy += Physics.GRAVITY;
    }
}