/**
 * This is the GamePiece class as required on the Assignment3 
 * It models a game piece that will be used on the board.
 * 
 * @author  Voicu Chirtes
 * @version 7/7/2014
 */
public abstract class GamePiece
{
    private String pieceDisplay;
    private Player owner;

    /**
     * Constructor GamePiece objects
     */
    public GamePiece(String pieceDisplay, Player owner)
    {
        this.pieceDisplay=pieceDisplay;
        this.owner=owner;
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
     * Accessor method for the owner of the piece
     * 
     * @return  the owner of the piece
     */
    public Player getOwner()
    {
        return owner;
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
    public abstract boolean isLegalMove(Location from, Location to);  
}
