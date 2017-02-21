/**
 * This is the Torch is a type of GamePiece as required ob the Assignment3
 * 
 * @author  Voicu Chirtes
 * @version 7/7/2014
 */

public class Torch extends GamePiece
{        
    /**
     * Constructor method
     * 
     * @param   pieceDisplay    the String display of the piece on the board
     */
    public Torch(String pieceDisplay, Player owner)
    {
        super(pieceDisplay, owner);
    }
    
    /**
     * Implementation of the abstract method that determines if the move is valid
     */
    public boolean isLegalMove(Location from, Location to)
    {
        return ((Math.abs(from.getX()-to.getX())>0) && (from.getY()==to.getY())) || ((Math.abs(from.getY()-to.getY())>0) && (from.getX()==to.getX()));
    }
}