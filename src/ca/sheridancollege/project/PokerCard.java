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
         return value + "of" + suit;
    }

    public int compareTo(PokerCard card) {
        //if  is equal to card suit 
        if (this.suit.compareTo(card.suit) > 1) {
            return 1;
        }
        if (this.suit.compareTo(card.suit) < 1) {
            return -1;
        }
        if (this.value.compareTo(card.value) > 1) {
            return 1;
        }
        if (this.value.compareTo(card.value) < 1) {
            return -1;
        }
        return 0;
    }

    public enum Suit {
        SPADES, HEARTS, DIAMONDS, CLUBS
    }

    public enum Value {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }
}
