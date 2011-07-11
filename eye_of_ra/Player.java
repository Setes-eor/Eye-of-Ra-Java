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
    BasicEntity be_buildCurser;
    BuildMenu bm_buildmenu;
    ResourceBar rb_resources;
    ResourceDisplay rd_store;
    BasicEntity be_dragBuild;
    String s_databuild;
    String s_typ;
    String[] s_buildImages1 = {"haupthaus_raster", "wohnhaus", "gerade_rechts", "markt", 
    "temple", "warenhaus", "", "lumber", "palmen_raster", "farmhaus", "ziegenfarmfeld",
    "fischerhaus"};
    
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
        s_databuild = data + "Buildings/";
        s_typ = typ;
    }// initMenusAndDisplaies
    
    // init the  buildcurser
    //
    public void initBuildCurser(String ref, int x, int y){
        String typ = ".bmp";
        if(ref == "buildcurserplank")
            typ = s_typ;
        
        be_buildCurser = new BasicEntity(s_databuild + ref + typ, x, y);
    }// initPlankBuildCurser
    
    // move teh buildcurser with the mouse
    //
    public void movebuildCurser(int x, int y){
        if(hm_playerstats.get("OnBuild") == "active")
            if(be_buildCurser != null){
                be_buildCurser.setXPos(x);
                be_buildCurser.setYPos(y);
            }// if         
    }// movebuildCurser
    
    // draw all the thinks of the player for ex. buildings, menus, ...
    //
    public void Draw(Graphics g){
        if(hm_playerstats.get("Build1") == "active"
                || hm_playerstats.get("OnBuild") == "active"){
            bm_buildmenu.Draw(g);
        }// if
        
        if(hm_playerstats.get("OnBuild") == "active")
                if(be_buildCurser != null)
                    be_buildCurser.Draw(g);
        
        if(hm_playerstats.get("Store") == "active")
            rd_store.DrawNumButtons(g, re_resources.getWood(), re_resources.getBrick(),
                     re_resources.getFishes(),  re_resources.getMilk(),  re_resources.getSlime());
        rb_resources.Draw(g, re_resources.getMoney(), re_resources.getWood(),
                re_resources.getBrick(), re_resources.getInhabs());
    }// Draw
    
    // open the buildmenu
    //
    public void openBuildMenu(){
        if(hm_playerstats.get("Play") == "active")
            changeStat("Build1");
        else if(hm_playerstats.get("Build1") == "active" 
                || hm_playerstats.get("OnBuild") == "active")
            changeStat("Play");
    }// openBuildMenu
    
    // do somthing when the mouse is clicked
    //
    public void mouseClicked(String mouse, int x, int y){
        if(mouse == "left"){
            if(hm_playerstats.get("Build1") == "active" 
                    || hm_playerstats.get("OnBuild") == "active"){
                this.changeStat("OnBuild");
                int buttonid = bm_buildmenu.buttonClicked(x, y);
                if(buttonid > 0)
                    initBuildCurser(s_buildImages1[buttonid], x, y);
            }// 
        }// if
        if(mouse == "right"){
            if(hm_playerstats.get("OnBuild") == "active")
                // muss wegen build2 noch geändert werden !!!!!!!!!!!!!!!!!!1111
                this.changeStat("Build1");
        }// if
    }// mouseClicked
    
    // open the storagedisplay
    //
    public void openStore(){
        if(hm_playerstats.get("Play") == "active")
            changeStat("Store");
        else if(hm_playerstats.get("Store") == "active")
            changeStat("Play");
    }// openStore
}// PlayerBasic
