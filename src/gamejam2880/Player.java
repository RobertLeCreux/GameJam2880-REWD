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
    public static int PLAYER_SPEED = 10;
    public static int FACING_LEFT = 0;
    public static int FACING_RIGHT = 1;
    public static int WEAPON_Y_OFFSET = -18;
    public static int WEAPON_X_RIGHT_OFFSET = -3;
    public static int WEAPON_X_LEFT_OFFSET = -63;
    
    
    public ArrayList<Weapon> weapons;
    public ArrayList<String> imgWeapons;
    public ArrayList<String> keysPressed;
    protected int weaponIndex;
    public static int IMG_WEAPONS_INDEX;
    
    public int keyPressed;
    private int numKeysPressed;
    
    public static int JUMP_FORCE = 15;
    private boolean touchedGround;
    private Weapon mainGun, flameThrower, sprayGun, cannonGun, lightningGun, ionGun;
    public Weapon equipped;
    private int direction = FACING_RIGHT;
    
    private int lives;
    
    public static final int STARTING_LIVES = 50;
    
    
    
    public Player(int x, int y) {
        super(x, y);
        lives = STARTING_LIVES;
        initPlayer();
        equipped = weapons.get(0);
    }

    private void initPlayer() {
        numKeysPressed = 0;
        // weapons = new ArrayList<>();
        loadImage("AlienRight.png");
        getImageDimensions();
        this.setX((GameJam2880.WINDOW_WIDTH / 18 - this.getWidth() / 2) + 300);
        touchedGround = true;
        weapons = new ArrayList();
        imgWeapons = new ArrayList();
        keysPressed = new ArrayList();
        
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
        //System.out.println("lives" + lives);
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
        numKeysPressed++;
        //System.out.println("key: " + keyPressed);
        
        int key = e.getKeyCode();
        
        if(!keysPressed.contains(KeyEvent.getKeyText(key))){
            keysPressed.add(KeyEvent.getKeyText(key));
        }
        
        //System.out.println(e.getKeyChar());
        //System.out.println((KeyEvent.getKeyText(KeyEvent.VK_ENTER)));
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
        IMG_WEAPONS_INDEX = weaponIndex * 2;
        equipped = weapons.get(weaponIndex);
        //System.out.println(equipped.getType());
        if (direction == Player.FACING_LEFT){
            weapons.get(weaponIndex).loadImage(imgWeapons.get(IMG_WEAPONS_INDEX + 1));
        } else if(direction == Player.FACING_RIGHT) {
            weapons.get(weaponIndex).loadImage(imgWeapons.get(IMG_WEAPONS_INDEX));
        }
        
        //System.out.println(keysPressed.contains("Enter"));
        /*if ((keysPressed.contains("Enter")) && !(this.equipped.equals(Weapon.FLAME_THROWER)))  {
            //System.out.println("attempting to fire weapon!");
            weapons.get(weaponIndex).fire(this);
        }*/
        
        if (key == KeyEvent.VK_SPACE){
            if (touchedGround){
                this.setDY(- (JUMP_FORCE / Physics.GRAVITY));
                touchedGround = false;
                //this.setY(this.getY() - 10);
            }
        }

        
        if (key == KeyEvent.VK_A) {
            this.setDX(-PLAYER_SPEED);
            this.loadImage("AlienLeft.png");
            weapons.get(weaponIndex).loadImage(imgWeapons.get(IMG_WEAPONS_INDEX + 1));
            setDirection(FACING_LEFT);
        }

        if (key == KeyEvent.VK_D) {
            super.setDX(PLAYER_SPEED);
            this.loadImage("AlienRight.png");
            weapons.get(weaponIndex).loadImage(imgWeapons.get(IMG_WEAPONS_INDEX));
            setDirection(FACING_RIGHT);
            //System.out.println("weapon index: " + weaponIndex + " weapon image index: " + IMG_WEAPONS_INDEX);
            
        }
        
        
        
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        keysPressed.remove(KeyEvent.getKeyText(key));
        //System.out.println("Keys Pressed: " + keysPressed.size());

        if (key == KeyEvent.VK_A) {
             setDX(0);
        }
        if(key == KeyEvent.VK_ESCAPE){
            if(Board.paused == true){
                Board.paused = false;
            } else {
                Board.paused = true;
            }
            
        }

        if (key == KeyEvent.VK_D) {
            setDX(0);
        }
        //System.out.println("weapon index: " + weaponIndex + " weapon image index: " + IMG_WEAPONS_INDEX);
        
        switch(key){
            case KeyEvent.VK_1:
                //System.out.println("Switching to weapon: " + equipped.getType());
                break;
            case KeyEvent.VK_2:
                //System.out.println("Switching to weapon: " + equipped.getType());
                //System.out.println("weapon index: " + weaponIndex + " weapon image index: " + IMG_WEAPONS_INDEX);
                break;
            case KeyEvent.VK_3:
                //System.out.println("Switching to weapon: " + equipped.getType());
                //System.out.println("weapon index: " + weaponIndex + " weapon image index: " + IMG_WEAPONS_INDEX);
                break;
            case KeyEvent.VK_4:
                //System.out.println("Switching to weapon: " + equipped.getType());
                //System.out.println("weapon index: " + weaponIndex + " weapon image index: " + IMG_WEAPONS_INDEX);
                break;
            case KeyEvent.VK_5:
                //System.out.println("Switching to weapon: " + equipped.getType());
                //System.out.println("weapon index: " + weaponIndex + " weapon image index: " + IMG_WEAPONS_INDEX);
                break;
            case KeyEvent.VK_6:
                //System.out.println("Switching to weapon: " + equipped.getType());
                //System.out.println("weapon index: " + weaponIndex + " weapon image index: " + IMG_WEAPONS_INDEX);
                break;
        }
        
        if ((key == KeyEvent.VK_ENTER) && !(this.equipped.equals(Weapon.FLAME_THROWER))){
            //System.out.println("attempting to fire weapon!");
            weapons.get(weaponIndex).fire(this);
        }
    }
    
    @Override
    public void doDrawing(Graphics g,Board canvas){
        super.doDrawing(g, canvas);
        
        if (keysPressed.contains("Enter") && this.equipped.getType().equals(Weapon.FLAME_THROWER))  {
            //System.out.println("attempting to fire weapon!");
            weapons.get(weaponIndex).fire(this);
        } else if (keysPressed.contains("Enter") && this.equipped.getType().equals(Weapon.SPRAY_GUN))  {
            //System.out.println("attempting to fire weapon!");
            weapons.get(weaponIndex).fire(this);
        }
        for(Weapon weapon : weapons){
            weapon.drawProjectiles(g,canvas);
        }
        
    }
}