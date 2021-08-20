package ca.sheridancollege.project;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WarGame extends Game {

	public WarGame(String givenName) {
		super(givenName);
		// TODO Auto-generated constructor stub
	}

	public static int WAR_CARD_NUMBER = 3;

	/**
	 * Play the game. This might be one method or many method calls depending on your game.
	 */
	public void play() {
		
		// Delegate play operation to WarPlayer
		for(Player player: getPlayers()) {
			WarPlayer wp = (WarPlayer) player;
			int wpRound = wp.getRoundStatus() + 1;
			wp.setRoundStatus(wpRound);
			wp.play();
		}
		
		// show card and compare
		int winPlayerIndex = compareCard();
		
		// the player win the war accept the cards from the other players.
		receiveCards(winPlayerIndex);
	}

	/**
	 * When the game is over, use this method to declare 
	 * and display a winning player.
	 */
	public void declareWinner() {
		
		// use clone to prevent affect the priority of players.
		List<Player> players = (List<Player>) getPlayers().clone();

		// Only enough player attend the game will declare the winner.
		if (players.size() > 2) {
			// Rank players according to the hand of cards.
			Collections.sort( players, new Comparator<Player>() {
				@Override
				public int compare(Player p1, Player p2) {
					int wp1cards = ((WarPlayer)p1).getCardHand().size(),
							wp2cards = ((WarPlayer)p2).getCardHand().size();
					int result = 0;
					if(wp1cards > wp2cards) {
						result = 1;
					} else if(wp1cards < wp2cards) {
						result = -1;
					}
					return result; 
				}
			} );
			
			// Check if tie or declare the winner.
			WarPlayer wp1st = (WarPlayer) players.get(0),
						wp2nd = (WarPlayer) players.get(1);
			if(wp1st.getCardHand().size() == wp2nd.getCardHand().size()) {
				System.out.println("TIE!!!");
			} else {
				System.out.println("Player "+wp1st.getPlayerID()+ " WIN!");
			}
		} else {
			System.out.println("No enough players attend the War Game.");
		}
	}

	public void distributeCards() {
		throw new UnsupportedOperationException();
	}

	public void guide() {
		throw new UnsupportedOperationException();
	}

	public void endEarly() {
		System.out.println("The War Game is ended earlier.");
		System.out.println("Declare the winner according to "
				+ "the hand of cards.");
		
		declareWinner();
	}

	public void abort() {
		
		System.out.println("The War Game is aborted!");
		// TODO whether ask users to restart?
	}

	public void restart() {
		// clear all the cards on players' hand.
		resetPlayersStatus();
		// distribute cards again.
		distributeCards();
		
		System.out.println("Game restart!");
	}
	
	private void resetPlayersStatus() {
		
		for(Player player: getPlayers()) {
			WarPlayer wp = (WarPlayer) player;
			wp.getCardHand().clear();
			wp.getHandOutCards().clear();
			wp.setRoundStatus(0);
		}
	}

	private int compareCard() {
		throw new UnsupportedOperationException();
	}

	private void receiveCards(int playerIndex) {
		throw new UnsupportedOperationException();
	}
}
