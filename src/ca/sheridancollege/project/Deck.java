package ca.sheridancollege.project;

import java.util.ArrayList;

import ca.sheridancollege.project.PokerCard.Suit;
import ca.sheridancollege.project.PokerCard.Value;

public class Deck extends GroupOfCards {

	public Deck(int givenSize) {
		
		super(givenSize);
		// generate a deck of cards 
		ArrayList<Card> cards = new ArrayList<Card>();
		for(Suit suit: PokerCard.Suit.values()) {
			for(Value value: PokerCard.Value.values()) {
				cards.add(new PokerCard(suit, value));
			}
		}
		
		// pick off the redundant number of cards.
		int removedNumber = 52 - givenSize;
		for(int i = 0; i < removedNumber; i++) {
			cards.remove((int)Math.random()*cards.size());
		}
		
		setCards(cards);
	}
}
