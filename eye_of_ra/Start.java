/*
 * startscreen of the game with credits, load, ...
 * 
 */
package eye_of_ra;

import java.awt.Graphics;
import java.util.HashMap;

/**
 *
 * @author setes
 */
public class Start extends Entity{
    
    // attrubtes of the class
    //
    GameCavs gc_game;
    public StartMenu sm_start;
    public LoadMenu lm_load;
    String s_datapath;
    String s_typ;
    BasicEntity be_credits;
    int i_MResolutionX;
    int i_MResolutionY;
    int i_ButtonResolutionX;
    int i_ButtonResolutionY;
    int i_ButtonResolutionADDY;
    int i_screenW;
    HashMap<String,String> hm_stats;
    
    // constructor
    //
    public Start(String datapath, String background, String typ, int mx, int my, int bx, 
            int by, int ba, GameCavs game, int x){   
        super(datapath + background + typ ,0,0);
        
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
        changeStat("StartMenu");
    }// constructor

    // init the startMenu
    //
    private void initMenus(){
        
        s_datapath += "Menu/";
        if(i_screenW < 1920){
            s_datapath += "Small/";
        }// if
        
        sm_start = new StartMenu(s_datapath + "SM_Menu" + s_typ, s_datapath, s_typ,
                i_MResolutionX, i_MResolutionY, i_ButtonResolutionX, i_ButtonResolutionY,
                i_ButtonResolutionADDY);
        lm_load = new LoadMenu(s_datapath + "SlM_Menu" + s_typ, s_datapath, s_typ,
                i_MResolutionX, i_MResolutionY, i_ButtonResolutionX, i_ButtonResolutionY,
                i_ButtonResolutionADDY);
        be_credits = new BasicEntity(s_datapath + "Credits" + s_typ, i_MResolutionX, i_MResolutionY);
    }//initStartMenu
    
    // init the Stats
    //
    private void initStats(){
        hm_stats.put("StartMenu", "inactive");
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
        int bid = sm_start.buttonClicked(x, y);
        if(bid == 1)
            gc_game.loadWorld(0);
        if(bid == 2)
            changeStat("Load");
        if(bid == 3)
            changeStat("Credits");
        else if(bid == 4)
            System.exit(0);
        
    }// buttonClicked
    
    // change the stat or end the game by enter the esc key
    //
    public void esc(){
        if(hm_stats.get("StartMenu") == "active")
            System.exit(0);
        else
            changeStat("StartMenu");
    }// esc
    
    // draw the image of the entity on the screen
    //
    @Override
    public void Draw(Graphics g){
        sp_sprite.Draw(g,(int) d_xPos,(int) d_yPos);   
        if(hm_stats.get("StartMenu") == "active")
            sm_start.Draw(g);  
        else if(hm_stats.get("Load") == "active")
            lm_load.Draw(g);
        else if(hm_stats.get("Credits") == "active")
            be_credits.Draw(g);
    }// Draw
    
    @Override
    public void collionWidth(Entity e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}// class start
