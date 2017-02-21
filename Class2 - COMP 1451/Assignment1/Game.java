import java.util.ArrayList;
/**
 * This is the class Game of the Assignment 1 project.
 * This class contains the game logic
 * 
 * @author  Voicu Chirtes
 * @version 5/18/2014
 */

public class Game
{
    private Deck deck;
    private InputReader reader;
    private ArrayList<Card> playerHand;
    private int roundsPlayed = 0;
    private int playerPoints = 0;
    private static final int INITIAL_CARDS = 2;
    private static final int BLACKJACK = 21;
    private static final int BLACKJACK_POINTS = 5;
    private static final int TIER1_POINTS = 1;
    private static final int TIER1_VALUE = 17;
    
    /**
     * The default constructor for the Game class.
     */
    public Game()
    {
        deck = new Deck();
        reader = new InputReader();
        playerHand = new ArrayList<Card>();
    }
    
    /**
     * The main method of the Game class that contains the game logic.
     * Call this method ot start a new game.
     */
    public void play()
    {
        Boolean exitGame=false;
        intro();
        roundsPlayed = 0;
        playerPoints = 0;
        while(!exitGame)
        {
            Boolean roundFinished=false;
            playerHand.clear();
            playerHand = newRound();
            roundsPlayed++;
            System.out.println("================New Round=============");
            while(!roundFinished)
            {
                showPlayerHand();
                if(hasBlackjack())
                {
                    System.out.println("Blackjack! You win this round!\n");
                    roundFinished = true;
                    playerPoints+=BLACKJACK_POINTS;
                }
                else
                if(isBusted())
                {
                    System.out.println("Bust! You lost this round. \n");
                    roundFinished = true;
                }
                else
                {
                    System.out.print("Your choice: hit or stand? > ");
                    String answer = reader.readString();
                    if(answer.equalsIgnoreCase("stand"))
                    {
                        roundFinished = true;
                        System.out.println("Player stands");
                        showPlayerHand();
                        if(getPlayerHandValue()>= TIER1_VALUE)
                            playerPoints+=1;
                    }
                    else
                    {
                        if(answer.equalsIgnoreCase("hit"))
                        {
                            playerHand.add(dealCard());
                        }
                    }
                }
            }            
            System.out.print("Another round? Type yes or no > ");
            if(reader.readString().equalsIgnoreCase("no"))
            {
                exitGame=true;
            }
        } 
        report();
    }
    
    /**
     * This method starts a new round by dealing the initial hand of 
     * cards to the player.
     * 
     * @return  the hand of the player, an array list of Cards 
     */
    private ArrayList<Card> newRound()
    {        
        ArrayList<Card> hand = new ArrayList<Card>();
        for(int i=0; i<INITIAL_CARDS;i++)
        {
            hand.add(dealCard());
        }
        return hand;
    }
    
    /**
     * This method deals the first card from the deck to the player. 
     * In case of an empty desk it refills and shuffles it.
     * 
     * @return  the first Card from the deck
     */
    private Card dealCard()
    {
        Card card=deck.getFirstCard();
        if(card == null)
        {
            deck.addAllCardsToDeck();
            deck.shuffleDeck();
            card = deck.getFirstCard();
        }   
        return card;
    }
    
    /**
     * This method shows the hand of cards the player currentelly has.
     */
    private void showPlayerHand()
    {
        if(!playerHand.isEmpty())
        {
            System.out.println("Your cards:");
            for(int i=0;i<playerHand.size();i++)
            {
                System.out.println(playerHand.get(i).getDescription()+" of "+playerHand.get(i).getSuit());
            }
            System.out.println("Hand value: "+getPlayerHandValue()+"\n"); 
        }
    }
    
    /**
     * This method calculates and returns the value of the hand of Cards
     * the player currentelly has.
     * 
     * @return  the total value of the cards in hand
     */
    private int getPlayerHandValue()
    {
        int value = 0;
        if(!playerHand.isEmpty())
        {
            for(int i=0;i<playerHand.size();i++)
            {
                value+=playerHand.get(i).getValue();
            }           
        }
        return value;
    }
    
    /**
     * This method displays an initial introductory message for the game
     */
    private void intro()
    {
        System.out.println();
        System.out.println();
        System.out.println("Welcome to 1451 Blackjack!");
        System.out.println("You will start with two cards.");
        System.out.println("You will be prompted to 'hit' or 'stand'");
        System.out.println("'hit' means you want another card, 'stand' not.");
        System.out.println("You are trying to get Blackjack with exactly 21 points.");
        System.out.println("Game points:");
        System.out.println(" 5 game points - Round won with Blackjack, hand of exactlly 21");
        System.out.println(" 1 game point - round that ends with a hand greater than 16");
        System.out.println();
    }

    /**
     * This method determines wether the player currentelly has Blackjack
     * 
     * @return  wether the player has Blackjack or not
     */
    private boolean hasBlackjack()
    {
        return (getPlayerHandValue()==BLACKJACK);
    }

    /**
     * This method determines wether the player currentelly busted that is he went over
     * Blackjack value
     * 
     * @return  wether the player is busted or not
     */
    private boolean isBusted()
    {
        return (getPlayerHandValue()>BLACKJACK);
    }
    
    /**
     * This method displays the final report and stats for the game
     */
    private void report()
    {
        System.out.println("\nThank you for playing, come again soon.");
        System.out.println("Game stats:");
        System.out.println("Player points: "+playerPoints);
        System.out.println("Total rounds: "+roundsPlayed);
    }    
}