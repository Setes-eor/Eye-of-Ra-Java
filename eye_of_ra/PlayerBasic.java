/*
 * base for the players and all types of ai
 * 
 */
package eye_of_ra;

import java.util.HashMap;

/**
 *
 * @author setes
 */
public abstract class PlayerBasic {
    
    // attributes of the class
    //
    HashMap<String,String> hm_playerstats;
    Resources re_resources;
    String s_playerid;
    // constructor
    //
    public PlayerBasic(String playerID){
        s_playerid = playerID;
        re_resources = new Resources(playerID);
        re_resources.InitResources(5, 5, 5, 5, 5, 5, 5, 5, 5, 5);
        hm_playerstats = new HashMap<String,String>();
        changeStat("Play");
    }// constructor
    
    // init the Stats
    //
    private void initStats(){
        hm_playerstats.put("Play", "inactive");
        hm_playerstats.put("Build1", "inactive");
        hm_playerstats.put("Build2", "inactive");
        hm_playerstats.put("Store", "inactive");
        
    }// initStats
    
    // set a stat to active
    //
    public void changeStat(String key){
        initStats();
        hm_playerstats.put(key, "active");
    }//changeStat
}// class PlayerBasic
