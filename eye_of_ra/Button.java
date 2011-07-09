/*
 * image of a button
 * 
 */
package eye_of_ra;

/**
 *
 * @author setes
 */
public class Button extends Entity{
    
    // attributes of the class
    //
    int i_ButtonID;
    // constructor
    //
    public Button(String ref, int x, int y, int id){
        super(ref,x,y);
        i_ButtonID = id;
    }// // constructor

    // returns the ButtonID
    //
    public int getButtonID(){return i_ButtonID;}
    
    @Override
    public void collionWidth(Entity e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}// class Button
