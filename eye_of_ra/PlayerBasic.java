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
    
    // constructor
    //
    public PlayerBasic(){
        hm_playerstats = new HashMap<String,String>();
        changeStat("Play");
    }// constructor
    
    // init the Stats
    //
    private void initStats(){
        hm_playerstats.put("Play", "inactive");
        hm_playerstats.put("Build1", "inactive");
        hm_playerstats.put("Build2", "inactive");
        
    }// initStats
    
    // set a stat to active
    //
    public void changeStat(String key){
        initStats();
        hm_playerstats.put(key, "active");
    }//changeStat
}// class PlayerBasic
