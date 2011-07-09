/*
 * the loadmenu with buttons
 * 
 */
package eye_of_ra;

/**
 *
 * @author setes
 */
public class LoadMenu extends Menu {

    // constructor
    //
    public LoadMenu(String ref, String data, String typ, int x, int y, int resx, int resy, int addy) {
        super(ref, data, typ, x, y);
        initButtons(resx, resy, addy);
    }// constructor

    // init the Buttons of the StartMenu
    //
    @Override
    public final void initButtons(int x, int y, int addy) {

        String data = s_datapath;
        addButton(data + "SpM_SpSt1" + s_typ, x, y + addy);
        addButton(data + "SpM_SpSt2" + s_typ, x, y + addy * 2);
        addButton(data + "SpM_SpSt3" + s_typ, x, y + addy * 3);
        addButton(data + "SpM_SpSt4" + s_typ, x, y + addy * 4);
        addButton(data + "SpM_SpSt5" + s_typ, x, y + addy * 5);
    }// initButtons
}// class StartMenu
