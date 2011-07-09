package eye_of_ra;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/*
 * the base for all visual elements
 * 
 */

/**
 *
 * @author setes
 */
public class Sprite {
    
    BufferedImage im_image;
    
    // constructor
    //
    public Sprite(BufferedImage image){
        this.im_image = image;
    }// constructor
    
    // getter
    public int getWidth(){return im_image.getWidth(null);}
    public int getHeight(){return im_image.getHeight(null);}
    
    // show a subimage
    //
    public void setSubImage(int x, int y, int width, int height){
        System.out.println(x + " " + width);
        im_image = im_image.getSubimage(x, y, width, height);
    }
    // draw the image of the spirte
    //
    public void Draw(Graphics g, int x, int y){
        g.drawImage(im_image, x, y, null);
    }// Draw
}// class Sprite
