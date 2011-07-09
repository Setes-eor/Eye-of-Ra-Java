/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eye_of_ra;

import java.awt.Graphics;

/**
 *
 * @author setes
 */
public class ResourceBar extends Entity{
    
    // attributes of the class
    //
    BasicEntity be_inhabs;
    
    //constructor
    //
    public ResourceBar(String ref, String ref2, int x, int y){
        super(ref,x,y);
    
        be_inhabs = new BasicEntity(ref2, x, y + 45);
    }//  constructor

    @Override
    public void Draw(Graphics g){
        sp_sprite.Draw(g,(int) d_xPos,(int) d_yPos);
        be_inhabs.Draw(g);
    }// Draw
    
    @Override
    public void collionWidth(Entity e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}// class ResourceBar
