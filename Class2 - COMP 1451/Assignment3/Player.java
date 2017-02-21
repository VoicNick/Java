import java.util.Random;
/**
 * This is the Player class as required on the Assignment3 
 * 
 * @author  Voicu Chirtes
 * @version 7/7/2014
 */

public class Player
{
    private String name;
    private int wins;
    private Random r;
    private static final String DEFAULT_NAME ="Player";
    private static final int MAX_NUMBER=100;
    
    public Player(String name, int wins)
    {
        r = new Random();
        setName(name);
        setWins(wins);
    }
    
    /**
     * Sets the name of the player
     * @param   name    the name of the player
     */
    public void setName(String name)
    {
        name=name.trim();
        if(name==null || name.equals("")) {
            this.name = (DEFAULT_NAME+r.nextInt(MAX_NUMBER));
            System.out.println("Invalid name, random name selected: "+ this.name);
        }
        else {
            this.name = name;
        }
    }

    /**
     * Accessor method for the player name
     */
    public String getName()
    {
        return name;
    }    

    /**
     * Sets the number of wins of the player
     * @param   the number of wins for this player
     */
    public void setWins(int wins)
    {
        if(wins>=0) {
            this.wins = wins;
        }
        else {
            this.wins = 0;
        }
    }

    /**
     * Increases the number of wins of the player
     */
    public void increaseWins()
    {
        this.wins++;
    }
    
    /**
     * Accessor method for the player wins
     */
    public int getWins()
    {
        return wins;
    }    
    
    /**
     * Overridde the toSting method for this class to provide the name of the player.
     * 
     * @return  the name of the player
     */
    public String toString()
    {
        return name;
    }
}