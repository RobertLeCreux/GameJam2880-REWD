/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * 
 * @author Robert LeCreux 
 */

public class Player extends NonStationary {
    public static int PLAYER_SPEED = 3;
    public static int FACING_LEFT = 0;
    public static int FACING_RIGHT = 1;
    public static int WEAPON_Y_OFFSET = -18;
    public static int WEAPON_X_RIGHT_OFFSET = -3;
    public static int WEAPON_X_LEFT_OFFSET = -63;
    
    
    public ArrayList<Weapon> weapons;
    public ArrayList<String> imgWeapons;
    protected int weaponIndex;
    public static int IMG_WEAPONS_INDEX;
    
    public int keyPressed;
    public static int JUMP_FORCE = 10;
    private boolean touchedGround;
    private Weapon mainGun, flameThrower, sprayGun, cannonGun, lightningGun, ionGun;
    public Weapon equipped;
    private int direction = FACING_RIGHT;
    
    private int lives;
    
    public static final int STARTING_LIVES = 3;
    
    
    
    public Player(int x, int y) {
        super(x, y);
        lives = STARTING_LIVES;
        initPlayer();
        equipped = weapons.get(0);
    }

    private void initPlayer() {
        
        // weapons = new ArrayList<>();
        loadImage("AlienRight.png");
        getImageDimensions();
        this.setX((GameJam2880.WINDOW_WIDTH / 18 - this.getWidth() / 2) + 300);
        touchedGround = true;
        weapons = new ArrayList();
        imgWeapons = new ArrayList();
        
        // TODO: fix sprites here
        imgWeapons.add("mainGunRight.png");
        imgWeapons.add("mainGunLeft.png");
        imgWeapons.add("ionGunRight.png");
        imgWeapons.add("ionGunLeft.png");
        imgWeapons.add("flameGunRight.png");
        imgWeapons.add("flameGunLeft.png");
        imgWeapons.add("sprayGunRight.png");
        imgWeapons.add("sprayGunLeft.png");
        imgWeapons.add("lghtinGunRight.png");
        imgWeapons.add("lghtinGunLeft.png");
        imgWeapons.add("cannonGunRight.png");
        imgWeapons.add("cannonGunLeft.png");
        
        
        mainGun = new Weapon(this.getX(), this.getY(), Weapon.SHOT_GUN, imgWeapons.get(0));
        flameThrower = new Weapon(this.getX(), this.getY(), Weapon.FLAME_THROWER, imgWeapons.get(2));
        sprayGun = new Weapon(this.getX(), this.getY(), Weapon.SPRAY_GUN, imgWeapons.get(4));
        cannonGun = new Weapon(this.getX(), this.getY(), Weapon.CANNON_GUN, imgWeapons.get(6));
        lightningGun = new Weapon(this.getX(), this.getY(), Weapon.LIGHTNING_GUN, imgWeapons.get(8));
        ionGun= new Weapon(this.getX(), this.getY(), Weapon.ION_GUN, imgWeapons.get(10));
        
        weapons.add(mainGun);
        weapons.add(ionGun);
        weapons.add(flameThrower);
        weapons.add(sprayGun);
        weapons.add(lightningGun);
        weapons.add(cannonGun);
        
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
    
    public void addWeapon(Weapon weapon){
        weapons.add(weapon);
    }
    
    public void setLives(int life){
        System.out.println("lives" + lives);
        lives = life;
    }
    
    public int getLives(){
        return lives;
    }
    
    public int getDirection(){
        return direction;
    }
    
    public void setDirection(int newDirection){
        direction = newDirection;
    }
    
    public void setTouchedGround(Boolean bool){
        touchedGround = bool;
    }
    
    @Override
    public void move(){
        super.move();
        weapons.get(weaponIndex).move(this);
    }

    public void keyPressed(KeyEvent e) {
        keyPressed = e.getKeyCode();
        // System.out.println("key: " + keyPressed);
        IMG_WEAPONS_INDEX = weaponIndex * 2;
        int key = e.getKeyCode();
        
        switch(key){
            case KeyEvent.VK_1:
                weaponIndex = 0;
                break;
            case KeyEvent.VK_2:
                weaponIndex = 1;
                break;
            case KeyEvent.VK_3:
                weaponIndex = 2;
                break;
            case KeyEvent.VK_4:
                weaponIndex = 3;
                break;
            case KeyEvent.VK_5:
                weaponIndex = 4;
                break;
            case KeyEvent.VK_6:
                weaponIndex = 5;
                break;
        }
        equipped = weapons.get(weaponIndex);
        System.out.println(equipped.getType());
        if (direction == Player.FACING_LEFT){
            weapons.get(weaponIndex).loadImage(imgWeapons.get(IMG_WEAPONS_INDEX + 1));
        } else if(direction == Player.FACING_RIGHT) {
            weapons.get(weaponIndex).loadImage(imgWeapons.get(IMG_WEAPONS_INDEX));
        }
        
        
        if (key == KeyEvent.VK_ENTER) {
            System.out.println("attempting to fire weapon!");
            weapons.get(weaponIndex).fire(this);
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
            weapons.get(weaponIndex).loadImage(imgWeapons.get(IMG_WEAPONS_INDEX + 1));
            setDirection(FACING_LEFT);
        }

        if (key == KeyEvent.VK_RIGHT) {
            super.setDX(PLAYER_SPEED);
            this.loadImage("AlienRight.png");
            weapons.get(weaponIndex).loadImage(imgWeapons.get(IMG_WEAPONS_INDEX));
            setDirection(FACING_RIGHT);
            System.out.println("weapon index: " + weaponIndex + " weapon image index: " + IMG_WEAPONS_INDEX);
            
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
    
    @Override
    public void doDrawing(Graphics g,Board canvas){
        super.doDrawing(g, canvas);
        for(Weapon weapon : weapons){
            weapon.drawProjectiles(g,canvas);
        }
    }
}