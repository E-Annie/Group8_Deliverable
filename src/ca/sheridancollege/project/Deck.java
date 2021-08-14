/*@author Maryam Hisam
 */

package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * This class is the Deck class which inherits all the methods of the GroupOfCards class
 * and will be used by the WarGame class 
 * 
 */
public class Deck extends GroupOfCards{
    
    // constructor for the deck class, which inherits all methods from GroupOfCards
    public Deck(int givenSize) {
        super(givenSize);
    }
}
