/*
 * the menu to choose a building
 * 
 */
package eye_of_ra;

/**
 *
 * @author setes
 */
public class BuildMenu extends Menu{

    // constructor
    //
    public BuildMenu(String ref, String data, String typ, int x, int y, int resx, int resy, int addy){
          super(ref, data, typ, 1700 - resx, 170 - resy);
        initButtons(resx, resy, addy);
    }// constructor
    
    // init the Buttons of the StartMenu
    //
    @Override
    public final void initButtons(int x, int y, int addy){
        String data = s_datapath;
        
        addButton(data + "BM_Stufe1" + s_typ, 1733 - x, 280 - y);
        //addButton(data + "BM_Stufe2" + s_typ, 1805,280 - y);
        addButton(data + "BM_Haus" + s_typ, 1733 - x, 410 - y);
        addButton(data + "BM_Strasse" + s_typ, 1805 - x, 410 - y);
        addButton(data + "BM_Markt" + s_typ, 1733 - x, 478 - y);
        //addButton(data + "BM_Tempel", 1805 - x,478 - y);
        addButton(data + "BM_Lager" + s_typ, 1733 - x, 546 - y);
        addButton(data + "BM_Haendler" + s_typ, 1805 - x, 546);
        addButton(data + "BM_Holz" + s_typ, 1733 - x, 614 - y);
        addButton(data + "BM_Palme" + s_typ, 1805 - x, 614 - y);
        //addButton(data + "BM_Grube" + s_typ,1733 - x,682 - y);
        //addButton(data + "BM_Ziegelei" + s_typ,1805 - x,682 - y);
        addButton(data + "BM_Fischer" + s_typ, 1733 - x, 750 - y); 
    }// initButtons   
}// class BuildMenu
