/**
 * This is the Board class as required ob the lab8b homework assignment
 * 
 * @author  Voicu Chirtes
 * @version 6/21/2014
 */

public class Board
{
    private GamePiece[][] board;
    
    private static final int SIZE_X = 4;
    private static final int SIZE_Y = 4;
    private static final String LAB_PIECE_DISPLAY = "X";
    private static final String EMPTY_CELL = "-";
    
    /**
     * Constructor method
     */
    public Board()
    {
        board = new LabPiece[SIZE_X][SIZE_Y];
        populateBoard();
    }
    
    /**
     * Method that populates the board. For now there is only one type of pieces
     * on the board, LabPiece
     */
    public void populateBoard()
    {
        for(int i=0;i<SIZE_X;i++) {
            for(int j=0;j<SIZE_Y;j++) {
                board[i][j]= new LabPiece(LAB_PIECE_DISPLAY,new Location(i,j));
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
         for(int i=0;i<SIZE_X;i++) {
             for(int j=0;j<SIZE_Y;j++) {
                 s=s+board[i][j].getPieceDisplay()+" ";
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
    public boolean isValidLocation(Location l) 
    {
        return (0<=l.getX() && l.getX()<SIZE_X && 0<=l.getY() && l.getY()<SIZE_Y);
    }
    
    /**
     * Method that checks if a piece move is possible, if so it moves a piece from
     * the 'from' location into the 'to' location.
     */
    public void movePiece(Location from, Location to)
    {
        if(isValidLocation(from) && isValidLocation(to)) {
            GamePiece currentPiece = board[from.getX()][from.getY()];
            if(currentPiece.getPieceDisplay()!=EMPTY_CELL){
                if(currentPiece.isLegalMove(from,to)) {
                    board[to.getX()][to.getY()]=currentPiece;
                    board[from.getX()][from.getY()]=new LabPiece(EMPTY_CELL,new Location(from.getX(),from.getY()));
                }
                else {
                    System.out.println("Illegal move");
                }
            }
            else {
                System.out.println("'from' location has no game piece, is empty.");
            }
        }
        else {
            System.out.println("Invalid location");
        }
    }
}