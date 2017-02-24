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

    private int dy;
    // private String[] img = {"mob1.png", "mob2.png", "mob3.png"};
    private String img;
    
    public Mob(int x, int y) {
        super(x, y);
        this.img = "mob1.png";

        initMob();
        
    }
    
    public Mob(int x, int y, String image){         
        super(x, y,image);
        img = image;
        initMob();
        
        
        System.out.println("Making Sprite" + x + y);
        
    }

    private void initMob() {
        
        //loadImage(img[(int)(Math.random() * 2)]);
        loadImage(img);
        
        getImageDimensions();
    }

    public void move() {
        super.move();
        x -= 1;
        
        
    }
    
}