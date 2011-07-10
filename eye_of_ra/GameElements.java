/*
 * base class for units and buildings
 * .
 */
package eye_of_ra;

/**
 *
 * @author setes
 */
public class GameElements extends Entity{
    
    // attribues of the class
    //
    private int i_energy;      // live-energy of units and buildings
    private boolean b_isAlive; // is the object actualy alive
    private int i_persID;
    
    public GameElements(String ref,int x,int y, int energy, int persID){
        super(ref,x,y);
        b_isAlive = true;
        i_persID = persID;
    }

    // set the alive of a element
    //
    public void setAlive(boolean alive){b_isAlive = alive;}// setAlive
    
    @Override
    public void collionWidth(Entity e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }// collisionWidth 
}// class GameElements
