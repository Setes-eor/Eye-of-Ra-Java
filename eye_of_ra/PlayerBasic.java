/*
 * base for the players and all types of ai
 * 
 */
package eye_of_ra;

import java.awt.Graphics;
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
    String s_databuild;
    String s_typ;
    HashMap<String,String> hm_buildings;
    
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
        
        initBuildingsData();
    }// constructor
    
    // set a build
    //
    public void addBuild(String data, String builddata, String typ, int x, int y, int energy){
        String[] main = builddata.split(";");
        String path = main[0];
        String[] cost = main[1].split(",");
        String[] money = cost[0].split("=");
        int imoney = Integer.parseInt(money[1]);
        String[] wood = cost[1].split("=");
        int iwood = Integer.parseInt(wood[1]);
        String[] brick = cost[2].split("=");
        int ibrick = Integer.parseInt(brick[1]);

        if(re_resources.EnaughResources(imoney, iwood, ibrick, 0)){
            re_resources.costs(imoney, iwood, ibrick, 0);
            i_persIDBuildings++;
            Buildings build = new Buildings(data + path + typ, x, y, energy, i_persIDBuildings);
            lb_buildings.add(build);
        }// if
        else
            System.out.println("Not enaugh resources for " + path);
    }// addBuild
    
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
            lb_buildings.get(i).setHorizontalMove(x);
        }// for
        for(int i = 0; i < lu_units.size(); i++){
            lb_buildings.get(i).setHorizontalMove(x);
        }// for
    }// horizontalMove
    public void verticalMove(int y){
        for(int i = 0; i < lb_buildings.size(); i++){
            lb_buildings.get(i).setVerticalMove(y);
        }// for
        for(int i = 0; i < lu_units.size(); i++){
            lb_buildings.get(i).setVerticalMove(y);
        }// for
    }// verticalMove
    
    // move all
    //
    public void move(int x){
         for(int i = 0; i < lb_buildings.size(); i++){
            lb_buildings.get(i).move(x);
        }// for
        for(int i = 0; i < lu_units.size(); i++){
            lb_buildings.get(i).move(x);
        }// for
    }// move
    
    // draw of the builds and units
    //
    public void drawBuildUnits(Graphics g){
        for(int i = 0; i < lb_buildings.size(); i++)
            lb_buildings.get(i).Draw(g);
    }// drawBuildUnits
    
    // init the Stats
    //
    private void initStats(){
        hm_playerstats.put("Play", "inactive");
        hm_playerstats.put("Build1", "inactive");
        hm_playerstats.put("Build2", "inactive");
        hm_playerstats.put("Store", "inactive");
        hm_playerstats.put("OnBuild", "inactive");
        
    }// initStats
    
    // set a stat to active
    //
    public void changeStat(String key){
        initStats();
        hm_playerstats.put(key, "active");
    }//changeStat
    
    
    String[] s_sortofBuildings = {"center", "house", "street", "market", "temple", "storage",
        "trader", "lumberjack", "tree", "slimegrave", "brickmaker", "fisher", "milkfarm", "milkfield",
        "center"};
    String[] s_buildImages1 =  {"haupthaus_raster", "wohnhaus", "gerade_rechts", "markt", 
    "temple", "warenhaus", "", "lumber", "palmen_raster", "", "",
    "fischerhaus", "farmhaus", "ziegenfarmfeld",}; 

    // init the HashMap for the data of buildings
    //
    public void initBuildingsData(){
        hm_buildings = new HashMap<String,String>();
        hm_buildings.put("center", "haupthaus_raster;" + "no;");
        hm_buildings.put("house", "wohnhaus;" + "money=0,wood=5,brick=0;");
        hm_buildings.put("street", "gerade_rechts;" + "money=2,wood=0,brick=0;");
        hm_buildings.put("market", "markt;" + "money=75,wood=15,brick=0;");
        hm_buildings.put("temple", "temple;" + "money=65,wood=15,brick=20;");
        hm_buildings.put("storage", "warenhaus;" + "money=75,wood=10,brick=0;");
        hm_buildings.put("trader", " ;" + "money=65,wood=10,brick=0;");
        hm_buildings.put("lumberjack", "lumber;" + "money=50,wood=2,brick=0;");
        hm_buildings.put("tree", "palmen_raster;" + "money=10,wood=0,brick=0;");
        hm_buildings.put("slimegrave", " ;" + "money=10,wood=5,brick=0;");
        hm_buildings.put("brickmaker", " ;" + "money=50,wood=10,brick=0;");
        hm_buildings.put("fisher", "fischerhaus;" + "money=50,wood=10,brick=0;");
        hm_buildings.put("milkfarm", "farmhaus;" + "money=75,wood=10,brick=0;");
        hm_buildings.put("milkfield", "ziegenfarmfeld;" + "money=10,wood=0,brick=0;");  
    }// initBuildingsData
}// class PlayerBasic
