
package gamejam2880;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Gen
 */
public class SpriteShadow {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private Rectangle rect;
    private int height;
    private int width;
    
    public SpriteShadow(int sX, int sY, int sDx,int sDy,Rectangle rectangle){
        setX(sX);
        setY(sY);
        setDX(sDx);
        setDY(sDy);
        rect = rectangle;
        height = (int) rectangle.getHeight();
        width = (int) rectangle.getWidth();
    }
    
    public void setX (int xpos){
        x = xpos;
    }
    
    public int getX(){
        return x;
    }
    
    public void setY(int ypos){
        x = ypos;
    }
    
    public int getY(){
        return y;
    }
    
    public void setDX(int deltaX){
        dx = deltaX;
    }
    
    public int getDX(){
        return dx;
    }
    
    public void setDY(int deltaY){
        dy = deltaY;
    }
    
    public int getDY(){
        return dy;
    }
    
    public Rectangle getRect(){
        return rect;
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getWidth(){
        return width;
    }
}
