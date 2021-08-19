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
       
        
        WarGame warCardGame = new WarGame("War Game"); 
        
        warCardGame.setPlayers(playerArray);
        
        
    }

}
