import java.util.HashMap;
/**
 * This is the class ValidCards of the Assignment 1 project.
 * It holds the possible values for Description and Suits and
 * also the default assignments and the values for each Card.
 * 
 * @author  Voicu Chirtes
 * @version 5/17/2014
 */

public class ValidCards
{
    // the possible descriptions a card can have. They need to be in precise order starting with Ace for corect values to determined
    private static final String[] descriptions = {"Ace","Two","Three","Four","Five","Six",
                               "Seven","Eight","Nine","Ten","Jack","Queen","King"};
    private static final String[] suits = {"Clubs","Diamonds","Hearts","Spades"};
    //default description a card would get for no or non-valid parameters
    private static final String defaultDescription = "Joker";
    //the default suit a card would get for no or non-valid parameters
    private static final String defaultSuit = "Spades";
    //non-numbered cards values
    private static final int JOKER_VALUE = 0;
    private static final int ACE_VALUE = 11;
    private static final int TEN_VALUE = 10;
    
    /**
     * The default constructor with no parameters
     */
    public ValidCards()
    {
        // nothing to do here
    }
    
    /**
     * Accessor method that returns all the possible and valid descriptions
     * 
     * @return  String array containing all the possible and valid descriptions
     */
    public String[] getDescriptions()
    {
        return descriptions;
    }
    
    /**
     * Accessor method that returns all the possible and valid suits
     * 
     * @return  String array contining all the possible and valid suits
     */
    public String[] getSuits()
    {
        return suits;
    }
    
    /**
     * Accessor method for the default description that a card would get for no or non-valid parameters
     * 
     * @return  the default description
     */
    public String getDefaultDescription()
    {
        return defaultDescription;
    }
    
    /**
     * Accessor method for the default suit that a card would get for no or non-valid parameters
     * 
     * @return  the default suit
     */
    public String getDefaultSuit()
    {
        return defaultSuit;
    }
    
    /**
     * Method that calculates and returns the face value of a card.
     * 
     * @description the card description
     * @return  the face value of the card.
     */
    public int setValue (String description)
    {
        int value = JOKER_VALUE;
        if(description.equalsIgnoreCase("Ace"))
        {
            value = ACE_VALUE;
        }
        else
        {
            for(int i=0; i<descriptions.length;i++)
            {
                if(description.equalsIgnoreCase(descriptions[i]))
                {
                    if(i<10) 
                    {
                        value = i+1;
                    }
                    else
                    {
                        value = TEN_VALUE;
                    }
                }
            }
        }
        return value;
    }   
}