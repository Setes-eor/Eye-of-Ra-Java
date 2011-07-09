/*
 * class for the Player
 * 
 */
package eye_of_ra;

import java.awt.Graphics;

/**
 *
 * @author setes
 */
public class Player extends PlayerBasic{
    
    // attributes of the class
    //
    BuildMenu bm_buildmenu;
    
    // constructor
    //
    public Player(){
        super();
    }// constructor
    
    // init of the BuildMenu
    public void initBuildMenu(String ref, String data, String typ, int x, int y, int resx, int resy, int addy){
        bm_buildmenu = new BuildMenu(data + ref + typ, data, typ, x, y, resx, resy, addy);
    }// initBuildMenu
    
    // draw all the thinks of the player for ex. buildings, menus, ...
    //
    public void Draw(Graphics g){
        if(hm_playerstats.get("Build1") == "active")
            bm_buildmenu.Draw(g);
    }// Draw
    
    // open the buildmenu
    //
    public void openBuildMenu(){
        if(hm_playerstats.get("Play") == "active")
            changeStat("Build1");
        else
            changeStat("Play");
    }// openBuildMenu
}// PlayerBasic
