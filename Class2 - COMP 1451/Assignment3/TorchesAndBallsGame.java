/**
 * This is the game logic class as required on the Assignment3 
 * 
 * @author  Voicu Chirtes
 * @version 7/12/2014
 */

public class TorchesAndBallsGame
{
    private Board board;
    private Player player1, player2, currentPlayer;
    private int player1Wins, player2Wins,rounds;
    private InputReader reader;    

    private static final int BOARD_ROWS = 5;
    private static final int BOARD_COLUMNS = 5;
    private static final int VALID_COMMAND_LENGTH=5;
    private static final int VALID_COMMAND_LENGTH_LOCATION=2;
    private static final int NEGATIVE_OUT_OF_BOARD_VALUE=-1;
    private static final String QUIT_COMMAND="q";
    private static final String EXIT_COMMAND="no";
    private static final int COMMAND_CHAR_1=0;
    private static final int COMMAND_CHAR_2=1;
    private static final int COMMAND_CHAR_3=3;
    private static final int COMMAND_CHAR_4=4;    
    private static final char[] ROW_LABEL = {'1','2','3','4','5'};
    private static final char[] COLUMN_LABEL = {'a','b','c','d','e'};

    /**
     * Default constructor
     */
    public TorchesAndBallsGame()
    {        
        reader = new InputReader();
    }    

    /**
     * The main method that contains the game logic.
     * Call this method ot start a new game.
     * 
     * @throws  InvalidMoveException custom exception in case the move is not valid
     */
    public void play() // throws InvalidMoveException    
    {
        // showWelcome(); to be implemented
        // showInstructions(); to be implemented
        setupPlayers();
        Boolean exitGame=false;
        rounds=0;
        while(!exitGame) {
            setupBoard();
            rounds++;
            currentPlayer=player1;            
            Boolean roundFinished=false;            
            while(!roundFinished) {
                board.showBoard();
                showPlayerPieces();                
                System.out.println(currentPlayer+" enter your move (q to quit round): > ");                    
                String command=reader.readString();
                command=command.trim();
                char fromRow, fromColumn, toRow, toColumn;
                Location from, to;
                if(command!=null) {
                    if(command.equalsIgnoreCase(QUIT_COMMAND)) {
                        roundFinished=true;
                        System.out.println(currentPlayer+" quits this round.");
                        currentPlayer = switchPlayer(currentPlayer);
                        currentPlayer.increaseWins();
                    }
                    else if(command.length()==VALID_COMMAND_LENGTH) {
                        String[] commandArray=command.split(" ");
                        boolean validCommand = true;
                        for(String oneLocation:commandArray) {
                            validCommand = validCommand && (oneLocation.length()==VALID_COMMAND_LENGTH_LOCATION); 
                        }
                        fromRow=command.charAt(COMMAND_CHAR_1);
                        fromColumn=command.charAt(COMMAND_CHAR_2);
                        toRow=command.charAt(COMMAND_CHAR_3);
                        toColumn=command.charAt(COMMAND_CHAR_4);
                        from = new Location(convertRow(fromRow),convertColumn(fromColumn));
                        to = new Location(convertRow(toRow),convertColumn(toColumn));
                        if(validCommand) {
                            try {
                                board.movePiece(from,to,currentPlayer);              
                                currentPlayer = switchPlayer(currentPlayer);       
                                if(board.countPieces(currentPlayer)==0) {
                                    currentPlayer = switchPlayer(currentPlayer);
                                    currentPlayer.increaseWins();
                                    System.out.println(currentPlayer+" wins the game! Congratulation!");
                                    roundFinished=true;
                                }

                            }
                            catch (InvalidMoveException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        else {
                            System.out.println("Invalid comand");
                        }
                    }
                    else {
                        System.out.println("Invalid comand lenght.");
                    }
                }

            }
            showStats();
            System.out.print("Another round? Type yes or no > ");
            if(reader.readString().equalsIgnoreCase(EXIT_COMMAND))
            {
                exitGame=true;
            }            
        }

    }

    /**
     * Method that sets up the two players
     */
    private void setupPlayers()
    {
        System.out.println("Player 1 name:");
        player1=new Player(reader.readString(),0);
        System.out.println("Player 2 name:");
        player2=new Player(reader.readString(),0);
    }

    /**
     * Method that populates the board for this specific game type. 
     * 
     *  * T T T *
     *  * O O O *
     *  * * * * *
     *  * o o o *
     *  * t t t *
     */
    private void setupBoard()
    {
        board=new Board(BOARD_ROWS, BOARD_COLUMNS);
        board.setPieceOnBoard(0,1,new Torch("T",player1));
        board.setPieceOnBoard(0,2,new Torch("T",player1));
        board.setPieceOnBoard(0,3,new Torch("T",player1));
        board.setPieceOnBoard(1,1,new Ball("O",player1));
        board.setPieceOnBoard(1,2,new Ball("O",player1));
        board.setPieceOnBoard(1,3,new Ball("O",player1));
        board.setPieceOnBoard(3,1,new Ball("o",player2));
        board.setPieceOnBoard(3,2,new Ball("o",player2));
        board.setPieceOnBoard(3,3,new Ball("o",player2));
        board.setPieceOnBoard(4,1,new Torch("t",player2));        
        board.setPieceOnBoard(4,2,new Torch("t",player2));
        board.setPieceOnBoard(4,3,new Torch("t",player2));        
    }

    /**
     * Method that converts the row from the comand string/char into int coordonate
     * for seting up a location on the board
     * 
     * @param   row the character to be converted
     * @retrun the int value of the coresponding row
     */
    private int convertRow(char row) 
    {
        int r=NEGATIVE_OUT_OF_BOARD_VALUE;
        for(int i=0;i<ROW_LABEL.length;i++) {
            if(ROW_LABEL[i]==row) {
                r=i;
            }
        }
        return r;
    }

    /**
     * Method that converts the column from the comand string/char into int coordonate
     * for seting up a location on the board
     * 
     * @param   column the character to be converted
     * @retrun the int value of the coresponding column
     */
    private int convertColumn(char column) 
    {
        int r=NEGATIVE_OUT_OF_BOARD_VALUE;
        for(int i=0;i<COLUMN_LABEL.length;i++) {
            if(COLUMN_LABEL[i]==column) {
                r=i;
            }
        }
        return r;
    }   

    /**
     * Method that shows the stats for the players and games played
     */
    private void showStats()
    {
        System.out.println("\n============Game stats:=============");
        System.out.println("Total games played: "+rounds);
        System.out.println("Games won "+player1+": "+player1.getWins());
        System.out.println("Games won "+player2+": "+player2.getWins());
    }

    /**
     * Method that switches the player after each successfull move
     */
    private Player switchPlayer(Player current)
    {
        if(current==player1) {
            return player2;
        }
        else {
            return player1;
        }
    }

    /**
     * Method that shows teh pieces types assigned to each player
     */
    private void showPlayerPieces()
    {
        System.out.println(player1+" pieces: T,O");
        System.out.println(player2+" pieces: t,o\n");
    }

}