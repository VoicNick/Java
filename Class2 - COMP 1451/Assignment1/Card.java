/**
 * This is the class Card of the Assignment 1 project.
 * This class models one card from the deck
 * 
 * @author  Voicu Chirtes
 * @version 5/17/2014
 */

public class Card
{
    private String description;
    private String suit;
    private int value;
    private ValidCards validCards;
    
    /**
     * Default constructor with no parameters
     */
    public Card()
    {
        validCards = new ValidCards();
        setDescription(validCards.getDefaultDescription());
        setSuit(validCards.getDefaultSuit());
        setValue(validCards.getDefaultDescription());
    }
    
    /**
     * Non-default constructor that accepts the description and suit for creating a Card object
     * 
     * @param   description the Card description
     * @param   suit        the Card suit
     */
    public Card(String description, String suit)
    {
        validCards = new ValidCards();
        setDescription(description);
        setSuit(suit);
        setValue(description);
    }
    
    /**
     * Mutator method that sets the description of the Card object.
     * It verifies for non-null and valid descriptions only.
     * 
     * @param   description the description to be set for the Card object
     */
    public void setDescription(String description)
    {
        if(description!=null)
        {
            if(descriptionIsValid(description))
                this.description = description;
            else
                this.description = validCards.getDefaultDescription();
        }
        else
        {
            this.description = validCards.getDefaultDescription();
        }
    }

    /**
     * Mutator method that sets the suit of the Card object.
     * It verifies for non-null and valid suits only.
     * 
     * @param   suit    the suit to be set for the Card object
     */

    public void setSuit(String suit)
    {
        if(suit!=null)
        {
            if(suitIsValid(suit))
                this.suit = suit;
            else
                this.suit = validCards.getDefaultSuit();
        }
        else
        {
            this.suit = validCards.getDefaultSuit();
        }
    }
    
    /**
     * Method that sets the value of the card based on its description
     * It uses the setValue method form teh ValidCards class to accomplish the task.
     * 
     * @param   description the Card description
     */
    public void setValue(String description)
    {
        if(description!=null)
        {        
            if(descriptionIsValid(description))
            {
                value = validCards.setValue(description);
            }
            else
            {
                value = 0;
            }
        }
        else
        {
            value = 0;
        }
    }
    
    /**
     * Method that validates if a description is valid. It uses the valid descriptions
     * stored in the ValidCards class.
     * 
     * @param   description the description to verify
     * @return  boolean result, weather the description is valid(true) or non-valid(false)
     */
    private boolean descriptionIsValid(String description)
    {
        Boolean isValid = false;
        String[] descriptions = validCards.getDescriptions();
        for(int i=0;i<descriptions.length;i++)
        {
            if(description.equalsIgnoreCase(descriptions[i]))
            {
                isValid = true;
            }
        }
        return isValid;
    }
    
    /**
     * Method that validates if a suit is valid. It uses the valid suits stored in
     * the ValidCards class.
     * 
     * @param   suit    the suit to verify
     * @return  boolean result, weather the description is valid(true) or non-valid(false)
     */
    private boolean suitIsValid(String suit)
    {
        Boolean isValid = false;
        String[] suits = validCards.getSuits();
        for(int i=0;i<suits.length;i++)
        {
            if(suit.equalsIgnoreCase(suits[i])) 
            {
                isValid = true;
            }
        }
        return isValid;
    }
    
    /**
     * Accessor method for the description variable of a Card
     * 
     * @return  the description of the Card
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Accessor method for the suit variable of the Card class
     * 
     * @return  the suit of the Card
     */
    public String getSuit()
    {
        return suit;
    }
    
    /**
     * Accessor method for the value variable of the Card class
     * 
     * @return  the value of the Card
     */
    public int getValue()
    {
        return value;
    }
}