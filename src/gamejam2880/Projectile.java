/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;

import java.awt.Graphics;
import static java.awt.event.KeyEvent.*;

/**
 *
 * @author rober
 */
public class Projectile extends NonStationary {
    
    public Projectile(int x, int y) {
        super(x, y);
        this.loadImage("standardProjectile.png");
        setDX(3);
    }
    
    @Override
    public void doDrawing(Graphics g,Board canvas){
        super.doDrawing(g, canvas);
    }
    
    
}
