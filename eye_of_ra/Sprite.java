package eye_of_ra;


import java.awt.Graphics;
import java.awt.Image;

/*
 * the base for all visual elements
 * 
 */

/**
 *
 * @author setes
 */
public class Sprite {
    
    Image im_image;
    
    // constructor
    //
    public Sprite(Image image){
        this.im_image = image;
    }// constructor
    
    // getter
    public int getWidth(){return im_image.getWidth(null);}
    public int getHeight(){return im_image.getHeight(null);}
    
    // draw the image of the spirte
    //
    public void Draw(Graphics g, int x, int y){
        g.drawImage(im_image, x, y, null);
    }// Draw
}// class Sprite
