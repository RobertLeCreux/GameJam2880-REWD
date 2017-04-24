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
    
    //--------------------------------------------------------- class constants
    public static int NO_COLLISION = 0;
    public static int COLLISION_TOP = 1;
    public static int COLLISION_BOTTOM = 2;
    public static int COLLISION_RIGHT = 3;
    public static int COLLISION_LEFT = 4;
    public static int COLLISION_INDETERMINATE = 5;
    
    //--------------------------------------------------------- object attributes
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected int width;
    protected int height;
    protected boolean vis;
    protected Image image;
    private String img;

    //--------------------------------------------------------- standard constructor
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
    
    
    public int getDX(){
        return dx;
    }
    public void setDX(int deltaX){
        dx = deltaX;
    }
    
    public int getDY(){
        return dy;
    }
    public void setDY(int deltaY){
        dy = deltaY;
    }
    
    public SpriteShadow getShadow(){
        return new SpriteShadow(getX(),getY(),getDX(),getDY(),getBounds());
    }
    
    
    public int detectCollision(Rectangle obj){
        Rectangle thisHitBox = this.getBounds();
        Rectangle thisHitBoxMovedUp = this.getBounds();
        thisHitBoxMovedUp.setLocation(this.getX(), this.getY() - this.getDY());
        Rectangle thisHitBoxMovedDown = this.getBounds();
        thisHitBoxMovedDown.setLocation(this.getX(), this.getY() + this.getDY());
        Rectangle thisHitBoxMovedRight = this.getBounds();
        thisHitBoxMovedRight.setLocation(this.getX() + this.getDX(), this.getY());
        Rectangle thisHitBoxMovedLeft = this.getBounds();
        thisHitBoxMovedLeft.setLocation(this.getX() - this.getDX(), this.getY());
        if (this.getBounds().intersects(obj) && !(thisHitBoxMovedUp.intersects(obj))){
            return COLLISION_BOTTOM;
        } else if(this.getBounds().intersects(obj) && !(thisHitBoxMovedDown.intersects(obj))){
            return COLLISION_TOP;
        } else if (this.getBounds().intersects(obj) && !(thisHitBoxMovedRight.intersects(obj))){
            return COLLISION_LEFT;
        } else if (this.getBounds().intersects(obj) && !(thisHitBoxMovedLeft.intersects(obj))){
            return COLLISION_RIGHT;
        } else if (this.getBounds().intersects(obj)){
            return COLLISION_INDETERMINATE;
        } else{
            return NO_COLLISION;
        }
    }
    
    /*
    public int detectCollision(Rectangle obj){
        Rectangle thisHitBox = getBounds();
        
        if (!thisHitBox.intersects(obj)){
            return NO_COLLISION;
        } else if (thisHitBox.intersects(obj) && clearsTop(obj) && clearsBottom(obj) && clearsRight(obj) && clearsLeft(obj)){
            System.out.println("Broken Physics!");
            return NO_COLLISION;
        } else if (thisHitBox.intersects(obj) && !clearsTop(obj) && clearsBottom(obj) && clearsRight(obj) && clearsLeft(obj)){
            return COLLISION_TOP;
        } else if (thisHitBox.intersects(obj) && clearsTop(obj) && !clearsBottom(obj) && clearsRight(obj) && clearsLeft(obj)){
            return COLLISION_BOTTOM;
        } else if (thisHitBox.intersects(obj) && clearsTop(obj) && clearsBottom(obj) && !clearsRight(obj) && clearsLeft(obj)){
            return COLLISION_RIGHT;
        } else if (thisHitBox.intersects(obj) && clearsTop(obj) && clearsBottom(obj) && clearsRight(obj) && clearsLeft(obj)){
            return COLLISION_LEFT;
        } else{
            System.out.println("Broken Phycis!! " + clearsTop(obj) + clearsBottom(obj) + clearsRight(obj) + clearsLeft(obj) + this.getDY());
            return NO_COLLISION;
        }        
    }
    
    public boolean clearsTop(Rectangle obj){
        Rectangle thisHitBox = this.getBounds();
        thisHitBox.setLocation((int) thisHitBox.getX(),(int) thisHitBox.getY() - this.getDY() * 4);
        return !thisHitBox.intersects(obj);        
    }
    
    public boolean clearsBottom(Rectangle obj){
        Rectangle thisHitBox = this.getBounds();
        thisHitBox.setLocation((int) thisHitBox.getX(),(int) thisHitBox.getY() + this.getDY());
        return !thisHitBox.intersects(obj);        
    }
    
    public boolean clearsRight(Rectangle obj){
        Rectangle thisHitBox = this.getBounds();
        thisHitBox.setLocation((int) thisHitBox.getX() + this.getDX(),(int) thisHitBox.getY());
        return !thisHitBox.intersects(obj);        
    }
    
    public boolean clearsLeft(Rectangle obj){
        Rectangle thisHitBox = this.getBounds();
        thisHitBox.setLocation((int) thisHitBox.getX() - this.getDX(),(int) thisHitBox.getY());
        return !thisHitBox.intersects(obj);        
    }
    */
    
    public void doDrawing(Graphics g,Board canvas){
        Graphics2D g2d = (Graphics2D) g;
        Color color = new Color(244,0,104,1);
        g2d.setPaint(color);
        g2d.drawImage(this.getImage(), this.getX(), this.getY(), canvas);
    }
    public void doDrawing(Graphics g, MenuBetter canvas, int i){
        Graphics2D g2d = (Graphics2D) g;
        Color color = new Color(244,0,104,1);
        g2d.setPaint(color);
        g2d.drawImage(this.getImage(), this.getX(), this.getY(), canvas);
    }
}