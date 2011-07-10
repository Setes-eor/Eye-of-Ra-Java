/*
 * shows the resources of the player
 * 
 */
package eye_of_ra;

/**
 *
 * @author setes
 */
public class ResourceDisplay extends Menu{
  
    int i_resx;
    int i_resy;
    
    // constructor
    //
    public ResourceDisplay(String ref, String data, String typ, int x, int y, int resx, int resy, int addy) {
        super(ref, data, typ, 1700 - resx, 170 - resy);
        initButtons(resx, resy, addy);  
        i_resx = resx;
        i_resy = resy;
    }// constructor

    // init the Buttons of the StartMenu
    //
    @Override
    public final void initButtons(int x, int y, int addy) {
        String data = s_datapath;

        addButtonpNum(data + "RA_Holz" + s_typ, 1733 - x, 380 - y, data, "RA_Zahlen", s_typ, 1749 - x, 420 - y);
        addButtonpNum(data + "RA_Ziegeln" + s_typ, 1805 - x, 380 - y, data, "RA_Zahlen", s_typ, 1822 - x, 420 - y);
        addButtonpNum(data + "RA_Fisch" + s_typ, 1733 - x, 459 - y, data, "RA_Zahlen", s_typ, 1749 - x, 499 - y);
        addButtonpNum(data + "RA_Milch" + s_typ, 1805 - x, 459 - y, data, "RA_Zahlen", s_typ, 1822 - x, 499 - y);
        addButtonpNum(data + "RA_Schlamm" + s_typ, 1733 - x, 538 - y, data, "RA_Zahlen", s_typ, 1749 - x, 578 - y);
    }// initButtons   
    
    
}// class ResourceDisplay
