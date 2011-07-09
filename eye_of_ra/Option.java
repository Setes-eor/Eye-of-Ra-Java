/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eye_of_ra;

import java.awt.Graphics;
import java.util.HashMap;

/**
 *
 * @author setes
 */
public class Option{
    
    // attrubtes of the class
    //
    GameCavs gc_game;
    public OptionMenu op_menu;
    public LoadMenu lm_load;
    public LoadMenu lm_save;
    String s_datapath;
    String s_typ;
    int i_MResolutionX;
    int i_MResolutionY;
    int i_ButtonResolutionX;
    int i_ButtonResolutionY;
    int i_ButtonResolutionADDY;
    int i_screenW;
    HashMap<String,String> hm_stats;
    
    // constructor
    //
    public Option(String datapath, String typ, int mx, int my, int bx, 
            int by, int ba, GameCavs game, int x){       
        i_screenW = x;
        s_datapath = datapath;
        s_typ = typ;
        i_MResolutionX = mx;
        i_MResolutionY = my;
        i_ButtonResolutionX = bx;
        i_ButtonResolutionY = by;
        i_ButtonResolutionADDY = ba;
        
        hm_stats = new HashMap<String,String>();
        
        gc_game = game;
        initStats();
        initMenus();
        changeStat("Option");
    }// constructor

    // init the startMenu
    //
    private void initMenus(){
        
        s_datapath += "Menu/";
        if(i_screenW < 1920){
            s_datapath += "Small/";
        }// if
        
        op_menu = new OptionMenu(s_datapath + "SM_Menu" + s_typ, s_datapath, s_typ,
                i_MResolutionX, i_MResolutionY, i_ButtonResolutionX, i_ButtonResolutionY,
                i_ButtonResolutionADDY);
        lm_load = new LoadMenu(s_datapath + "SlM_Menu" + s_typ, s_datapath, s_typ,
                i_MResolutionX, i_MResolutionY, i_ButtonResolutionX, i_ButtonResolutionY,
                i_ButtonResolutionADDY);
        lm_save = new LoadMenu(s_datapath + "SpM_Menu" + s_typ, s_datapath, s_typ,
                i_MResolutionX, i_MResolutionY, i_ButtonResolutionX, i_ButtonResolutionY,
                i_ButtonResolutionADDY);
    }//initStartMenu
    
    // init the Stats
    //
    private void initStats(){
        hm_stats.put("Option", "inactive");
        hm_stats.put("Save", "inactive");
        hm_stats.put("Load", "inactive");
        hm_stats.put("Credits", "inactive");
    }// initStats
    
    // set a stat to active
    //
    public void changeStat(String key){
        initStats();
        hm_stats.put(key, "active");
    }//changeStat
    
    // get the button id by mouseclick
    //
    public void buttonClicked(int x, int y){
        int bid = op_menu.buttonClicked(x, y);
        if(bid == 1)
            gc_game.loadWorld(0);
        else if(bid == 2)
            changeStat("Save");
        else if(bid == 3)
            changeStat("Load");
        else if(bid == 5)
            gc_game.setStateActive("Start");
        else if(bid == 6)
            System.exit(0);
        
    }// buttonClicked
    
    // change the stat or end the game by enter the esc key
    //
    public void esc(){
        if(hm_stats.get("Option") == "active")
            System.exit(0);
        else
            changeStat("Option");
    }// esc
    
    // draw the image of the entity on the screen
    //
    public void Draw(Graphics g){  
        if(hm_stats.get("Option") == "active")
            op_menu.Draw(g);  
        else if(hm_stats.get("Load") == "active")
            lm_load.Draw(g);
        else if(hm_stats.get("Save") == "active")
            lm_save.Draw(g);        
        else if(hm_stats.get("Credits") == "active")
            op_menu.Draw(g);
    }// Draw 
}// class start
