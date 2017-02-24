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
    private int gravitySlower = 0;
    
    
    
    
    public NonStationary(int x, int y) {
        super(x, y);
        this.setVisible(true);
    }
    public NonStationary(int x, int y, String img) {
        super(x, y, img);
    }
    
           
    public void move(){
        
        this.setY(this.getY() + this.getDY());
        
        if (gravitySlower == Physics.GRAVITY_SLOW_FACTOR){
            this.setDY(this.getDY() + Physics.GRAVITY);
            gravitySlower = 0;
        } else{
            gravitySlower += 1;
        }
        
    }
}