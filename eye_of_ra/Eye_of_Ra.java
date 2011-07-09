/*
 * the main class
 * 
 */
package eye_of_ra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JFrame;

/**
 *
 * @author setes
 */
public class Eye_of_Ra {

    /**
     * @param args the command line arguments
     */
     public static void main(String args[]) throws FileNotFoundException, IOException{
        
        // init the resolution and fullscreenmode / read in from the configfile
        //
        URL configURL = ClassLoader.getSystemResource("UserConfig/UserConfig.txt");
        URLConnection connect = configURL.openConnection();
        InputStreamReader reader = new InputStreamReader(connect.getInputStream());
        BufferedReader br_file = new BufferedReader(reader);    
        int width = Integer.parseInt(br_file.readLine());
        int height = Integer.parseInt(br_file.readLine());
        boolean fullscreen;
        
        if(br_file.readLine() == "1")
             fullscreen = true;
        else
           fullscreen = false;
        
        // init the game JFrame
        //
        JFrame frame = new JFrame("Spiel");
        
        frame.setIgnoreRepaint(true);
        frame.setBounds(0, 0, width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        // init the game
        //
        GameCavs game = new GameCavs(width, height); 
        frame.add(game);      
        game.setVisible(true);       
    }// main
}// class Eye_of_Ra
