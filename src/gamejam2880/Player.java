/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * 
 * @author Robert LeCreux
 */

public class Player extends NonStationary {
    public static int PLAYER_SPEED = 3;
    private ArrayList<Weapon> weapon;
    private int weaponIndex;
    private boolean touchedGround;
    
    
    
    public Player(int x, int y) {
        super(x, y);

        initPlayer();
    }

    private void initPlayer() {
        
        // missiles = new ArrayList<>();
        loadImage("Sketch002.png");
        getImageDimensions();
        this.setX(GameJam2880.WINDOW_WIDTH / 8 - this.getWidth() / 2);
        touchedGround = true;
    }

    /*
    public void move() {

        x += this.getDX();
        y += this.getDY();

        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }
    */
/*
    public ArrayList getMissiles() {
        return missiles;
    }*/
    
    public void setTouchedGround(Boolean bool){
        touchedGround = bool;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        switch(key){
            case KeyEvent.VK_1:
                weaponIndex = 1;
                break;
            case KeyEvent.VK_2:
                weaponIndex = 2;
                break;
            case KeyEvent.VK_3:
                weaponIndex = 3;
                break;
        }
        
        if (key == KeyEvent.VK_SPACE) {
            weapon.get(weaponIndex).fire();
        }
        
        if (key == KeyEvent.VK_ENTER){
            if (touchedGround){
                this.setDY(- 20);
                touchedGround = false;
                //this.setY(this.getY() - 10);
            }
        }

        
        if (key == KeyEvent.VK_LEFT) {
            this.setDX(-PLAYER_SPEED);
            System.out.println("Left");
        }

        if (key == KeyEvent.VK_RIGHT) {
            super.setDX(PLAYER_SPEED);
            System.out.println("Right");
        }
        
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
             setDX(0);
        }

        if (key == KeyEvent.VK_RIGHT) {
            setDX(0);
        }
    }
}