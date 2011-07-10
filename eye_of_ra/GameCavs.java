package eye_of_ra;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.HashMap;

/*
 * show the game-field and do the game (game-loop)
 * 
 */

/**
 *
 * @author setes
 */
public class GameCavs extends Canvas implements Runnable, KeyListener, MouseMotionListener{
    
    // attributes of the class
    //
    int i_widthScreen = 1024;
    int i_heightScreen = 768;   
    int i_BMResolutionX;      // build menu resolution
    int i_BMResolutionY;      // build menu resolution
    int i_SBResolutionX;      // background resolution
    int i_SBResolutionY;      // background resolution
    int i_MResolutionX;       // menu resolutions
    int i_MResolutionY;       // menu resolutions
    int i_MoveResolutionX;
    int i_MoveResolutionY;
    int i_ButtonResolutionX;
    int i_ButtonResolutionY;
    int i_ButtonResolutionADDY;
    int i_MapWidth;
    int i_MapHeight;
    int i_mouseX;             // mouse x position on the screen
    int i_mouseY;             // mouse y position on the sceen
    long l_period = 10; 
    
    Player pl_player;
    BufferStrategy bs_Buffer;
    Graphics g;
    Thread t;
    Start st_startScreen;
    Option op_option;
    BasicEntity be_map;
    String s_datapath = "Data/";
    String s_typ = ".gif";
    HashMap<String,String> hm_stats;
    
