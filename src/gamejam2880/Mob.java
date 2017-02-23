/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;

public class Mob extends Sprite {

    private int dy;
    private String[] img = {"mob1.png", "mob2.png", "mob3.png"};

    public Mob(int x, int y) {
        super(x, y);

        initMob();
        move();
    }

    private void initMob() {
        
        loadImage(img[(int)(Math.random() * 2)]);
        getImageDimensions();
    }

    public void move() {

        x -= 1;
        y += dy;

    }
    
}