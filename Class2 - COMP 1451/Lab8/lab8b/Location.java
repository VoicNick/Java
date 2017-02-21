/**
 * This is the Location class as required ob the lab8b homework assignment
 * This class has an x-position and a y-position to indicate the coordinates 
 * of a GamePiece on a Board, with appropriate accessor methods.
 * 
 * @author  Voicu Chirtes
 * @version 6/21/2014
 */

public class Location
{
    private int x, y;
    
    /**
     * Constructor method
     * 
     * @param   x   the x posistion
     * @param   y   the y position
     */
    public Location(int x, int y)
    {
        setX(x);
        setY(y);
    }
    
    /**
     * Mutator method for x
     * 
     * @param   x   the x to set
     */
    public void setX(int x)
    {
        if(x>=0) {
            this.x=x;
        }
    }

    /**
     * Mutator method for y
     * 
     * @param   y   the y to set
     */
    
    public void setY(int y) 
    {
        if(y>=0) {
            this.y=y;
        }
    }
    
    /**
     * Accessor method for x
     * 
     * @return  x
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * Accessor method for y
     * 
     * @return  y
     */
    public int getY()
    {
        return y;
    }
}