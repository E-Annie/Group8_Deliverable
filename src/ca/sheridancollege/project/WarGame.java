/*
 * @modifier Eduardo Rodriguez, E Hyun Kim, Maryam Hisam, Yi-Wen Chu
 * Date August 19, 2021
 * File name: WarGame.java
 * 
 * References: 
 * [1] Bicycle. (n.d). War. https://bicyclecards.com/how-to-play/war/
 * [2] War (card game). (2021, April 16). In Wikipedia. https://en.wikipedia.org/
 *  wiki/War_(card_game)
 */

package ca.sheridancollege.project;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class WarGame extends Game {

    public WarGame(String givenName) {
        super(givenName);
    }

    public static int WAR_CARD_NUMBER = 3;
    public static int MIN_PLAYER_NUM = 2;
    
    public void play() {
		
		if(checkWinner()) {
			System.out.println("The winner appears!");
			declareWinner();
		} else {
			// Delegate play operation to WarPlayer
			for(Player player: getPlayers()) {
				WarPlayer wp = (WarPlayer) player;
				int wpRound = wp.getRoundStatus() + 1;
				wp.setRoundStatus(wpRound);
				wp.play();
			}
			
			// show card and compare
			int winPlayerIndex = compareCard();
			
			if(winPlayerIndex > -1) {
				System.out.println("Player "
						+getPlayers().get(winPlayerIndex).getPlayerID() 
						+" holds the card with higher value.");
				// the player win the war accept the cards from the other players.
				receiveCards(winPlayerIndex);
				showCardHandNum();
				
				play();
			} else { // result is tie and war continue.
				play();
			}
		}
    }    

    /**
     * When the game is over, use this method to declare and display a winning
     * player.
     */
    public void declareWinner() {

        // use clone to prevent affect the priority of players
        List<Player> players = (List<Player>) getPlayers().clone();

        // Only enough player attend the game will declare the winner.
        if (checkPlayerNum()) {
            // Rank players according to the hand of cards.
            Collections.sort(players, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    int wp1cards = ((WarPlayer) p1).getCardHand().size(),
                            wp2cards = ((WarPlayer) p2).getCardHand().size();
                    int result = 0;
                    if (wp1cards > wp2cards) {
                        result = 1;
                    } else if (wp1cards < wp2cards) {
                        result = -1;
                    }
                    return result;
                }
            });

            // Check if tie or declare the winner.
            WarPlayer wp1st = (WarPlayer) players.get(0),
                    wp2nd = (WarPlayer) players.get(1);
            if (wp1st.getCardHand().size() == wp2nd.getCardHand().size()) {
                System.out.println("TIE!!!");
            } else {
                System.out.println("Player " + wp1st.getPlayerID() + " WIN!");
            }
        }
    }

    public void distributeCards() {
        
    	// variable used in the method
    	GroupOfCards deck = new Deck(52);
        ArrayList<Player> players = getPlayers();
        int playerNum = players.size();
        
        deck.shuffle();
        
        // Check if the players number reach the minimum.
        if (checkPlayerNum()) {
        	
        	// take turn to distributed card to the player.
        	for (int i = 0; i < deck.showCards().size(); i++) {
        		// calculate which player is in turn to receive the card.
        		int playerIndex = i % playerNum;
        		WarPlayer wp = (WarPlayer) players.get(playerIndex);
        		
        		// distribute card to the player
        		ArrayList<Card> distributedCard = new ArrayList<Card>();
        		distributedCard.add(deck.showCards().get(i));
        		wp.addCardHand(distributedCard);
        	}
        }
 
    }
    
    //Game guide 
    public void guide() {
        System.out.println("Welcome to the ‘War’ card game! You will be playing"
            + " with the computer and the first player to collect all 52 cards"
            + "in the deck wins (Bicycle, n.d.).\n The computer will equally"
            + " distributes the deck of cards, placing it face down"
            + " (Bicycle, n.d.).\n The players (you and the computer) then"
            + " simultaneously open one card each, and the higher card value"
            + " wins and the player takes both cards faced down and adds it to"
            + "the bottom of the cards he/she has (Bicycle, n.d.).\n If the cards"
            + " are of the same value, ‘War’ begins; each player places 3 cards"
            + " face down and then one face-up (“War (card game)”, 2021).\n The "
            + "player with a higher card value wins all cards played and places"
            + " it at the bottom of the deck; but, if face-up cards are of the"
            + " same value again, the process of ‘War’ continues until one player"
            + " has a higher card value (“War (card game)”, 2021). The player who"
            + " has all the cards at the end wins (Bicycle, n.d.).");
    }

    public void endEarly() {
        System.out.println("The War Game is ended earlier.");
        System.out.println("Declare the winner according to "
                + "the hand of cards.");

        declareWinner();
    }

    public void abort() {
        System.out.println("The War Game is aborted!");
        System.exit(0);
    }

    public void restart() {
        // clear all the cards on players' hand.
        resetPlayersStatus();
        // distribute cards again.
        distributeCards();

        System.out.println("Game restart!");
    }

    private void resetPlayersStatus() {

        for (Player player : getPlayers()) {
            WarPlayer wp = (WarPlayer) player;
            wp.getCardHand().clear();
            wp.getHandOutCards().clear();
            wp.setRoundStatus(0);
        }
    }

	private int compareCard() {
		List<Player> players = getPlayers();
		int winnerIndex = -1;
		// read the hand out cards from the first player first.
		ArrayList<Card> handOutCards = ((WarPlayer) players.get(0))
				.getHandOutCards();
		
		// Card comparison will only occur 
		// while there are cards in the hand out cards.
		if(handOutCards.size() > 0) {
			PokerCard comparedCard = (PokerCard) handOutCards.get(0);
			// Message for the 1st player to play card.
			System.out.println("The 1st card played by Player " 
					+players.get(0).getPlayerID()
					+ ": "+ comparedCard);
			
			// get the players, and their cards
			for (int i = 1; i < players.size(); i++) {
				WarPlayer wp = ((WarPlayer) players.get(i));
				PokerCard newComparedCard = (PokerCard)wp.getHandOutCards().get(0);
				// Message for the other player to play card.
				System.out.println("The 1st card played by Player " 
						+players.get(i).getPlayerID()
						+ ": "+ newComparedCard);
				
				int result = newComparedCard.compareTo(comparedCard);
				if(result > 0) {
					winnerIndex = i;
				} else {
					winnerIndex = (i - 1 < 0)? players.size(): i-1;
				}
				
				comparedCard = newComparedCard;
			}
		}

		return winnerIndex;
	}

    /* 
     * Check if the players minimum reach and show message 
     * if there is no enough player. 
     */
    private boolean checkPlayerNum() {
    	
    	boolean result = false;
    	if (getPlayers().size() >= MIN_PLAYER_NUM) {
    		result = true;
        } else {
            System.out.println("No enough players attend the War Game.");
        }
    	return result;
    }
    
    private boolean checkWinner() {
    	
    	for (Player player : getPlayers()) {
    		WarPlayer wp = (WarPlayer) player;
    		if (wp.getCardHand().size() <= 0) {
    			return true;
    		}
    	}
    		
    	return false;
    }
    
    private void receiveCards(int winnerIndex) {
    	List<Player> players = getPlayers();
        WarPlayer wp1 = ((WarPlayer) players.get(0));
        WarPlayer wp2 = ((WarPlayer) players.get(1));

        if (winnerIndex == 0) {
            wp1.addCardHand(wp2.getHandOutCards());
            wp2.getHandOutCards().clear();
        } else {
            wp2.addCardHand(wp1.getHandOutCards());
            wp1.getHandOutCards().clear();
        }
    }
    
    private void showCardHandNum() {
    	for(Player player: getPlayers() ) {
    		WarPlayer wp = (WarPlayer) player;
    		System.out.println("Player "+wp.getPlayerID()+ " has " 
    				+ wp.getCardHand().size() + " cards.");
    	}
    }
}
