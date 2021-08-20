/*
 * @modifier Eduardo Rodriguez, E Hyun Kim, Maryam Hisam, Yi-Wen Chu
 * Date August 19, 2021
 * File name: PockerCard.java
 *
 */
package ca.sheridancollege.project;

public class PokerCard extends Card {

    private Suit suit;
    private Value value;

    public PokerCard(Suit suit, Value value) {
        super();
        this.suit = suit;
        this.value = value;
    }

    @Override
    public java.lang.String toString() {
         return value + " of " + suit;
    }

    public int compareTo(PokerCard card) {
        //if  is equal to card suit 
    	if (this.value.compareTo(card.value) > 0) {
    		return 1;
    	}
    	if (this.value.compareTo(card.value) < 0) {
    		return -1;
    	}
        if (this.suit.compareTo(card.suit) > 0) {
            return 1;
        }
        if (this.suit.compareTo(card.suit) < 0) {
            return -1;
        }
        return 0;
    }

    public enum Suit {
    	CLUBS, DIAMONDS, HEARTS, SPADES
    }

    public enum Value {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }
}