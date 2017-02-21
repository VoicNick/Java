import java.util.ArrayList;
import java.util.Collections;
/**
 * This is the class Deck of the Assignment 1 project.
 * This class models a deck of 52 cards
 * 
 * @author  Voicu Chirtes
 * @version 5/18/2014
 */

public class Deck
{
    private ArrayList<Card> deck;
    private ValidCards validCards;
    private InputReader reader;
    
    // instance variable for all the vaid descriptions
    private String[] descriptions;
    //instance variable for all the valid suits
    private String[] suits;
    private final static int DECK_SIZE = 52;
    
    /**
     * Default constructor with no parameters
     */
    public Deck()
    {
        deck = new ArrayList<Card>();
        validCards = new ValidCards();
        descriptions = validCards.getDescriptions();
        suits = validCards.getSuits();
        reader = new InputReader();
    }
    
    /**
     * Method that adds one card to the deck. It verifies for card to be non-null and 
     * for the deck to be not full.
     * 
     * @param   cardToAdd   the Card object to add
     */
    public void addOneCardToDeck(Card cardToAdd)
    {
        if(cardToAdd!=null)
        {
            if(deck.size()<DECK_SIZE)
            {
                deck.add(cardToAdd);    
            }
            else 
            {
                System.out.println("Deck is full, it has all the "+ DECK_SIZE+" cards.");
            }
        }
    }
    
    /**
     * Method that adds all the cards to the deck. This method ensures the deck will have
     * ONLY and ALL the valid cards in the deck after its execution.
     */
    public void addAllCardsToDeck()
    {
            deck.clear();
            for(int i=0;i<descriptions.length;i++) 
            {
                for(int j=0;j<suits.length;j++) 
                {
                    deck.add(new Card(descriptions[i],suits[j]));
                }
            }

    }
    
    /**
     * Accessor method that returns the first card from the deck. If the deck is empty
     * the method returns null
     * 
     * @return  the first Card in the deck or null if deck is empty
     */
    public Card getFirstCard()
    {
        if(!deck.isEmpty())
            return deck.remove(0);
        else
            return null;    
    }
    
    /**
     * Accessor method that displays all the cards from the deck.
     */
    public void showAllCardsInDeck()
    {
        if(!deck.isEmpty())
        {
            System.out.println("==============Deck contains "+deck.size()+" cards================");
            System.out.println("========Here are all the cards in the deck:=========");
            for(int i=0;i<deck.size();i++)
            {
                Card card = deck.get(i);
                System.out.println(card.getDescription()+" of "+card.getSuit());
            }
            System.out.println("====================End of deck====================");
        }
        else
        {
            System.out.println("Deck is empty.");
        }
    }
    
    /**
     * Method to shuffle the cards in the arraylist so that they are randomized
     */
    public void shuffleDeck()
    {
        Collections.shuffle(deck);
    }
}