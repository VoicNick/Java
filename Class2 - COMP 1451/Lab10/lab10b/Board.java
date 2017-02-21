/**
 * This is the Board class as required ob the lab10b homework assignment
 * 
 * @author  Voicu Chirtes
 * @version 7/5/2014
 */

public class Board 
{
    private GamePiece[][] board;

    private static final int SIZE_X = 4;
    private static final int SIZE_Y = 4;
    private static final String LAB_PIECE_DISPLAY = "*";
    private static final String EMPTY_CELL = "-";

    /**
     * Constructor method
     */
    public Board()
    {
        board = new GamePiece[SIZE_X][SIZE_Y];
        populateBoard();
    }

    /**
     * Method that populates the board. For now there is only one type of pieces
     * on the board, GamePiece
     */
    private void populateBoard()
    {
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[i].length;j++) {
                board[i][j]= new GamePiece(LAB_PIECE_DISPLAY);
            }
        }
    }

    /**
     * Method that returns a string containing the display of the board
     * 
     * @return  the string containing the display of the board
     */
    public String toString()
    {
        String s = "";
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[i].length;j++) {
                if(board[i][j]==null) {
                    s=s+EMPTY_CELL+" ";
                }
                else {
                    s=s+board[i][j].getPieceDisplay()+" ";
                }
            }
            s+="\n";
        }
        return s;
    }

    /**
     * Method that shows the board on the display
     */
    public void showBoard()
    {
        System.out.println(this);
    }

    /**
     * Method that determinets if a location is valid on the current board
     * 
     * @return  wether a location is valid or not on the board
     */
    private boolean isValidLocation(Location l) 
    {
        return (0<=l.getX() && l.getX()<board.length && 0<=l.getY() && l.getY()<board[0].length);
    }

    /**
     * Method that determinets if two locations are the same
     * 
     * @return  wether two locations are the same
     */
    private boolean isSameLocation(Location from, Location to) 
    {
        return (from.getX()==to.getX() && from.getY()==to.getY());
    }

    /**
     * Method that checks if a piece move is possible, if so it moves a piece from
     * the 'from' location into the 'to' location.
     * 
     * @param   from, to the from and to locations
     * @throws  InvalidMoveException custom exception in case the move is not valid
     */
    public void movePiece(Location from, Location to) throws InvalidMoveException
    {
        if(!isValidLocation(from) || !isValidLocation(to)) {
            //System.out.println("Invalid location");
            throw new InvalidMoveException("Invalid location");
        }
        else if(isSameLocation(from,to)) {
            //System.out.println("Source and destination are same location");
            throw new InvalidMoveException("Source and destination are same location");
        }
        else if(board[from.getX()][from.getY()]==null) {
            //System.out.println("'from' location has no game piece, is empty.");
            throw new InvalidMoveException("'from' location has no game piece, is empty.");
        }
        else if(!((board[from.getX()][from.getY()]).isLegalMove(from,to))) {
            //System.out.println("Invalid move");
            throw new InvalidMoveException("Invalid move");
        }
        else {
            board[to.getX()][to.getY()]=board[from.getX()][from.getY()];
            board[from.getX()][from.getY()]=null;
        }           
    }
}