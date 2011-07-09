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
    ResourceBar rb_resources;
    ResourceDisplay rd_store;
    
    // constructor
    //
    public Player(String playerID){
        super(playerID);
    }// constructor
    
    // init of menus and displaies
    //
    public void initMenusAndDisplaies(String ref, String data, String typ, int x, int y, int resx, int resy, int addy){
        bm_buildmenu = new BuildMenu(data + "Menu/" + ref + typ, data + "Menu/", typ, x, y, resx, resy, addy);
        rd_store = new ResourceDisplay(data + "Anzeigen/" + "RA_Anzeige" + typ, data + "Anzeigen/", typ, x, y, resx, resy, addy);
        rb_resources = new ResourceBar(data + "Anzeigen/" + "Ressourcenleiste" + typ, data + "Anzeigen/" + "Ressourcenleistes" + typ,
                data, typ, 5, 5);
    }// initMenusAndDisplaies
    
    // draw all the thinks of the player for ex. buildings, menus, ...
    //
    public void Draw(Graphics g){
        if(hm_playerstats.get("Build1") == "active")
            bm_buildmenu.Draw(g);
        if(hm_playerstats.get("Store") == "active")
            rd_store.Draw(g);
        rb_resources.Draw(g, re_resources.getMoney(), re_resources.getWood(),
                re_resources.getBrick(), re_resources.getInhabs());
    }// Draw
    
    // open the buildmenu
    //
    public void openBuildMenu(){
        if(hm_playerstats.get("Play") == "active")
            changeStat("Build1");
        else if(hm_playerstats.get("Build1") == "active")
            changeStat("Play");
    }// openBuildMenu
    
    // open the storagedisplay
    //
    public void openStore(){
        if(hm_playerstats.get("Play") == "active")
            changeStat("Store");
        else if(hm_playerstats.get("Store") == "active")
            changeStat("Play");
    }// openStore
}// PlayerBasic
