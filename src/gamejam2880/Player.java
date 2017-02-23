/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends NonStationary {

    
    // private ArrayList<Missile> missiles;

    public Player(int x, int y) {
        super(x, y);

        initPlayer();
    }

    private void initPlayer() {
        
        // missiles = new ArrayList<>();
        loadImage("craft.png");
        getImageDimensions();
    }

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
/*
    public ArrayList getMissiles() {
        return missiles;
    }*/

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        /*
        if (key == KeyEvent.VK_SPACE) {
            fire();
        }
*/
        if (key == KeyEvent.VK_LEFT) {
            this.setDX(-1);
        }

        if (key == KeyEvent.VK_RIGHT) {
            super.setDX(1);
        }

        if (key == KeyEvent.VK_UP) {
            setDX(1);
        }

    }

    /*
    public void fire() {
        missiles.add(new Missile(x + width, y + height / 2));
    }*/

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