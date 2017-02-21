/**
 * This is the GamePiece class as required on the lab8b homework assignment
 * 
 * @author  Voicu Chirtes
 * @version 6/21/2013
 */

public abstract class GamePiece
{
    private String pieceDisplay;
    private Location location;
    
    /**
     * Constructor method
     * 
     * @param   pieceDisplay    the display string for this piece
     * @param   location        the location of this piece
     */
    public GamePiece(String pieceDisplay, Location location)
    {
        setPieceDisplay(pieceDisplay);
        setLocation(location);
    }
    
    /**
     * Mutator method for the pieceDisplay field
     * 
     * @param   pieceDisplay    the display string for this piece
     */
    public void setPieceDisplay(String pieceDisplay)
    {
        if(pieceDisplay!=null) {
            this.pieceDisplay=pieceDisplay;
        }
    }
    
    /**
     * Mutator method for the location field
     * 
     * @param   location    the location of this piece on the board
     */
    public void setLocation(Location location)
    {
        if(location!=null) {
            this.location=location;
        }
    }
    
    /**
     * Accessor method for the pieceDisplay field
     * 
     * @return  the pieceDisplay, the display string for this piece
     */
    public String getPieceDisplay()
    {
        return pieceDisplay;
    }
    
    /**
     * Accessor method for the location field
     * 
     * @return  the location of this piece
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Method that determinsed if a move is valid. To be implemented by the 
     * subclasses individually.
     */
    public abstract boolean isLegalMove(Location a, Location b);
}