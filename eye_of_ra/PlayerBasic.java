/*
 * base for the players and all types of ai
 * 
 */
package eye_of_ra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author setes
 */
public abstract class PlayerBasic {
    
    // attributes of the class
    //
    List<Buildings> lb_buildings;
    List<Unit> lu_units;
    HashMap<String,String> hm_playerstats;
    Resources re_resources;
    String s_playerid;
    int i_persIDBuildings;
    int i_persIDUnits;
    // constructor
    //
    public PlayerBasic(String playerID){
        s_playerid = playerID;
        re_resources = new Resources(playerID);
        re_resources.InitResources(5, 5, 5, 5, 5, 5, 5, 5, 5, 5);
        hm_playerstats = new HashMap<String,String>();
        lb_buildings = new ArrayList<Buildings>();
        lu_units = new ArrayList<Unit>();
        i_persIDBuildings = 0;
        i_persIDUnits = 0;
        changeStat("Play");
    }// constructor
    
    // set a build
    //
    public void setBuild(String data, String path, String typ, int x, int y, int energy){
        i_persIDBuildings++;
        Buildings build = new Buildings(data + path + typ, x, y, energy, i_persIDBuildings);
        lb_buildings.add(build);
    }// setBuild
    
    // set a unit
    //
    public void setUnit(String data, String path, String typ, int x, int y, int energy){
        i_persIDUnits++;
        Unit unit = new Unit(data + path + typ, x, y, energy, i_persIDUnits);
        lu_units.add(unit);
    }// setUnit
    
    // by scrolling around the map -> move buildings and units
    //
    public void horizontalMove(int x){
        for(int i = 0; i < lb_buildings.size(); i++){
            lb_buildings.get(i);
        }// for
    }// horizontalMove
    public void verticalMove(int y){
        
    }// verticalMove
    
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
