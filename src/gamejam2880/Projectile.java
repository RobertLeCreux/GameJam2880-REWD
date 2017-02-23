/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;

import static java.awt.event.KeyEvent.*;

/**
 *
 * @author rober
 */
public class Projectile extends NonStationary {
    
    public Projectile(int x, int y) {
        super(x, y);
        setDX(3);
    }
    
    
}
