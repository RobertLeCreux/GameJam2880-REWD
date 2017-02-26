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

public class Mob extends NonStationary {
    
    public int hp;
    public static int CLOUD_HP = 10; 
    public static int DRAGON_HP = 16;
    public static int VULTURE_HP = 6;
    public static double MOB_SPAWN_FREQ = 0.2;

    // private String[] img = {"mob1.png", "mob2.png", "mob3.png"};
    private static double MOB_SPAWN_RATE;
    private String img;
    private String type;
    
    public Mob(int x, int y) {
        super(x, y);
        this.img = "mob1.png";
        this.type = this.img;
        
        initMob();
        
    }
    
    public Mob(int x, int y, String image,int health){         
        super(x, y,image);
        img = image;
        hp = health;
        initMob();
        
        
        //System.out.println("Making Sprite" + x + y);
        
    }

    private void initMob() {
        
        //loadImage(img[(int)(Math.random() * 2)]);
        loadImage(img);
        if (this.type == "dragonG.png"){
            
        }
        
        getImageDimensions();
    }

    public void move() {
        super.move();
        dx = -1;
        int direction = (int) Math.floor(Math.random() * 50);
        switch (direction){
            case 0: dx += 1;
                    break;
            case 1: dx -= 1;
                    break;
            case 2: dy -= 5;
                    break;
            case 3: dy -= 1;
                    break;
        }
        
        x += dx;
        
        
    }
    
}