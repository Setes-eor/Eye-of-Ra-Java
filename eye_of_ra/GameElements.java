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
    
    public GameElements(String ref,int x,int y, int energy){
        super(ref,x,y);
        b_isAlive = true;
    }

    @Override
    public void collionWidth(Entity e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }// collisionWidth 
}// class GameElements
