/*@author Maryam Hisam
 */

package ca.sheridancollege.project;

/**
 *
 * This class is a concrete class which inherits the Card class, and adds the
 * suit and value to the poker cards
 * 
 */
public class PokerCard extends Card {

	private Suit suit;
	private Value value;

	// suit and value enums for the cards
	public enum Suit {
		SPADES, HEARTS, DIAMONDS, CLUBS
	};

	public enum Value {
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
	};

	// constructor for PokerCard
	public PokerCard(Suit suit, Value value) {
		super();
		this.suit = suit;
		this.value = value;
	}

	public Suit getSuit() {
		return this.suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Value getValue() {
		return this.value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	// toString method which returns the value and suit of the card
	@Override()
	public String toString() {
		return value + "of" + suit;
	}

	/*
	 * method which will determine if the player's card has greater,lesser, or equal
	 * rank to the computer's card
	 */
	public int compareTo(PokerCard card) {
		// if is equal to card suit
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

}
