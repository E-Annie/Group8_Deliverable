/*
 * @modifier Eduardo Rodriguez, E Hyun Kim, Maryam Hisam, Yi-Wen Chu
 * Date August 19, 2021
 * File name: WarPlayer.java
 *
 */

package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

public class WarPlayer extends Player {

    //data members 
    private ArrayList<Card> cardHand;
    private ArrayList<Card> handOutCards;
    private int roundStatus;

    //constructor 
    public WarPlayer(String name) {
        super(name);
        this.cardHand = new ArrayList<Card>(26);
        handOutCards = new ArrayList<Card>();
    }

    public int getRoundStatus() {
        return roundStatus;
    }

    public void setRoundStatus(int roundStatus) {
        this.roundStatus = roundStatus;
    }

    //no setter for handOutCards so duistributed cards cannot be changed 
    public ArrayList<Card> getHandOutCards() {
        return handOutCards;
    }

    public ArrayList<Card> getCardHand() {
        return cardHand;
    }

    public void addCardHand(ArrayList<Card> cardSet) {
        if (cardSet.size() > 26) {
            throw new IllegalArgumentException("The default card hand is "
                    + "half of the deck (26), the player won't get cards "
                    + "more then this number at once.");
        } else {
            this.cardHand.addAll(cardSet);
        }
    }

    //method to pick cards from the player's deck of cards
    private Card pickCard() {

        //pick a random card in the deck
        int random = (int) (Math.random() * cardHand.size());

        //returns the picked random Card object
        return cardHand.remove(random);
    }

    public void play() {
        switch (roundStatus) {
            case 1:
            	if(cardHand.size() > 0) {
            		handOutCards.add(pickCard());
            	}
            default:
            	int pickedTime = 0;
            	while (pickedTime < WarGame.WAR_CARD_NUMBER 
            			&& cardHand.size() > 0) {
            		handOutCards.add(pickCard());
            		pickedTime++;
            	}
                // rotate to put new card in the first
                Collections.rotate(handOutCards, pickedTime);
        }
    }
}
