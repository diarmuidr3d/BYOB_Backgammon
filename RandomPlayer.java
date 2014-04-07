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
    
    public RandomPlayer() {

    }
        
    /**
     * Sets the player colour
     *
     * @param c is the colour to be set
     */
    public void setPlayerColour(char c) {
        playerColour = c;
    }
    
    /**
     * Calls the allPossiblePlays method, selects one of these plays at 
random and returns it to the calling method
     * @param d
     * @param b
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public int[][] getPlay(Dice d, Board b) throws FileNotFoundException, IOException{
    	List<int[]> possible_moves = b.allPossiblePlays(d,b);
    	Random generator = new Random(); 
    	int randomPlay = generator.nextInt(possible_moves.size());
    	int[] play = possible_moves.get(randomPlay);
    	if(play[0] == 0){
    		play = possible_moves.get(0);
    	}
    	else{
    		while(possible_moves.get(randomPlay-1)[0] == play[0]) {
        		randomPlay--;
        		play = possible_moves.get(randomPlay);
        	}
    	}
    	int retVal[][] = new int[1][2];
    	retVal[0][0] = play[1];
    	retVal[0][1] = play[2];
    	return retVal;
    }
}