    // constructor
    //
    public GameCavs(int x, int y){
        i_widthScreen = x;
        i_heightScreen = y;
               
        this.setIgnoreRepaint(true);
        this.setBounds(0, 0, i_widthScreen, i_heightScreen);
        this.setBackground(Color.white);
            
        this.setVisible(true); 
        
        addKeyListener(this);      
        // add mousemotionlistener to get x and y from the mouse
        addMouseMotionListener(this);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseClicked1(evt);
            }
       });
        
        setResolution(i_widthScreen, i_heightScreen);
        
        String Background = "/Anzeigen/BackgroundSmall";
        if(x >= 1920)
            Background = "/Anzeigen/Background";
        
        st_startScreen = new Start(s_datapath, Background, s_typ, i_MResolutionX, i_MResolutionY, 
                i_ButtonResolutionX, i_ButtonResolutionY, i_ButtonResolutionADDY, this, x);
        setStateActive("Start");
        
        //initWorld();
    }// constructor
    
    // start the game and init the buffer
    //
    public void addNotify(){
        super.addNotify();
        this.createBufferStrategy(2);
        this.bs_Buffer = this.getBufferStrategy();
        
        startGame();       
    }// addNotify
    
    // start game thread
    //
    public void startGame(){
        if(t == null){
            t = new Thread(this);
            t.start();
        }// if
    }// startGame
    
    // run the game with update render and draw / the game-loop
    //
    public void run(){
        while(true){
            
            long beginTime = System.currentTimeMillis();
            
            Update();
            Render();
            Draw();
            
            long timeTaken = System.currentTimeMillis();
            long sleepTime = l_period - timeTaken;
            
            try{
                t.sleep(sleepTime);
            }// try
            catch(Exception e){              
            }// catch
        }// while
    }// run
      
    // update all the thinks of the game: for examp. positions, ressources, ....
    //
    public void Update(){
        if((hm_stats.get("Game")) == "active"){
            be_map.move(500);
            pl_player.move(500);
        }// move
    }// Update
    
    // render the entities
    //
    public void Render(){
        g = bs_Buffer.getDrawGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, i_widthScreen, i_heightScreen);
        
        // Paint stuff
        if((hm_stats.get("Start")) == "active"){
            st_startScreen.Draw(g);
        }// if
            
        if((hm_stats.get("Game")) == "active" || (hm_stats.get("Option")) == "active"){
            be_map.Draw(g);
            pl_player.Draw(g);
        }// if
            
        if((hm_stats.get("Option")) == "active"){
            op_option.Draw(g);
        }// if
        
        // show the mousepositions
        // System.out.println("mousepositions: " + i_mouseX + " " + i_mouseY);
    }// Render
    
    // draw with the double-buffer
    //
    public void Draw(){
        if(!bs_Buffer.contentsLost()){
            bs_Buffer.show();
            
            if(g != null){
                g.dispose();
            }// if
        }// if
    }// Draw

    
    public void keyTyped(KeyEvent e){
        
    }// keyTyped
    
    // do something when the mousekey is pressed
    //
    public void mouseClicked1(MouseEvent e){
        if(hm_stats.get("Start") == "active")
            st_startScreen.buttonClicked(i_mouseX, i_mouseY);
        if((hm_stats.get("Option")) == "active")
            op_option.buttonClicked(i_mouseX, i_mouseY);
    }// mouseClicked1
    
    // scroll over the map by mousemovement
    //
    public void mouseMoved(final MouseEvent e) {
        i_mouseX = e.getX();
        i_mouseY = e.getY();
            
        if((hm_stats.get("Game")) == "active" ){
            if (i_mouseX < 10.0) {
                moveHorizontal(15);
            } else if (i_mouseX > 500.0) {
                resetHorizontal();
            }
            if (i_mouseX > 1900.0 - i_MoveResolutionX && be_map.getXPos() > -1050) {
                moveHorizontal(-15);
            } else if (i_mouseX < 1900.0 - i_MoveResolutionX) {
                resetHorizontal();
            }
            if (i_mouseY < 10.0 && be_map.getYPos() < 0) {
                moveVertical(15);
            } else if (i_mouseY > 10.0) {
                resetVertical();
            }
            if (i_mouseY > 1000.0 - i_MoveResolutionY && be_map.getYPos() > -1050) {
                moveVertical(-15);
            } else if (i_mouseY < 1000.0 - i_MoveResolutionY) {
                resetVertical();
            }
        }// if
        
    }// mouseMoved
      
    // do all the thinks, when a key is pressed
    //
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if((hm_stats.get("Start")) == "active")
                st_startScreen.esc();   
            if((hm_stats.get("Option")) == "active")
                op_option.esc();  
        }// if
            
        
        if((hm_stats.get("Game")) == "active"){
            if (e.getKeyCode() == KeyEvent.VK_LEFT && be_map.getXPos() < 0) {
                moveHorizontal(15);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && be_map.getXPos() > -1050) {
                moveHorizontal(-15);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP && be_map.getYPos() < 0) {
                moveVertical(15);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN && be_map.getYPos() > -1050) {
                moveVertical(-15);
            }
            if (e.getKeyCode() == KeyEvent.VK_B) {
                pl_player.openBuildMenu();
            }// if
            if (e.getKeyCode() == KeyEvent.VK_L) {
                pl_player.openStore();
            }// if
            if (e.getKeyCode() == KeyEvent.VK_O) {
                setStateActive("Option");
            }// if
        }// if
        
    }// keyPressed
      
    public void moveHorizontal(int value){
        be_map.setHorizontalMove(value);
        pl_player.horizontalMove(value);
    }// moveUp
    
    public void moveVertical(int value){
        be_map.setVerticalMove(value);
        pl_player.verticalMove(value);
    }// moveDown
    
    public void resetHorizontal(){
        be_map.setHorizontalMove(0);
        pl_player.horizontalMove(0);
    }// resetHorizontal
    
    public void resetVertical(){
        be_map.setVerticalMove(0);
        pl_player.verticalMove(0);
    }// resetVertical
    
    // do all the thinks, when a key is released
    //
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            resetHorizontal();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            resetHorizontal();
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            resetVertical();
        }// if
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            resetVertical();
        }// if     
    }

    
    public void mouseDragged(MouseEvent me) {
        
    }

    // init the stats
    //
    public void initStats(){
        hm_stats = new HashMap<String,String>();
        hm_stats.put("Start", "inactive");
        hm_stats.put("Game", "inactive");
        hm_stats.put("Credits", "inactive");
        hm_stats.put("Load", "inactive");
        hm_stats.put("Option", "inactive");
    }// initStats
    
    // change the vale of the stats
    //
    public void setStateActive(String key){
        initStats();
        hm_stats.put(key, "active");
    }// setStateActive
    
    
    // inits the map and the gamefield
    //
    public void loadWorld(int game){
        
        String mappath = "Map/testmap1" + s_typ;
        if(game == 0){
            mappath = s_datapath + "Map/testmap1" + s_typ;
        }// if
        be_map = new BasicEntity(mappath, 0, 0);
        
        i_MapWidth = be_map.sp_sprite.getWidth();
        i_MapHeight = be_map.sp_sprite.getHeight();
        
        initsPlayer();
        
        
        op_option = new Option(s_datapath, s_typ, i_MResolutionX, i_MResolutionY, 
                i_ButtonResolutionX, i_ButtonResolutionY, i_ButtonResolutionADDY, this, i_widthScreen);
        
        setStateActive("Game");
    }// initWorld()
    
    // inits the player and ai
    //
    public void initsPlayer(){
        pl_player = new Player("P1");
        pl_player.initMenusAndDisplaies("BM_Menu", s_datapath, s_typ, i_MapWidth, 
                i_MapHeight, i_BMResolutionX, i_BMResolutionX,i_ButtonResolutionADDY);
        
    }// initsPlayer
    
    // set the values for all objects addicated by resolution
    //
    public void setResolution(int x, int y){
        switch (x) {
            case (1920):
                i_BMResolutionX = 0;
                i_SBResolutionX = 0;
                i_MResolutionX = 650;
                break;
            case (1680):
                i_BMResolutionX = 240;
                i_SBResolutionX = 328;
                i_MResolutionX = 617;
                break;
            case (1440):
                i_BMResolutionX = 480;
                i_SBResolutionX = 208;
                i_MResolutionX = 497;
                break;
            case (1280):
                i_BMResolutionX = 640;
                i_SBResolutionX = 128;
                i_MResolutionX = 417;
                break;
            case (1240):
                i_BMResolutionX = 680;
                i_SBResolutionX = 108;
                i_MResolutionX = 397;
                break;
            case (1024):
            default:
                i_BMResolutionX = 896;
                i_SBResolutionX = 0;
                i_MResolutionX = 289;
                break;
        }// switch x

        switch (y) {
            case (1080):
                i_BMResolutionY = 0;
                i_SBResolutionY = 0;
                i_MResolutionY = 50;
                break;
            case (900):
                i_BMResolutionY = 124;
                i_SBResolutionY = 50;
                i_MResolutionY = 100;
                break;
            case (800):
            default:
                i_BMResolutionY = 163;
                i_SBResolutionY = 0;
                i_MResolutionY = 50;
                break;
        }// switch y
        
        if (x != 1920) {
            i_ButtonResolutionX = i_MResolutionX + 55;
            i_ButtonResolutionY = i_MResolutionY + 96;
            i_ButtonResolutionADDY = 97;
            /*m_iButtonW = 335;
            m_iButtonH = 78;
            m_sBackgroundfile = "Data/Anzeigen/BackgroundSmall.bmp";
            m_sSMenu = "Data/Menu/Small/SM_Menu_Small.bmp";
            m_sLoadMenu = "Data/Menu/Small/SlM_Menu_Small.bmp";
            m_sSaveMenu = "Data/Menu/Small/SpM_Menu_Small.bmp";
            m_sOptionMenu = "Data/Menu/Small/OM_Menu_Small.bmp";
            m_sCredits = "Data/Anzeigen/Small/Credits_Small.bmp";
            m_sNewGame = "Data/Menu/Small/SM_NewGame_Small.bmp";
            m_sLoad = "Data/Menu/Small/SM_Spielladen_Small.bmp";
            m_sCreditsb = "Data/Menu/Small/SM_Credits_Small.bmp";
            m_sClose = "Data/Menu/Small/SM_Spielbeenden_Small.bmp";
            m_sSave = "Data/Menu/Small/OM_Spielspeichern_Small.bmp";
            m_SSave1 = "Data/Menu/Small/SpM_1_Small.bmp";
            m_SSave2 = "Data/Menu/Small/SpM_2_Small.bmp";
            m_SSave3 = "Data/Menu/Small/SpM_3_Small.bmp";
            m_SSave4 = "Data/Menu/Small/SpM_4_Small.bmp";
            m_SSave5 = "Data/Menu/Small/SpM_5_Small.bmp";*/

        } else {
            i_ButtonResolutionX = i_MResolutionX + 76;
            i_ButtonResolutionY = i_MResolutionY + 133;
            i_ButtonResolutionADDY = 130;
            /*m_iButtonW = 461;
            m_iButtonH = 106;
            m_sBackgroundfile = "Data/Anzeigen/StartBackground.bmp";
            m_sSMenu = "Data/Menu/SM_Menu.bmp";
            m_sLoadMenu = "Data/Menu/SlM_Menu.bmp";
            m_sSaveMenu = "Data/Menu/SpM_Menu.bmp";
            m_sOptionMenu = "Data/Menu/OM_Menu.bmp";
            m_sCredits = "Data/Anzeigen/Credits.bmp";
            m_sNewGame = "Data/Menu/SM_NeuesSpiel.bmp";
            m_sLoad = "Data/Menu/SM_SpielLaden.bmp";
            m_sCreditsb = "Data/Menu/SM_Credits.bmp";
            m_sClose = "Data/Menu/SM_SpielBeenden.bmp";
            m_sSave = "Data/Menu/OM_SpielSpeichern.bmp";
            m_SSave1 = "Data/Menu/SpM_SpSt1.bmp";
            m_SSave2 = "Data/Menu/SpM_SpSt2.bmp";
            m_SSave3 = "Data/Menu/SpM_SpSt3.bmp";
            m_SSave4 = "Data/Menu/SpM_SpSt4.bmp";
            m_SSave5 = "Data/Menu/SpM_SpSt5.bmp";*/
        }
        setMoveResolution(x,y);
    }// setResolution

    // set the resolution for the movement
    //
    public void setMoveResolution(int x, int y) {
        switch (x) {
            case (1920):
                i_MoveResolutionX = 0;
                break;
            case (1680):
                i_MoveResolutionX = 240;
                break;
            case (1440):
                i_MoveResolutionX = 480;
                break;
            case (1280):
                i_MoveResolutionX = 640;
                break;
            case (1240):
                i_MoveResolutionX = 680;
                break;
            case (1024):
            default:
                i_MoveResolutionX = 896;
                break;
        }// switch x

        switch (y) {
            case (1080):
                i_MoveResolutionY = 0;
                break;
            case (900):
                i_MoveResolutionY = 150;
                break;
            case (800):
            default:
                i_MoveResolutionY = 250;
                break;
        }// switch y
    }
}// class GameCavs


