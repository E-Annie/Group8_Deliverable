/*
 * @modifier Eduardo Rodriguez, E Hyun Kim, Maryam Hisam, Yi-Wen Chu
 * Date August 19, 2021
 * File name: Main.java
 *
 */

package ca.sheridancollege.project;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter your player ID: ");
        String id = input.next(); 
        
        WarPlayer playerOne = new WarPlayer(id); 
        WarPlayer playerComp = new WarPlayer("comp"); 
        
        ArrayList<Player> playerArray = new ArrayList();
        playerArray.add(playerOne);
        playerArray.add(playerComp);
       
        //create a new war card game round
        WarGame warCardGame = new WarGame("War Game"); 
        
        //assign the players to the war card game
        warCardGame.setPlayers(playerArray);
        
        //distribute the shuffled card evenly between the two players
        warCardGame.distributeCards();
        
        //play the game 
        warCardGame.play(); 
        warCardGame.declareWinner(); 
        
    }
   

}
