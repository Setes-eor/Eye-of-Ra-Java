/*
 * the startmenu with buttons
 * 
 */
package eye_of_ra;

/**
 *
 * @author setes
 */
public class StartMenu extends Menu{
    
    // constructor
    //
    public StartMenu(String ref, String data, String typ, int x, int y, int resx, int resy, int addy){
        super(ref, data, typ, x,y);
        initButtons(resx, resy, addy);
    }// constructor
    
    // init the Buttons of the StartMenu
    //
    @Override
    public final void initButtons(int x, int y, int addy){
        
        String data = s_datapath;
        addButton(data + "SM_NeuesSpiel" + s_typ, x, y, 1);
        // addButton(data + "Tutorial" + s_typ, x, y + addy,2);
        addButton(data + "SM_SpielLaden" + s_typ, x, y + addy * 2, 3);
        //addButton(data + "SM_Einstellungen" ,x, y + addy * 3, 4);
        addButton(data + "SM_Credits" + s_typ, x,/*715*/ y + addy * 4, 5);
        addButton(data + "SM_SpielBeenden" + s_typ, x,/*845*/ y + addy * 5, 6);
    }// initButtons
}// class StartMenu
