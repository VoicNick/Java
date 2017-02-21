/**
 * This is the LabPiece class as required ob the lab8b homework assignment
 * 
 * @author  Voicu Chirtes
 * @version 6/21/2014
 */

public class LabPiece extends GamePiece
{        
    /**
     * Constructor method
     * 
     * @param   pieceDisplay    the String display of the piece on the board
     * @param   location    the location of the piece
     */
    public LabPiece(String pieceDisplay, Location location)
    {
        super(pieceDisplay,location);
    }
    
    /**
     * Implementation of the abstract method that determines if the move is valid
     */
    public boolean isLegalMove(Location a, Location b)
    {
        return ((a.getX()<b.getX())||(a.getY()<b.getY()));
    }
}