/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Robert LeCreux
 */
public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean vis;
    protected Image image;
    private String img;

    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        vis = true;
        
    }
    public Sprite(int x, int y, String image) {

        this.x = x;
        this.y = y;
        img = image;
        vis = true;
        loadImage(img);
    }

    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
        getImageDimensions();
    }
    
    public Image getImage() {
        return image;
    }
    
    public void setX(int var){
        x = var;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setY(int var){
        y = var;
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getWidth(){
        return width;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    public void doDrawing(Graphics g,Board canvas){
        Graphics2D g2d = (Graphics2D) g;
        Color color = new Color(244,0,104,1);
        g2d.setPaint(color);
        g2d.drawImage(this.getImage(), this.getX(), this.getY(), canvas);
    }
    public void doDrawing(Graphics g,Menu canvas){
        Graphics2D g2d = (Graphics2D) g;
        Color color = new Color(244,0,104,1);
        g2d.setPaint(color);
        g2d.drawImage(this.getImage(), this.getX(), this.getY(), canvas);
    }
}