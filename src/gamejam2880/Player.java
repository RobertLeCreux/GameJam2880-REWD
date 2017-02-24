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
    public static int JUMP_FORCE = 20;
    private ArrayList<Weapon> weapon;
    private int weaponIndex;
    private boolean touchedGround;
    
    private int lives;
    
    public static final int STARTING_LIVES = 3;
    
    
    
    public Player(int x, int y) {
        super(x, y);
        lives = STARTING_LIVES;
        initPlayer();
    }

    private void initPlayer() {
        
        // weapons = new ArrayList<>();
        loadImage("AlienLeft.png");
        getImageDimensions();
        this.setX(GameJam2880.WINDOW_WIDTH / 8 - this.getWidth() / 2);
        touchedGround = true;
        weapon = new ArrayList();
        weapon.add(new Weapon(this.getX(), this.getDY()/2, Weapon.SHOT_GUN, "shotGun.png"));
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
    
    public void setLives(int life){
        lives += life;
    }
    public int getLives(){
        return lives;
    }
    
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
            case KeyEvent.VK_4:
                weaponIndex = 4;
                break;
            case KeyEvent.VK_5:
                weaponIndex = 5;
                break;
            case KeyEvent.VK_6:
                weaponIndex = 6;
                break;
        }
        
        if (key == KeyEvent.VK_ENTER) {
            weapon.get(weaponIndex).fire();
        }
        
        if (key == KeyEvent.VK_SPACE){
            if (touchedGround){
                this.setDY(- (JUMP_FORCE / Physics.GRAVITY));
                touchedGround = false;
                //this.setY(this.getY() - 10);
            }
        }

        
        if (key == KeyEvent.VK_LEFT) {
            this.setDX(-PLAYER_SPEED);
            this.loadImage("AlienLeft.png");
        }

        if (key == KeyEvent.VK_RIGHT) {
            super.setDX(PLAYER_SPEED);
            this.loadImage("AlienRight.png");
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