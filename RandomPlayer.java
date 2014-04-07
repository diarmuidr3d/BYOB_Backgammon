/*
 * COMP20050 - Software Engineering Project 2 - 2014
 * Team: BYOB
 * Members: Michael Dalton (12328661), Stefano Forti(13201749), Diarmuid Ryan (11363776)
 */

package backgammon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class RandomPlayer{

    private char playerColour;
    private List<int[]> possible_moves;
    
    public RandomPlayer() {

    }
    
    private void newTurn(Dice d, Board b) {
    	possible_moves = b.allPossiblePlays(d,b);
    }
    
    /**
     * Sets the player colour
     *
     * @param c is the colour to be set
     */
    public void setPlayerColour(char c) {
        playerColour = c;
    }
    
    public int[] getPlay(Dice d, Board b) throws FileNotFoundException, IOException{
    	Random generator = new Random(); 
    	int randomPlay = generator.nextInt(possible_moves.size());
    	int[] play = possible_moves.get(randomPlay);
    	while (possible_moves.get(randomPlay-1)[0] == play[0]) {
    		randomPlay--;
    		play = possible_moves.get(randomPlay);
    	}
    	possible_moves.remove(randomPlay);
    	int retVal[] = new int[2];
    	retVal[0] = play[1];
    	retVal[1] = play[2];
    	if (!d.isDoubleRoll()) {
    		for (int i =0; i < possible_moves.size(); i++) {
    			if (Math.abs(possible_moves.get(i)[1] - possible_moves.get(i)[2]) == Math.abs(play[1] - play[2])) {
    				possible_moves.remove(i);
    				i--;
    			}
    		}
    	}
    	return retVal;
    }
}
