/*
 * @modifier Eduardo Rodriguez, E Hyun Kim, Maryam Hisam, Yi-Wen Chu
 * Date August 19, 2021
 * File name: WarGame.java
 *
 */

package ca.sheridancollege.project;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class WarGame extends Game {

    public WarGame(String givenName) {
        super(givenName);
    }

    public static int WAR_CARD_NUMBER = 3;
    
    ArrayList<Card> cardHalf = new ArrayList(); 
    ArrayList<Card> cardOtherHalf = new ArrayList();
    
    public void play() {
        
    }    

    /**
     * When the game is over, use this method to declare and display a winning
     * player.
     */
    public void declareWinner() {

        // use clone to prevent affect the priority of players
        List<Player> players = (List<Player>) getPlayers().clone();

        // Only enough player attend the game will declare the winner.
        if (players.size() > 2) {
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
        } else {
            System.out.println("No enough players attend the War Game.");
        }
    }

    public void distributeCards(WarPlayer playerOne, WarPlayer playerTwo) {
        GroupOfCards cardArray = new GroupOfCards(); 
        Deck deck = new Deck(52); 
        
        for(int i = 0; i < cardArray.showCards().size(); i++){
            if (i < 26) {
                cardHalf.add(cardArray.showCards().get(i)); 
                playerOne.addCardHand(cardHalf); 
            } else {
                cardOtherHalf.add(cardArray.showCards().get(i)); 
                playerOne.addCardHand(cardOtherHalf); 
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
        System.exit(0);
        System.out.println("The War Game is aborted!");
        // TODO whether ask users to restart?
    }

    public void restart(WarPlayer playerOne, WarPlayer playerTwo) {
        // clear all the cards on players' hand.
        resetPlayersStatus();
        // distribute cards again.
        distributeCards(playerOne, playerTwo);

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

    private void compareCard() {
        
    }

    private String getCardHand() {
        return null;
    }

    private void receiveCards(int playerIndex) {
        
    }
}
