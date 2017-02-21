/**
 * This is the Ball is a type of GamePiece as required ob the Assignment3
 * 
 * @author  Voicu Chirtes
 * @version 7/7/2014
 */

public class Ball extends GamePiece
{        
    private static final int MOVEMENT_RANGE = 1;
    /**
     * Constructor method
     * 
     * @param   pieceDisplay    the String display of the piece on the board
     */
    public Ball(String pieceDisplay, Player owner)
    {
        super(pieceDisplay, owner);
    }
    
    /**
     * Implementation of the abstract method that determines if the move is valid
     */
    public boolean isLegalMove(Location from, Location to)
    {
        return ((Math.abs(from.getX()-to.getX())==MOVEMENT_RANGE) && (from.getY()==to.getY()));
    }
}