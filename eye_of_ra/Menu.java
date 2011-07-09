/*
 * base for all menus
 * 
 */
package eye_of_ra;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author setes
 */
public abstract class Menu extends Entity{
    
    // attributes of the class
    //
    protected int i_ButtonsID;
    protected List<Button> lb_buttons;
    protected String s_typ;
    protected String s_datapath;
    
    // constructor
    //
    public Menu(String ref, String data, String typ, int x,int y){
        super(ref, x, y);
        i_ButtonsID = 0;
        s_typ = typ;
        s_datapath = data;
        lb_buttons = new ArrayList<Button>();   
    }// 

    // set the buttons of the menu
    //
    protected void addButton(String ref, int x, int y){
        i_ButtonsID++;
        Button button = new Button(ref,x,y, i_ButtonsID);
        
        lb_buttons.add(button);   
    }// setButton
    
    // set the buttons of the menu
    //
    protected void addButton(String ref, int x, int y, int id){
        Button button = new Button(ref,x,y, id);
        
        lb_buttons.add(button);   
    }// setButton
    
    // return the buttonID by clicked on the button
    //
    public int buttonClicked(int x, int y){
        
        for(int i = 0; i < lb_buttons.size(); i++){
            int x1 = (int) lb_buttons.get(i).d_xPos;
            int x2 = ( x1 + (int) lb_buttons.get(i).sp_sprite.getWidth());
            int y1 = (int) lb_buttons.get(i).d_yPos;
            int y2 = ( y1 + (int) lb_buttons.get(i).sp_sprite.getHeight());
            
            if(x >= x1 && x <= x2 && y >= y1 && y <= y2)
                return lb_buttons.get(i).getButtonID();
        }// for
        
        return 0;
    }// buttonClicked
    
    // draw the image of the entity on the screen
    //
    @Override
    public void Draw(Graphics g){
        sp_sprite.Draw(g,(int) d_xPos,(int) d_yPos);   
        drawButtons(g);
    }// Draw
    
    // draw the buttons
    //
    private void drawButtons(Graphics g){
        for(int i = 0; i < lb_buttons.size(); i++){
            lb_buttons.get(i).Draw(g);
        }// for
    }// drawButtons
    
    @Override
    public void collionWidth(Entity e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public abstract void initButtons(int x, int y, int addy);
}// class Menu
