/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eye_of_ra;

/**
 *
 * @author setes
 */
public class OptionMenu extends Menu{
    
    // constructor
    //
    public OptionMenu(String ref, String data, String typ, int x, int y, int resx, int resy, int addy){
        super(ref, data, typ, x,y);
        initButtons(resx, resy, addy);
    }// constructor
    
    // init the Buttons of the StartMenu
    //
    @Override
    public final void initButtons(int x, int y, int addy){
        
        String data = s_datapath;
        addButton(data + "SM_NeuesSpiel" + s_typ, x, y);
        addButton(data + "OM_SpielSpeichern" + s_typ, x, y + addy);
        addButton(data + "SM_SpielLaden" + s_typ, x, y + addy * 2);
        //addButton(data,x, y + addy*3);
        //addButton(data + "SM_Credits" + s_typ, x,/*715*/ y + addy * 4);
        addButton(data + "SM_SpielBeenden" + s_typ, x,/*845*/ y + addy * 5);
    }// initButtons
}// class StartMenu
