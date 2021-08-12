
package ca.sheridancollege.project;

import java.util.ArrayList;

public class WarPlayer extends Player {
    
    //data members 
    private ArrayList<Card> cardHand = new ArrayList(26); 
    private final ArrayList<Card> handOutCards = new ArrayList(); 
    
    //constructor 
    public WarPlayer(String name) {
        super(name); 
    }
    
    //no setter for handOutCards so duistributed cards cannot be changed 
    public ArrayList<Card> getHandOutCards() {
        return handOutCards; 
    }
    
    public ArrayList<Card> getCardHand() {
        return cardHand;
    }
    
    public void setCardHand(ArrayList<Card> cardSet) {
        this.cardHand = cardSet;
    }
    
    //method to pick cards from the player's deck of cards
    public Card pickCard() {
        
        //pick a random card in the deck
        int random = (int)((Math.random() * 26) + 1);
        
        //returns the picked random Card object
        return handOutCards.get(random);
    }
    
    public void play() {
        
    }
}
