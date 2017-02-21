/**
 * This is the GamePiece class as required on the lab 10 b homework assignment
 * 
 * @author  Voicu Chirtes
 * @version 7/5/2014
 */
public class GamePiece
{
    private String pieceDisplay;

    /**
     * Constructor GamePiece objects
     */
    public GamePiece(String pieceDisplay)
    {
        this.pieceDisplay=pieceDisplay;
    }

    /**
     * Accessor method for the display string
     * 
     * @return  the display string
     */
    public String getPieceDisplay()
    {
        return pieceDisplay;
    }
    
    /**
     * Checks a proposed move to determine if it is legal for 
     * this game piece. A legal move is to either a higher-numbered row 
     * or to a higher-numbered column, or both. Any other move is not legal.
     * 
     * @param   source location
     * @param   destination location
     * @return  true if the proposed move is legal
     */
    public boolean isLegalMove(Location from, Location to)
    {
        return ((from.getX()<=to.getX()) && (from.getY()<=to.getY()));
    }

}
