import java.lang.Math;
/**
 * This is the Board class as required ob the Assignment3 
 * 
 * @author  Voicu Chirtes
 * @version 7/7/2014
 */

public class Board 
{
    private GamePiece[][] board;
    
    private static final String EMPTY_CELL = "*";    
    //board specifics, for bigger board the constants below need to be modified
    private static final int MAX_BOARD_ROW=5;
    private static final int MAX_BOARD_COLUMN=5;
    private static final char[] ROW_LABEL = {'1','2','3','4','5'};
    private static final char[] COLUMN_LABEL = {'a','b','c','d','e'};
    
    /**
     * Constructor method
     * 
     * @param   rows, columns   the rows and columns of the board
     */
    public Board(int rows, int columns)
    {
        if(rows<=MAX_BOARD_ROW && columns<=MAX_BOARD_COLUMN) {
            board = new GamePiece[rows][columns];
        }   
        else {
            board = new GamePiece[MAX_BOARD_ROW][MAX_BOARD_COLUMN];
        }
    }

    /**
     * Mutator method for the board using the row and columns values
     * 
     * @param   row, column the row and column coordinates
     * @param   piece   the piece to set at the coordinates
     */
    public boolean setPieceOnBoard(int row, int column, GamePiece piece)
    {
        boolean setSuccess=false;
        if(0<=row && row<board.length && 0<=column && column<board[0].length) {
            board[row][column] = piece;
            setSuccess=true;
        }
        return setSuccess;
    }

    /**
     * Method that counts the total count of pieces for a specific player
     * 
     * @param   player  the player to count the pieces for
     * @return  the total count of pieces that this player has on the board
     */
    public int countPieces(Player player)
    {
        int count = 0;
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[i].length;j++) { 
                if(board[i][j]!=null) {
                    if((board[i][j]).getOwner()==player) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Method that shows the board on the display
     */
    public void showBoard()
    {
        System.out.println("========Board contains:=========");
        String s=" ";
        for(int i=0;i<COLUMN_LABEL.length;i++) {
            s=s+" "+COLUMN_LABEL[i];
        }
        s+='\n';
        for(int i=0;i<board.length;i++) {
            s=s+(i+1)+" "; // to correct
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
        System.out.println(s);
    }

    /**
     * Method that determinets if a location is valid on the current board
     * 
     * @return  wether a location is valid or not on the board
     */
    public boolean isValidLocation(Location l) 
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
     * Method that determinets if the path is clear between two locations
     * 
     * @return  wether the path is clear between two locations
     */
    private boolean clearPath(Location from, Location to) 
    {
        int fromX=from.getX();
        int toX=to.getX();
        int fromY=from.getY();
        int toY=to.getY();
        int piecesInTheWay=0;
        int bigger, smaller;
        if(fromX==toX) {
            int i=Math.min(fromY,toY);
            i++;
            while(i<Math.max(fromY,toY)) {
                if(board[fromX][i++]!=null) {
                    piecesInTheWay++;
                }
            }          
        }
        else if(fromY==toY){
            int i=Math.min(fromX,toX);
            i++;
            while(i<Math.max(fromX,toX)) {
                if(board[i++][fromY]!=null) {
                    piecesInTheWay++;
                }
            }            
        }
        return (piecesInTheWay==0);
    }    

    /**
     * Method that checks if a piece move is possible, if so it moves a piece from
     * the 'from' location into the 'to' location.
     * 
     * @param   from, to the from and to locations
     * @return  wheter the move was successfull
     * @throws  InvalidMoveException custom exception in case the move is not valid
     */
    public boolean movePiece(Location from, Location to, Player current) throws InvalidMoveException
    {
        boolean moveSuccess=false;
        if(!isValidLocation(from)) {
            //System.out.println("Invalid input for source location.");
            throw new InvalidMoveException("Invalid input for source location.");
        }
        else if(!isValidLocation(to)) {
            //System.out.println("Invalid input for destination location.");
            throw new InvalidMoveException("Invalid input for destination location.");
        }        
        else if(isSameLocation(from,to)) {
            //System.out.println("Invalid move, source and destination cannot be the same.");
            throw new InvalidMoveException("Invalid move, source and destination cannot be the same.");
        }
        else if(board[from.getX()][from.getY()]==null) {
            //System.out.println("No piece at the source location.");
            throw new InvalidMoveException("No piece at the source location.");
        }
        else if (current!=((board[from.getX()][from.getY()]).getOwner())) {
            //System.out.println("That's not your piece.");
            throw new InvalidMoveException("That's not your piece.");
        }
        else if((board[to.getX()][to.getY()] != null) && (current==((board[to.getX()][to.getY()]).getOwner()))) {
            //System.out.println("You can't capture your own piece.");
            throw new InvalidMoveException("You can't capture your own piece.");
        }
        else if(!((board[from.getX()][from.getY()]).isLegalMove(from,to))) {
            //System.out.println("Invalid move for this piece.");
            throw new InvalidMoveException("Invalid move for this piece.");
        }
        else if(!clearPath(from,to)) { 
            //System.out.println("Path is not clear.");   
            throw new InvalidMoveException("Path is not clear.");
        }
        else {
            board[to.getX()][to.getY()]=board[from.getX()][from.getY()];
            board[from.getX()][from.getY()]=null;
            moveSuccess=true;
        }           
        return moveSuccess;
    }
}