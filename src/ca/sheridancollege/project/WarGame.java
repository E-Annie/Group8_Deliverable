/*
 * @modifier Eduardo Rodriguez, E Hyun Kim, Maryam Hisam, Yi-Wen Chu
 * Date August 19, 2021
 * File name: WarPlayer.java
 *
 */

package ca.sheridancollege.project;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import java.util.ArrayList;

public class WarGame extends Game {

    LinkedList<Card> deck1 = new LinkedList<Card>();
    LinkedList<Card> deck2 = new LinkedList<Card>();

    public WarGame(String givenName) {
        super(givenName);
        // TODO Auto-generated constructor stub
    }

    public static int WAR_CARD_NUMBER = 3;

    /**
     * Play the game. This might be one method or many method calls depending on
     * your game.
     */
    public void play() {
        List<Card> war1 = new ArrayList<Card>();
        List<Card> war2 = new ArrayList<Card>();

        //checking do players have enough (4)cards to stay in game
        for (int x = 0; x < 3; x++) {
            //either one player runs out of card is game over
            if (deck1.size() == 0 || deck2.size() == 0) {
                break;
            }

            System.out.println("War card for player1 is xx\nWar card for player2 is xx");

            war1.add(deck1.pop());  //place additional card for war
            war2.add(deck2.pop());
        }

        //only compare result when both players have enough cards for war
        if (war1.size() == 3 && war2.size() == 3) {
            //display the war cards from each player
            System.out.println("War card for player1 is " + war1.get(0).toString());
            System.out.println("War card for player2 is " + war2.get(0).toString());

            // Collections.sort( players, new Comparator<Player>() {
            //if player 1 wins the war round
            if (((WarPlayer) war1).getCardHand().size() > ((WarPlayer) war2).getCardHand().size()) {
                deck1.addAll(war1); //player1 get all 10 cards
                deck1.addAll(war2);
                System.out.println("Player 1 wins the war round");
            }//end if
            //otherwise player 2 wins the war round
            else {
                deck2.addAll(war1); //player2 get all 10 cards
                deck2.addAll(war2);
                System.out.println("Player 2 wins the war round");
            }
        }

    }

    /**
     * When the game is over, use this method to declare and display a winning
     * player.
     *
     * @return
     */
    public void declareWinner() {

        // use clone to prevent affect the priority of players.
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

    public void distributeCards() {

        // if(declareWinner()){
        // 	getCardHand().addAll(wp1st);
        // }
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
        System.exit(0);
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
        throw new UnsupportedOperationException();
    }
}
