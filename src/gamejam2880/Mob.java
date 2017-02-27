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
    private int mobModifierX;
    private int mobModifierY;
    
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
        System.out.println(image.substring(0, image.length() - 4));
        switch(image.substring(0, image.length() - 4)){
            case "dragonG1":
                mobModifierX = -10;
                mobModifierY = 3;
            case "killerBunny":
                mobModifierX = 100;
                mobModifierY = 1;
            case "vulture":
                mobModifierX = 50;
                mobModifierY = 6;
            case "cloud1":
                mobModifierX = 30;
                mobModifierY = 0;
            default:
                mobModifierX = 10;
                mobModifierY = 1;
        }
        
        //System.out.println("Making Sprite" + x + y);
        
    }

    private void initMob() {
        
        //loadImage(img[(int)(Math.random() * 2)]);
        loadImage(img);
        if (this.type == "dragonG.png"){
            this.setDX(4 + ((int) Math.random() * 4));
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
            case 1: dx -= mobModifierX;
                    break;
            case 2: dy -= mobModifierY * 2;
                    break;
            case 3: dy -= mobModifierY;
                    break;
        }
        
        x += dx;
        
        
    }
    
}