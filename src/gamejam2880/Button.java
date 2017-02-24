/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;

import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author rober
 */
public class Button extends Sprite implements MouseListener {
    
    private boolean mouseClicked;
    private String image;
    
    public Button(int x, int y, String img) {
        super(x, y, img);
        image = img;
        initButton();
        
    }

    
    
    // -------------------------------------------------- get set
    public boolean getMouseClicked(){
        return mouseClicked;
    }
    
    private void initButton() {
        
        loadImage(image);
        
        getImageDimensions();
        System.out.println("button height: " + height);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
            mouseClicked = true;
            System.out.println(mouseClicked);
        
    }

    @Override
    public  void mousePressed(MouseEvent e) {
        
            mouseClicked = true;
            System.out.println(mouseClicked);
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
            mouseClicked = true;
            System.out.println(mouseClicked);
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
            mouseClicked = true;
            System.out.println(mouseClicked);
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
            mouseClicked = true;
            System.out.println(mouseClicked);
        
    }
    
}
